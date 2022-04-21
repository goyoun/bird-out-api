package api.birdout.common.responseHandler;

import org.springframework.http.HttpStatus;

public enum ResponseCode {

  S_OK(HttpStatus.OK, "0-0", "ok")

  , F_KAKAO_4XX(HttpStatus.BAD_REQUEST, "1-0", "KaKao Server not answered due to bad request")
  , F_KAKAO_5XX(HttpStatus.INTERNAL_SERVER_ERROR, "1-1", "Kakao Server Error")
  , F_KAKAO_EMAIL(HttpStatus.BAD_REQUEST, "1-2", "Need KaKao Email Data")

  , F_FORBIDDEN(HttpStatus.FORBIDDEN, "2-0", "Forbidden")
  , F_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "2-1", "UnAuthorized")
  , F_MEMBER_STATUS(HttpStatus.FORBIDDEN, "2-2", "Member Status is No Normal")
  , F_NOT_FOUND_MEMBER(HttpStatus.UNAUTHORIZED, "2-3", "Not Found Member")
  , F_DUPL_NICK_NAME(HttpStatus.UNAUTHORIZED, "2-4", "Nick Name is Duplication")

  , F_VALID(HttpStatus.BAD_REQUEST, "3-0", "Request Validation Fail")
  
  , F_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "5-0", "Server Error")
  ;

  public final HttpStatus status;
  public final String code;
  public final String message;
  ResponseCode(HttpStatus status, String code, String message) {
    this.status = status;
    this.code = code;
    this.message = message;
  }

  @Override
  public String toString() {
    return this.name();
  }

}
