package api.birdout.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import api.birdout.common.jwtHandler.JwtTokenProvider;
import api.birdout.common.responseHandler.ResponseCode;
import api.birdout.common.responseHandler.ResponseService;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SecurityTokenFilter extends OncePerRequestFilter {

  private final JwtTokenProvider jwtTokenProvider;
  private final ResponseService responseService;

  @Autowired
  public SecurityTokenFilter(
    JwtTokenProvider jwtTokenProvider
    , ResponseService responseService
  ) {
    this.jwtTokenProvider = jwtTokenProvider;
    this.responseService = responseService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
    try {
      this.checkAuthentication(request, response, chain);
    } catch (Exception exception) {
      this.handleException(exception, response);
    }
  }
  
  /**
   * Private Area
   */
  
  private void checkAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
    String reqToken = jwtTokenProvider.resolveToken(request);
    if (reqToken != null && jwtTokenProvider.validateToken(reqToken)) {
      Authentication authentication = jwtTokenProvider.getAuthentication(reqToken);
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    chain.doFilter(request, response);
  }

  private void handleException(Exception exception, HttpServletResponse response) throws IOException {
    response.setContentType("application/json;charset=UTF-8");
    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    String responseTextOfJson = responseService.sendTextOfJson(ResponseCode.valueOf(exception.getMessage()));
    response.getWriter().println(responseTextOfJson);
  }

}