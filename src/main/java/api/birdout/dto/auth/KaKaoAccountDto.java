package api.birdout.dto.auth;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class KaKaoAccountDto {
  
  private boolean profile_image_needs_agreement;
  private KaKaoProfileDto profile;
  private boolean has_email;
  private boolean email_needs_agreement;
  private boolean is_email_valid;
  private boolean is_email_verified;
  private String email;

}
