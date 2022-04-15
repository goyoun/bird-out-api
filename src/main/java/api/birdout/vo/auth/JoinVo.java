package api.birdout.vo.auth;

import javax.validation.constraints.NotBlank;

import api.birdout.common.consts.Verify;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@ApiModel("인증")
public class JoinVo {
  
  @ApiModelProperty(value = "인가코드", example = "EF0021keiFikfii_e9399kke999_123-22", required = true)
  @NotBlank(message = Verify.NOT_BLANK)
  private String code;

  // 임시
  @ApiModelProperty(value = "카카오 Rest Api 코드(임시값)", example = "aa020099-ekAfkeiiif0000", required = true)
  private String client_id;
  @ApiModelProperty(value = "카카오 리다이렉트 주소(임시값)", example = "http://localhost:8080", required = true)
  private String redirect_uri;
  
}
