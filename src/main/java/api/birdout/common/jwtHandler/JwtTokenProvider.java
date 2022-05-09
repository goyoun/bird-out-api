package api.birdout.common.jwtHandler;

import java.util.Base64;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import api.birdout.common.responseHandler.ResponseCode;
import api.birdout.config.security.CustomAuthenticationEntryPoint;
import api.birdout.config.security.CustomUserDetailService;
import api.birdout.dto.auth.AuthDto;
import api.birdout.dto.auth.AuthTokenSetDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtTokenProvider implements InitializingBean {

  @Autowired
  CustomUserDetailService customUserDetailService;

  private final String secretKey;
  private String secretKeyB64;
  private final long accessTokenExpirationMillisecond; // 2H
  private final long refreshTokenExpirationMillisecond; // 1W
  private final String headerKey;
  
  public JwtTokenProvider(
    @Value("${jwt.secret-key}") String secretKey
    , @Value("${jwt.access-token-expiration-millisecond}") long accessTokenExpirationMillisecond
    , @Value("${jwt.refresh-token-expiration-millisecond}") long refreshTokenExpirationMillisecond
    , @Value("${jwt.header-key}") String headerKey
  ) {
    this.secretKey = secretKey;
    this.accessTokenExpirationMillisecond = accessTokenExpirationMillisecond;
    this.refreshTokenExpirationMillisecond = refreshTokenExpirationMillisecond;
    this.headerKey = headerKey;
  }

  /**
   * secretKey encode(string to base64)
   * jwt token 생성 및 해독을 위한 key인코딩
   */
  @Override
  public void afterPropertiesSet() {
    secretKeyB64 = Base64.getEncoder().encodeToString(secretKey.getBytes());
  }

  /**
   * jwt token 세트 생성(access, refresh)
   */
  public AuthTokenSetDto createAuthTokenSet(String email) {
    String accessToken = createToken(email, this.accessTokenExpirationMillisecond);
    String refreshToken = createToken(email, this.refreshTokenExpirationMillisecond);
    return AuthTokenSetDto.builder()
                          .accessToken(accessToken)
                          .refreshToken(refreshToken)
                          .build();
  }

  /**
   * jwt access token 생성
   */
  public String createAccessToken(String email) {
    return createToken(email, this.accessTokenExpirationMillisecond);
  }

  /**
   * Http request에서 token 가져오기
   */
  public String resolveToken(HttpServletRequest request) {
    return request.getHeader(headerKey);
  }

  /**
   * token 유효시간 검사 && secretKey 검사
   */
  public boolean validateToken(String token) {
    try {
      Jws<Claims> claims = Jwts.parser().setSigningKey(secretKeyB64).parseClaimsJws(token);
      return !claims.getBody().getExpiration().before(new Date());
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * token으로 user정보 얻기
   * 1. token으로 user정보 get
   * 2. token이 signin token(access token)여부 check
   * 3. security context에 save
   */
  public Authentication getAuthentication(String reqToken) {
    String email = this.parsingToken(reqToken);
    AuthDto memberData = customUserDetailService.loadUserByUsername(email);
    this.checkAccessToken(reqToken, memberData.getAccessToken());
    return new UsernamePasswordAuthenticationToken(memberData, "", memberData.getAuthorities());
  }

  /**
   * token 분석으로 body정보 구하기
   */
  public String parsingToken(String token) {
    return Jwts.parser()
               .setSigningKey(secretKeyB64)
               .parseClaimsJws(token)
               .getBody()
               .getSubject()
               ;
  }

  /**
   * Private Area
   */

  /**
   * jwt token 생성
   */
  private String createToken(String email, long expirationMillisecond) {
    Claims claims = Jwts.claims().setSubject(email);
    long now = (new Date()).getTime();
    Date validityMSc = new Date(now + expirationMillisecond);

    return Jwts.builder()
               .setClaims(claims) // token에 정보 등록
               .setIssuedAt(new Date()) // token 생성일
               .setExpiration(validityMSc) // 유효기간 설정
               .signWith(SignatureAlgorithm.HS256, secretKeyB64) // token생성 방식
               .compact();
  }

  private void checkAccessToken(String reqToken, String accessToken) {
    if(!accessToken.equals(reqToken)) {
      log.info("[Log] : AccessToken Fail");
      throw new UsernameNotFoundException(ResponseCode.F_FORBIDDEN.toString());
    }
  }

}
