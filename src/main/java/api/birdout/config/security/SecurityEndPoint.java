package api.birdout.config.security;

public final class SecurityEndPoint {

  // 모든 접근 허용
  public static final String[] permitAllPoints = new String[] { 
      "/"
    , "/msgmart/api/doc/**"
    , "/v3/api-docs/**"
    , "/api/v1/**" // TODO: 임시
  };

}