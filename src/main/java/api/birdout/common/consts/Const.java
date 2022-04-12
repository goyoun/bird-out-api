package api.birdout.common.consts;

public enum Const {

    STATUS_Y("Y")
  , STATUS_N("N")
  , ROLE_USER("ROLE_USER")
  , ROLE_ADMIN("ROLE_ADMIN")
  , ACCESS_TOKEN_TYPE("ACT")
  , REFRESH_TOKEN_TYPE("RFT")
  ;

  public String val;
  Const(String val) {
    this.val = val;
  }
  
}
