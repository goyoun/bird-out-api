package api.birdout.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
  
  @GetMapping("/test")
  public String kakaoSignin() {
    return "kakao";
  }

  @GetMapping("/test1")
  public String kakao1Signin() {
    return "kakao1";
  }

}
