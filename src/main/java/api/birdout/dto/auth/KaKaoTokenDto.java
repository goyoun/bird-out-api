package api.birdout.dto.auth;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KaKaoTokenDto {
  
  private String token_type;
  private String access_token;
  private String id_token;
  private String expires_in;
  private String refresh_token;
  private String refresh_token_expires_in;
  private String scope;
  
}
