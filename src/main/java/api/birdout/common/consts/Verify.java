package api.birdout.common.consts;

public class Verify {

  /**
   * validation message
   * ex) public static final String NOT_BLANK = "[코드]-[메세지]";
   * 응답 시 "-" 기준으로 잘라서 사용됩니다.
   */
  public static final String NOT_BLANK = "V1-Null or '' Not Allow";
  public static final String F_FORMMAT = "V2-Format Fail";
  public static final String ONLY_NUM = "V3-Need Only Number";
  public static final String ONLY_Y_N = "V4-Need Only Y or N";
  public static final String MORE_ONE = "V5-More than 1";
  public static final String F_PASSWORD = "V6-Password Pattern Fail";
  public static final String F_LOGINID = "V7-LoginId Pattern Fail";
  public static final String MAX_SIZE = "V8-Max Size Excess";

  /**
   * validation rule
   */
  public static final String ONLY_NUM_REG = "^[0-9]*$";
  // 최소8자+최소 한개 문자+최소 한개 숫자+최소 한개 특수 문자
  public static final String PASSWORD_REG = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$";
  // 첫글자는 반드시 영문 소문자 또는 대문자 + 영문 또는 숫자만 + 최소 3글자이상 최대 20글자 미만
  public static final String LOGINID_REG = "^[A-Za-z]{1}[A-Za-z0-9]{3,19}$";
  public static final String ONLY_Y_N_REG = "^[YN]$";

}
