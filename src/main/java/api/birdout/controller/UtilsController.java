package api.birdout.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.birdout.vo.utils.ImageVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/utils")
@Api(tags = "유틸")
@Slf4j
public class UtilsController {

  @ApiOperation(
      value = "이미지 업로드"
    , notes = "동시에 진행됩니다."
  )
  @ApiResponses({@ApiResponse(responseCode = "1-4", description = "Kakao Server Error")})
  @PostMapping("/v1/image")
  // @RequestPart("file") MultipartFile file, 
  public void upLoadImage(@ModelAttribute ImageVo imageVo) throws IllegalStateException, IOException {
    // log.info("file ====> : {}", file);
    // TODO: validation check
    /**
     * 1. file size
     * 2. null check
     * 3. file 확장자 체크
     */
    // get file origin name
    String originalFile = imageVo.getFile().getOriginalFilename();
    log.info("originalFile : {}", originalFile);
    
    String originalFileExtension = originalFile.substring(originalFile.lastIndexOf("."));
    log.info("originalFileExtension : {}", originalFileExtension);

    String storedFileName = UUID.randomUUID().toString().replaceAll("-", "") + originalFileExtension;
    log.info("storedFileName : {}", storedFileName);


    // 실질적 파일 업로드 로직
    // File file = new File("/home/ec2-user/birdout/public/images/" + storedFileName);
    // log.info("file : {}", file);

    Path path = Paths.get("/app/birdout/public/images/" + storedFileName).toAbsolutePath();
    // Path path = Paths.get("/Users/superpil/Documents/etc-doc/" + storedFileName).toAbsolutePath();
    log.info("path : {}", path);
    imageVo.getFile().transferTo(path.toFile());



    // Path savePath = Paths.get("/Users/superpil/Documents");
    // log.info("savePath! : {}", savePath);

    // File file = new File("/Users/superpil/Documents/etc-doc/cb5bb2df849b223781d4c4fbf8d2b2e2.jpeg");
    // log.info("file : {}", file);

    // aa.transferTo(file);
  }

}
