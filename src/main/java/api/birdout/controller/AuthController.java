package api.birdout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.birdout.common.responseHandler.ResponseDto;
import api.birdout.config.security.AuthInfo;
import api.birdout.dto.auth.AuthDto;
import api.birdout.service.AuthService;
import api.birdout.vo.auth.JoinVo;
import api.birdout.vo.auth.ReGenerateTokenVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/auth")
@Api(tags = "권한")
public class AuthController {
  
  private final AuthService authService;

  @Autowired
  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @ApiOperation(
      value = "로그인/회원가입"
    , notes = "카카오 로그인으로 회원가입과 로그인을 동시에 진행됩니다."
  )
  @ApiResponses({
      @ApiResponse(responseCode = "1-0", description = "Member Status is No Normal")
    , @ApiResponse(responseCode = "1-1", description = "Request Validation Fail")
    , @ApiResponse(responseCode = "1-2", description = "Need KaKao Email Data")
    , @ApiResponse(responseCode = "1-3", description = "KaKao Server not answered due to bad request")
    , @ApiResponse(responseCode = "1-4", description = "Kakao Server Error")
  })
  @PostMapping("/v1/join")
  public ResponseEntity<ResponseDto> join(@Validated @RequestBody JoinVo joinVo) {
    return authService.join(joinVo);
  }

  @ApiOperation(
      value = "로그아웃"
    , notes = "BirdOut에 로그아웃을 요청합니다.\t\n요청 시 접근토큰이 필요합니다."
  )
  @ApiResponses({@ApiResponse(responseCode = "1-7", description = "Not Found Member")})
  @GetMapping("/v1/sign-out")
  public ResponseEntity<ResponseDto> signOut(@AuthInfo AuthDto auth) {
    return authService.signOut(auth);
  }

  @PostMapping("/v1/re-generate/access-token")
  @ApiOperation(value = "접근토큰 재발급", notes = "갱신토큰으로 접근토큰을 재발급 받을 수 있습니다.")
  @ApiResponses({@ApiResponse(responseCode = "1-1", description = "Request Validation Fail")})
  public ResponseEntity<ResponseDto> reCreateAccessToken(@Validated @RequestBody ReGenerateTokenVo reGenerateTokenVo) {
    return authService.reGenerateAccessToken(reGenerateTokenVo);
  }
  
  @ApiOperation(
      value = "멤버 정보 조회"
    , notes = "아래의 멤버 정보를 조회 합니다.\t\n1.닉네임\t\n2.프로필 이미지"
  )
  @ApiResponses({@ApiResponse(responseCode = "1-7", description = "Not Found Member")})
  @GetMapping("/v1/members/information")
  public ResponseEntity<ResponseDto> getInformation(@AuthInfo AuthDto auth) {
    return authService.getInformation(auth);
  }

  // TODO: 개발필요
  @ApiOperation(
      value = "멤버 정보 수정"
    , notes = "아래의 멤버 정보를 수정 합니다.\t\n1.닉네임\t\n2.프로필 이미지"
  )
  @PostMapping("/v1/update/members/information")
  public ResponseEntity<ResponseDto> updateInformation(@AuthInfo AuthDto auth) {
    return authService.getInformation(auth);
  }

}
