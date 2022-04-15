package api.birdout.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import api.birdout.dto.auth.KaKaoUserInfoDto;
import api.birdout.entity.auth.MemberBas;
import api.birdout.repository.auth.MemberBasRepository;
import api.birdout.vo.auth.JoinVo;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthService {
  
  private final MemberBasRepository memberBasRepository;

  @Autowired
  public AuthService(MemberBasRepository memberBasRepository) {
    this.memberBasRepository = memberBasRepository;
  }

  public void join(JoinVo joinVo) {
    /**
     * 함수 뽑아낼것
     * 1. 토큰 발급 받기
     * 2. 사용자 정보 조회
     * 3. 로그인, 회원가입
     */
    // MemberBas member = memberBasRepository.findByEmail(userInfo.getKakao_account().getEmail());

    // KaKaoTokenDto kakaoToken = this.requestTokenToKakao();
    this.requestTokenToKakao();
    KaKaoUserInfoDto userInfo = this.requestUserInfoToKakao();
    MembrBas birdOutMember = userInfo.findBirdOutMemberByEmail();

    if(birdOutMember == null) {
      this.signUp();
      /**
       * 1. 랜덤숫자 발행(닉네임)
       * 1. 토큰 생성
       * 2. 토큰 업데이트(카카오 사진, 닉네임)
       * 3. 토큰 리턴
       */
    }else{
      this.signIn();
      /**
       * 1. status가 0인경우만
       * 1. 토근생성
       * 2. 토큰 업데이트(카카오 사진)
       * 3. 토큰 리턴
       */
    }

    
    /**
     * 1. 이메일로 auth에 있는지 조회
     * 2. 있다면 access-token, refresh-token생성해서 발급 + 데이터 업데이트
     * 3. 없다면 토큰이랑 이메일을 db에 저장하기(닉네임 숫자 랜덤으로 넣기)
     * 4. 이미지가 문젠데. 처음 회원
     * 5. 결국엔 accecc token, refresh token 발급
     */
  }

  private void requestTokenToKakao() {


    // application/x-www-form-urlencoded;charset=utf-8
    // kauth.kakao.com/oauth/token
    /**
     * 필수값
     * grant_type	String	authorization_code로 고정	O
     * client_id	String	앱 REST API 키
     * redirect_uri	String	인가 코드가 리다이렉트된 URI	O
     * code	String	인가 코드 받기 요청으로 얻은 인가 코드	O
     */

    // log.info("joinVo : {}", joinVo);
    // MultiValueMap<String, String> req = new LinkedMultiValueMap<>();
    // req.add("grant_type", "authorization_code");
    // req.add("client_id", joinVo.getClient_id());
    // req.add("redirect_uri", joinVo.getRedirect_uri());
    // req.add("code", joinVo.getCode());

    // KaKaoTokenDto join = WebClient.builder()
    // .baseUrl("https://kauth.kakao.com/oauth/token")
    // .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    // .build()
    // .post()
    // .body(BodyInserters.fromFormData(req))
    // .retrieve()
    // .bodyToMono(KaKaoRes.class)
    // .doOnSuccess(res -> {
    //   log.info("res : {}", res);               
    // })
    // .onErrorMap(e -> {
    //   log.info("e : {}", e.toString()); 
    //   log.info("e : {}", e.getLocalizedMessage()); 
    //   log.info("e : {}", e.getMessage()); 
    //   return e;}
    // )
    // .log()
    // .block();
  }

  private KaKaoUserInfoDto requestUserInfoToKakao() {
    // TODO: test 해보기
  return WebClient.builder()
  .baseUrl("https://kapi.kakao.com")
  //  .defaultHeader("Authorization", "Bearer" + " " + join.getAccess_token())
  .defaultHeader("Authorization", "Bearer" + " " + "cTtpeiMJe47SCRsU_mW7wlG-4QgrHYxZKpmcRQopyWAAAAGAKzMXBA")
  .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  .build()
  .post()         
  .uri("/v2/user/me")
  .retrieve()
  .bodyToMono(KaKaoUserInfoDto.class)
  .doOnSuccess(res1 -> {
    log.info("res1 : {}", res1);
  })
  .block();
  }

  private void signIn() {

  }

  private void signUp() {
    
  }

}
