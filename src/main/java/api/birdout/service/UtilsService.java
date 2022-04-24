package api.birdout.service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import api.birdout.common.responseHandler.ResponseCode;
import api.birdout.common.responseHandler.ResponseDto;
import api.birdout.common.responseHandler.ResponseService;
import api.birdout.vo.utils.ImageVo;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UtilsService {

  @Value("${custom.file.image-upload-path}")
  private String filePath;

  private final ResponseService responseService;

  @Autowired
  public UtilsService(ResponseService responseService) {
    this.responseService = responseService;
  }
  
  public ResponseEntity<ResponseDto> upLoadImage(ImageVo imageVo) {
    // get file origin name
    String originalFile = imageVo.getFile().getOriginalFilename();
    log.info("originalFile : {}", originalFile);
    
    String originalFileExtension = originalFile.substring(originalFile.lastIndexOf("."));
    log.info("originalFileExtension : {}", originalFileExtension);

    String saveFileName = UUID.randomUUID().toString().replaceAll("-", "") + originalFileExtension;
    log.info("storedFileName : {}", saveFileName);

    // 사진용량 100kb 아래로

    log.info("this.filePath : {}", this.filePath);
    Path path = Paths.get(this.filePath + saveFileName).toAbsolutePath();
    log.info("path : {}", path);
    // imageVo.getFile().transferTo(path.toFile());

    return responseService.send(ResponseCode.S_OK);
  }

}
