package api.birdout.vo.auth;

import javax.validation.constraints.NotBlank;

import api.birdout.common.consts.Verify;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel("토큰 재발급")
public class ReGenerateTokenVo {
  
  @ApiModelProperty(value = "갱신토큰", example = "kfiekefifsfsflkei_ekefiwflw_1231i_eiek_19kl", required = true)
  @NotBlank(message = Verify.NOT_BLANK)
  private String refreshToken;

}
