package api.birdout.vo.auth;

import javax.validation.constraints.Min;

import api.birdout.common.consts.Verify;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ApiModel("멤버정보 변경")
@ToString
public class MemberUpdateVo {
  
  @ApiModelProperty(value = "멤버ID", example = "1", required = true)
  @Min(value = 1, message = Verify.F_MIN_ID)  
  private int memberId;

  @ApiModelProperty(value = "닉네임", example = "Superpil")
  private String nickName;
  
}
