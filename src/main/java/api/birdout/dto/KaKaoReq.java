package api.birdout.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KaKaoReq {

  private String grant_type = "authorization_code";
  private String client_id = "c76c2faea754c69bddcd0d37d2d8303d";
  private String redirect_uri = "http://localhost:8080/test1";
  private String code = "YgQIDb00MNpohvvvW-u8TsC53ev2VaQXLJlsku_x-81CQix5J8nTKH4eOXdawTYjvvWtUgo9dZwAAAGAJ0RqKQ";

}
