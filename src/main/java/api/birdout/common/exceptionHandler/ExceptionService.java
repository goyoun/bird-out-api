// package api.birdout.common.exceptionHandler;

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.validation.BindException;
// import org.springframework.validation.BindingResult;
// import org.springframework.validation.FieldError;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.RestControllerAdvice;

// @RestControllerAdvice
// public class ExceptionService {

//   @Autowired
//   private ResponseService responseService;
  
//   @ExceptionHandler(BindException.class)
//   protected ResponseEntity<ResponseDto> handleClientRequestValidException(BindException exception) {
//     BindingResult bindResult = exception.getBindingResult();
//     List<FieldError> baindResultList = bindResult.getFieldErrors();

//     List<Object> errors = new ArrayList<>();
//     for(final FieldError el: baindResultList) {
//       Map<String, String> errObj = new HashMap<>();
//       errObj.put("target", el.getField());
//       String[] code = el.getDefaultMessage().split("-");
//       errObj.put("code", code[0]);
//       errObj.put("message", code[1]);
//       errors.add(errObj);
//     }

//     ErrorDto error = ErrorDto.builder().list(errors).build();
//     return responseService.sendError(ResponseCode.F_VALID, error);
//   }

// }
