package api.birdout.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import api.birdout.common.responseHandler.ResponseCode;
import api.birdout.common.responseHandler.ResponseService;
import lombok.extern.slf4j.Slf4j;

/**
 * 403(Forbidden) handler
 * 인증은 했으나 접근 권한(Role)이 없는 경우
 */
@Component
@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

  private final ResponseService responseService;

  @Autowired
  public CustomAccessDeniedHandler(ResponseService responseService) {
    this.responseService = responseService;
  }
  
  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exc) throws IOException, ServletException {  
    response.setContentType("application/json;charset=UTF-8");
    response.setStatus(HttpServletResponse.SC_FORBIDDEN);

    String responseTextOfJson = responseService.sendTextOfJson(ResponseCode.F_FORBIDDEN);
    response.getWriter().println(responseTextOfJson);
  }
  
}
