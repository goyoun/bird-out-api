// package api.birdout.config.security;

// import java.io.IOException;

// import javax.servlet.ServletException;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;

// import com.msgmart2.msg_mart2api.common.responseHandler.ResponseCode;
// import com.msgmart2.msg_mart2api.common.responseHandler.ResponseService;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.AuthenticationException;
// import org.springframework.security.web.AuthenticationEntryPoint;
// import org.springframework.stereotype.Component;

// import lombok.extern.slf4j.Slf4j;

// /**
//  * 401(UnAuthorized) handler
//  * 인증과정에서 실패했거나 인증헤더(Authorization)를 요청하지 않은 경우
//  */
// @Component
// @Slf4j
// public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
  
//   private final ResponseService responseService;

//   @Autowired
//   public CustomAuthenticationEntryPoint(ResponseService responseService) {
//     this.responseService = responseService;
//   }

//   @Override
//   public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//     response.setContentType("application/json;charset=UTF-8");
//     response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

//     String responseTextOfJson = responseService.sendTextOfJson(ResponseCode.F_UNAUTH);
//     response.getWriter().println(responseTextOfJson);
//   }

// }
