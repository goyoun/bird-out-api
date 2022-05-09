package api.birdout.dto.auth;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class KaKaoProfileDto {

  private String thumbnail_image_url;
  private String profile_image_url;
  private boolean is_default_image;

}
