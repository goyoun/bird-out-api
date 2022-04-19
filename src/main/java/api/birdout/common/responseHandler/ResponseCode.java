package api.birdout.common.responseHandler;

import org.springframework.http.HttpStatus;

public enum ResponseCode {

  S_OK(HttpStatus.OK, "0-0", "ok")

  , F_MEMBER_STATUS(HttpStatus.FORBIDDEN, "1-0", "Member Status is No Normal")
  , F_VALID(HttpStatus.BAD_REQUEST, "1-1", "Request Validation Fail")
  , F_KAKAO_EMAIL(HttpStatus.BAD_REQUEST, "1-2", "Need KaKao Email Data")
  , F_KAKAO_4XX(HttpStatus.BAD_REQUEST, "1-3", "KaKao Server not answered due to bad request")
  , F_KAKAO_5XX(HttpStatus.INTERNAL_SERVER_ERROR, "1-4", "Kakao Server Error")
  , F_FORBIDDEN(HttpStatus.FORBIDDEN, "1-5", "Forbidden")
  , F_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "1-6", "UnAuthorized")
  , F_NOT_FOUND_MEMBER(HttpStatus.UNAUTHORIZED, "1-7", "Not Found Member")
  , F_DUPL_NICK_NAME(HttpStatus.UNAUTHORIZED, "1-8", "Nick Name is Duplication")
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
