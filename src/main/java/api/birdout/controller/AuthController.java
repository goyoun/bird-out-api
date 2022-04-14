package api.birdout.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import api.birdout.dto.KaKaoRes;
import api.birdout.entity.auth.MemberBas;
import api.birdout.entity.community.CommunityBas;
import api.birdout.repository.auth.MemberBasRepository;
import api.birdout.repository.community.CommunityBasRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class AuthController {
  
  private final MemberBasRepository memberBasRepository;
  private final CommunityBasRepository communityBasRepository;

  @Autowired
  public AuthController(MemberBasRepository memberBasRepository, CommunityBasRepository communityBasRepository) {
    this.memberBasRepository = memberBasRepository;
    this.communityBasRepository = communityBasRepository;
  }

  @GetMapping("/v1/auth/signin-up")
  public void kakaoSigninUp() {
    // application/x-www-form-urlencoded;charset=utf-8
    // kauth.kakao.com/oauth/token
    /**
     * 필수값
     * grant_type	String	authorization_code로 고정	O
     * client_id	String	앱 REST API 키
     * redirect_uri	String	인가 코드가 리다이렉트된 URI	O
     * code	String	인가 코드 받기 요청으로 얻은 인가 코드	O
     */

     // 참고 https://stackoverflow.com/questions/59792224/how-to-post-request-with-spring-boot-web-client-for-form-data-for-content-type-a
     /**
      * onNext(
        KaKaoRes(
          token_type=bearer, 
          access_token=JN05OQlrc3Ujolrj3pHWUnZFs4p72iHN6-aXZAopcNEAAAGAJ3Lskg, 
          id_token=null, 
          expires_in=21599, 
          refresh_token=_XDIigutl2s341pU7N9o5Rl9YZB67cQww33OGgopcNEAAAGAJ3LskA, 
          refresh_token_expires_in=5183999, 
          scope=account_email 
          profile_image)
        )
      */
    MultiValueMap<String, String> req = new LinkedMultiValueMap<>();
    req.add("grant_type", "authorization_code");
    req.add("client_id", "restapi code 값");
    req.add("redirect_uri", "http://localhost:8080/test1");
    req.add("code", "인가코드");

    WebClient.builder()
    .baseUrl("https://kauth.kakao.com/oauth/token")
    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    // .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
    // .defaultHeader(tokenKey, messageToken)
    .build()
    .post()
    // .uri("/oauth/token")
    .body(BodyInserters.fromFormData(req))
    .retrieve()
    .bodyToMono(KaKaoRes.class)
    .onErrorMap(e -> {
      log.info("e : {}", e.toString()); 
      log.info("e : {}", e.getLocalizedMessage()); 
      log.info("e : {}", e.getMessage()); 
      return e;}
    )
    .log()
    .block();

    // 터미널로 날렸을경우 아래와 같이 날라옴
// {"access_token":"","token_type":"bearer","refresh_token":"","expires_in":21599,"scope":"account_email profile_image","refresh_token_expires_in":5183999}%                                                                       

// curl -v -X POST "https://kauth.kakao.com/oauth/token" \
//  -H "Content-Type: application/x-www-form-urlencoded" \
//  -d "grant_type=authorization_code" \
//  -d "client_id=" \
//  --data-urlencode "redirect_uri=" \
//  -d "code="
  }

  @GetMapping("/v1/auth")
  public void test() {
    List<MemberBas> members = memberBasRepository.findAll();
    log.info("members : {}", members);

    List<CommunityBas> cb = communityBasRepository.findAll();
    log.info("cb : {}", cb);
    // log.info("cb : {}", cb.get(0).getMemberBas().getEmail());
    // log.info("cb : {}", cb.get(0).getMemberBas().getMemberBasId());
    // log.info("cb : {}", cb.get(0).getMemberBas().getNickName());

    // MemberBas memberById = memberBasRepository.findByMemberBasId("4");
    // log.info("memberById : {}", memberById);
  }
  
}
