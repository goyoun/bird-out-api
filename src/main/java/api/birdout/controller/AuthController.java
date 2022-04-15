package api.birdout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.birdout.service.AuthService;
import api.birdout.vo.auth.JoinVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api")
@Api(tags = "회원권한")
public class AuthController {
  
  private final AuthService authService;

  @Autowired
  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @ApiOperation(
    value = "조인"
    , notes = "일반회원 로그인/회원가입 요청\n카카오 로그인으로 회원가입과 로그인을 동시에 진행됩니다."
  )
  @ApiResponses({
    @ApiResponse(responseCode = "1-2", description = "LoginId Or Password Fail")
  })
  @PostMapping("/v1/auth/join")
  public void join(@RequestBody JoinVo joinVo) {
    authService.join(joinVo);
  }
  
  @GetMapping("/v1/auth")
  public void test() {
    // List<MemberBas> members = memberBasRepository.findAll();
    // log.info("members : {}", members);

    // List<CommunityBas> cb = communityBasRepository.findAll();
    // log.info("cb : {}", cb);
    // log.info("cb : {}", cb.get(0).getMemberBas().getEmail());
    // log.info("cb : {}", cb.get(0).getMemberBas().getMemberBasId());
    // log.info("cb : {}", cb.get(0).getMemberBas().getNickName());

    // MemberBas memberById = memberBasRepository.findByMemberBasId("4");
    // log.info("memberById : {}", memberById);
  }
  
  // TODO: logout

  // TODO: 회원정보 조회(닉네임, 이미지)
  
}
