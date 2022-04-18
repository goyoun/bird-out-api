package api.birdout.vo.utils;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ImageVo {

  private MultipartFile file;
  private String type;
  
}