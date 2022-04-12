// package api.birdout.config.security;

// import java.io.IOException;

// import javax.servlet.FilterChain;
// import javax.servlet.ServletException;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;

// import com.msgmart2.msg_mart2api.common.jwtHandler.JwtTokenProvider;
// import com.msgmart2.msg_mart2api.common.responseHandler.ResponseService;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import lombok.extern.slf4j.Slf4j;

// @Component
// @Slf4j
// public class SecurityTokenFilter extends OncePerRequestFilter {

//   private final JwtTokenProvider jwtTokenProvider;

//   @Autowired
//   public SecurityTokenFilter(JwtTokenProvider jwtTokenProvider) {
//     this.jwtTokenProvider = jwtTokenProvider;
//   }

//   @Override
//   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//     log.info("Test : ");
//     String token = jwtTokenProvider.resolveToken(request);
//     if (token != null && jwtTokenProvider.validateToken(token)) {
//       Authentication authentication = jwtTokenProvider.getAuthentication(token);
//       SecurityContextHolder.getContext().setAuthentication(authentication);
//     }
//     chain.doFilter(request, response);
//   }

// }