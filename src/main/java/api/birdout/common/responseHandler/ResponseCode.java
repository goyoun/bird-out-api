package api.birdout.common.responseHandler;

import org.springframework.http.HttpStatus;

public enum ResponseCode {

    S_OK(HttpStatus.OK, "0-0", "ok")
    
  , F_AUTH(HttpStatus.FORBIDDEN, "1-0", "Forbidden")
  , F_ALREADY(HttpStatus.BAD_REQUEST, "1-1", "Already Data")
  , F_AUTH_INFO(HttpStatus.BAD_REQUEST, "1-2", "LoginId Or Password Fail")
  , F_VALID(HttpStatus.BAD_REQUEST, "1-3", "Request Validation Fail")
  , F_NOT_FOUND(HttpStatus.BAD_REQUEST, "1-4", "Not Found Data")
  , F_UNAUTH(HttpStatus.UNAUTHORIZED, "1-5", "Unauthorized")
  , F_FORMAT(HttpStatus.BAD_REQUEST, "1-6", "Format Fail")
  , F_AUTH_VALUE(HttpStatus.BAD_REQUEST, "1-7", "UnAuthenticated Value")
  ;

  public final HttpStatus status;
  public final String code;
  public final String message;
  ResponseCode(HttpStatus status, String code, String message) {
    this.status = status;
    this.code = code;
    this.message = message;
  }

}
