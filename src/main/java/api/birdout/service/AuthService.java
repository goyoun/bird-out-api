package api.birdout.service;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import api.birdout.common.consts.Const;
import api.birdout.common.exceptionHandler.ExternalServer4xx;
import api.birdout.common.exceptionHandler.ExternalServer5xx;
import api.birdout.common.jwtHandler.JwtTokenProvider;
import api.birdout.common.responseHandler.ResponseCode;
import api.birdout.common.responseHandler.ResponseDto;
import api.birdout.common.responseHandler.ResponseService;
import api.birdout.dto.auth.AuthDto;
import api.birdout.dto.auth.AuthTokenSetDto;
import api.birdout.dto.auth.KaKaoTokenDto;
import api.birdout.dto.auth.KaKaoUserInfoDto;
import api.birdout.dto.auth.MemberInfoDto;
import api.birdout.entity.auth.MemberBas;
import api.birdout.repository.auth.MemberBasRepository;
import api.birdout.utils.RandomUtil;
import api.birdout.vo.auth.JoinVo;
import api.birdout.vo.auth.ReGenerateTokenVo;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class AuthService {
  
  private final MemberBasRepository memberBasRepository;
  private final RandomUtil randomUtil;
  private final JwtTokenProvider jwtTokenProvider;
  private final ResponseService responseService;

  @Autowired
  public AuthService(
    MemberBasRepository memberBasRepository
    , RandomUtil randomUtil
    , JwtTokenProvider jwtTokenProvider
    , ResponseService responseService
  ) {
    this.memberBasRepository = memberBasRepository;
    this.randomUtil = randomUtil;
    this.jwtTokenProvider = jwtTokenProvider;
    this.responseService = responseService;
  }

  public ResponseEntity<ResponseDto> join(JoinVo joinVo) {
    KaKaoTokenDto kakaoToken = this.requestTokenToKakao(joinVo.getCode(), joinVo);
    KaKaoUserInfoDto userInfo = this.requestUserInfoToKakao(kakaoToken);
    if(userInfo.getKakao_account().getEmail() == null) return responseService.send(ResponseCode.F_KAKAO_EMAIL);

    MemberBas birdOutMember = memberBasRepository.findByEmail(userInfo.getKakao_account().getEmail());
    AuthTokenSetDto tokenSet = jwtTokenProvider.createAuthTokenSet(userInfo.getKakao_account().getEmail()); 

    if(birdOutMember == null) {
      this.signUp(tokenSet, userInfo);
    }else{
      // true: 로그인 가능, false: 로그인 불가능
      boolean isNormal = birdOutMember.isNormalMember();
      if(!isNormal) return responseService.send(ResponseCode.F_MEMBER_STATUS);
      this.signIn(tokenSet, birdOutMember);
    }

    Map<String, Object> result = new HashMap<>();
    result.put("tokens", tokenSet);
    return responseService.sendData(ResponseCode.S_OK, result);
  }

  public ResponseEntity<ResponseDto> reGenerateAccessToken(ReGenerateTokenVo reGenerateTokenVo) {
    // refresh token valid check
    boolean isValid = jwtTokenProvider.validateToken(reGenerateTokenVo.getRefreshToken());
    if(!isValid) return responseService.send(ResponseCode.F_VALID);

    // refresh token check
    String email = jwtTokenProvider.parsingToken(reGenerateTokenVo.getRefreshToken());
    MemberBas memberBasData = memberBasRepository.findByEmailAndRefreshToken(email, reGenerateTokenVo.getRefreshToken());
    if(memberBasData == null) return responseService.send(ResponseCode.F_VALID);

    String accesssToken = jwtTokenProvider.createAccessToken(email);
    memberBasData.updateAccessToken(accesssToken);
    memberBasRepository.save(memberBasData);

    Map<String, Object> result = new HashMap<>();
    result.put("token", accesssToken);
    return responseService.sendData(ResponseCode.S_OK, result);
  }

  public ResponseEntity<ResponseDto> signOut(AuthDto auth) {
    MemberBas memberData = memberBasRepository.findByEmail(auth.getEmail());
    if(memberData == null) return responseService.send(ResponseCode.F_NOT_FOUND_MEMBER);
    memberData.signOut();
    memberBasRepository.save(memberData);
    return responseService.send(ResponseCode.S_OK);
  }

  public ResponseEntity<ResponseDto> getInformation(AuthDto auth) {
    MemberBas memberData = memberBasRepository.findByEmail(auth.getEmail());
    if(memberData == null) return responseService.send(ResponseCode.F_NOT_FOUND_MEMBER);
    MemberInfoDto info = MemberInfoDto.builder()
                                      .nickName(memberData.getNickName())
                                      .imageType(memberData.getImageType())
                                      .build();
    info.setImageUrl();

    Map<String, Object> result = new HashMap<>();
    result.put("info", info);
    return responseService.sendData(ResponseCode.S_OK, result);
  }

  /**
   * Private Area
   */
  
  private KaKaoTokenDto requestTokenToKakao(String code, JoinVo joinVo) {
    MultiValueMap<String, String> reqData = new LinkedMultiValueMap<>();
    reqData.add(Const.KAKAO_GRANT_TYPE_KEY.val, Const.KAKAO_GRANT_TYPE_VALUE.val);
    reqData.add(Const.KAKAO_CODE_KEY.val, code);
    // FIXME: 테스트 끝난경우 client_id, redirect_uri은 환경변수로 처리하기
    reqData.add(Const.KAKAO_CLIENT_ID_KEY.val, joinVo.getClient_id());
    reqData.add(Const.KAKAO_REDIRECT_KEY.val, joinVo.getRedirect_uri());

    return WebClient.builder()
                    .baseUrl(Const.KAKAO_TOKNE_BASE_URL.val)
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                    .build()
                    .post()
                    .uri(Const.KAKAO_TOKEN_URL.val)
                    .body(BodyInserters.fromFormData(reqData))
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, err -> Mono.error(new ExternalServer4xx(ResponseCode.F_KAKAO_4XX.toString())))
                    .onStatus(HttpStatus::is5xxServerError, err -> Mono.error(new ExternalServer5xx(ResponseCode.F_KAKAO_5XX.toString())))
                    .bodyToMono(KaKaoTokenDto.class)
                    .log()
                    .block();
  }

  private KaKaoUserInfoDto requestUserInfoToKakao(KaKaoTokenDto token) {
    String reqTokenSet = token.getToken_type() + " " + token.getAccess_token();
    return WebClient.builder()
                    .baseUrl(Const.KAKAO_INFO_BASE_URL.val)
                    .defaultHeader(Const.KAKAO_AUTH_KEY.val, reqTokenSet)
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                    .build()
                    .post()        
                    .uri(Const.KAKAO_INFO_URL.val)
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, err -> Mono.error(new ExternalServer4xx(ResponseCode.F_KAKAO_4XX.toString())))
                    .onStatus(HttpStatus::is5xxServerError, err -> Mono.error(new ExternalServer5xx(ResponseCode.F_KAKAO_5XX.toString())))
                    .bodyToMono(KaKaoUserInfoDto.class)
                    .log()
                    .block();
  }

  private void signIn(AuthTokenSetDto tokenSet, MemberBas birdOutMember) {
    birdOutMember.updateTokenSet(tokenSet);
    memberBasRepository.save(birdOutMember);
  }

  private void signUp(AuthTokenSetDto tokenSet, KaKaoUserInfoDto kakaoInfo) {
    String nickName = this.createNickName();
    MemberBas newMember = MemberBas.builder()
                                   .nickName(nickName)
                                   .role(Const.ROLE_USER.val)
                                   .emial(kakaoInfo.getKakao_account().getEmail())
                                   .status(Const.ZERO.val)
                                   .accessToken(tokenSet.getAccessToken())
                                   .refreshToken(tokenSet.getRefreshToken())
                                   .image(kakaoInfo.getKakao_account().getProfile().getProfile_image_url())
                                   .imageType(Const.KAKAO.val)
                                   .signupDate(LocalDateTime.now())
                                   .build();
    memberBasRepository.save(newMember);
  }

  private String createNickName() {
    boolean isDuplicationCheck = true;
    String nickName = null;

    while(isDuplicationCheck) {
      nickName = randomUtil.generateAlphanumericString(18);
      MemberBas duplicationNickName = memberBasRepository.findByNickName(nickName);
      if(duplicationNickName == null) break;
    }
    return nickName;
  }

}
