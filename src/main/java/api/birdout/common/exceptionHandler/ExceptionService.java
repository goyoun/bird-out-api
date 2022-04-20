package api.birdout.common.exceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import api.birdout.common.responseHandler.ErrorDto;
import api.birdout.common.responseHandler.ResponseCode;
import api.birdout.common.responseHandler.ResponseDto;
import api.birdout.common.responseHandler.ResponseService;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ExceptionService {

  @Autowired
  private ResponseService responseService;
  
  // validation check excepiton
  @ExceptionHandler(BindException.class)
  protected ResponseEntity<ResponseDto> handleClientRequestValidException(BindException exception) {
    BindingResult bindResult = exception.getBindingResult();
    List<FieldError> baindResultList = bindResult.getFieldErrors();

    List<Object> errors = new ArrayList<>();
    for(final FieldError el: baindResultList) {
      Map<String, String> errObj = new HashMap<>();
      errObj.put("target", el.getField());
      String[] code = el.getDefaultMessage().split("-");
      errObj.put("code", code[0]);
      errObj.put("message", code[1]);
      errors.add(errObj);
    }

    ErrorDto error = ErrorDto.builder().list(errors).build();
    return responseService.sendError(ResponseCode.F_VALID, error);
  }

  // external server(Bad Request)
  @ExceptionHandler(ExternalServer4xx.class)
  protected ResponseEntity<ResponseDto> handleExternalServer4xx(ExternalServer4xx exception) {
    return responseService.send(ResponseCode.valueOf(exception.getMessage()));
  }

  // external server(Internal Server Error)
  @ExceptionHandler(ExternalServer5xx.class)
  protected ResponseEntity<ResponseDto> handleExternalServer5xx(ExternalServer5xx exception) {
    return responseService.send(ResponseCode.valueOf(exception.getMessage()));
  }

  // FIXME: 리팩토링필요
  @ExceptionHandler(NullPointerException.class)
  protected ResponseEntity<ResponseDto> handleNullPointer(NullPointerException exception) {
    log.info("exception : {}", exception);
    log.info("exception.getMessage() : {}", exception.getMessage());
    log.info("exception.fillInStackTrace() : {}", exception.fillInStackTrace());
    return responseService.send(ResponseCode.F_SERVER_ERROR);
  }

}
