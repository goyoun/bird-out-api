package api.birdout.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.validation.annotation.Validated;
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
    , notes = "이미지 업로드는 Postman으로 요청 바랍니다."
  )
  @ApiResponses({@ApiResponse(responseCode = "3-0", description = "Request Validation Fail")})
  @PostMapping("/v1/image")
  public void upLoadImage(@Validated @ModelAttribute ImageVo imageVo) throws IllegalStateException, IOException {
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

    Path path = Paths.get("/app/birdout/public/images/" + storedFileName).toAbsolutePath();
    log.info("path : {}", path);
    imageVo.getFile().transferTo(path.toFile());
  }

}
