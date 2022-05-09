package api.birdout.vo.utils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.web.multipart.MultipartFile;

import api.birdout.common.consts.Verify;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ImageVo {

  @ApiModelProperty(value = "이미지 파일", required = true)
  private MultipartFile file;

  @ApiModelProperty(value = "구분 타입", example = "0", required = true)
  @NotBlank(message = Verify.NOT_BLANK)
  @Pattern(regexp = Verify.IMAGE_TYPE_REG, message = Verify.F_IMAGE_TYPE)
  private String type;
  
}