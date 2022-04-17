package api.birdout.common.consts;

public enum Const {

    STATUS_Y("Y")
  , STATUS_N("N")
  , ROLE_USER("ROLE_USER")
  , ROLE_BROK("ROLE_BROK")
  , ROLE_ADMIN("ROLE_ADMIN")
  , ZERO("0")
  , ONE("1")
  , TWO("2")

  /**
   * KAKAO
   */
  , KAKAO("KKO")
  , KAKAO_TOKNE_BASE_URL("https://kauth.kakao.com")
  , KAKAO_TOKEN_URL("/oauth/token")
  , KAKAO_INFO_BASE_URL("https://kapi.kakao.com")
  , KAKAO_INFO_URL("/v2/user/me")
  , KAKAO_GRANT_TYPE_KEY("grant_type")
  , KAKAO_GRANT_TYPE_VALUE("authorization_code")
  , KAKAO_CODE_KEY("code")
  , KAKAO_CLIENT_ID_KEY("client_id")
  , KAKAO_REDIRECT_KEY("redirect_uri")
  , KAKAO_AUTH_KEY("Authorization")
  ;

  public String val;
  Const(String val) {
    this.val = val;
  }
  
}
