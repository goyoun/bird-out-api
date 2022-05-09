package api.birdout.config.security;

public final class SecurityEndPoint {

  // 모든 접근 허용
  // public static final String[] permitAllPoints = new String[] { 
    //   "/"
    // , "/bird-out/api/doc/**"
    // , "/v3/api-docs/**"
    // , "/api/auth/v1/join"
    // , "/api/auth/v1/re-generate/access-token"
  // };

  // 인증된 사용자
  public static final String[] authenticatEndPoints = new String[] { 
      "/api/auth/v1/sign-out"
    , "/api/auth/v1/members/information"
  };

}