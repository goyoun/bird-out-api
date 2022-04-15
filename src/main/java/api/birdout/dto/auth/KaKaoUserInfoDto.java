package api.birdout.dto.auth;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class KaKaoUserInfoDto {

  private long id;
  private String connected_at;
  private KaKaoAccountDto kakao_account;

}
