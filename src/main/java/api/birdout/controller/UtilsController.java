package api.birdout.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.birdout.common.consts.ApiDoc;
import api.birdout.common.responseHandler.ResponseCode;
import api.birdout.common.responseHandler.ResponseDto;
import api.birdout.common.responseHandler.ResponseService;
import api.birdout.config.security.AuthInfo;
import api.birdout.dto.auth.AuthDto;
import api.birdout.service.UtilsService;
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

  private final List<String> imageType = Arrays.asList("jpg", "jpeg", "png");

  private final UtilsService utilsService;
  private final ResponseService responseService;

  @Autowired
  public UtilsController(UtilsService utilsService, ResponseService responseService) {
    this.utilsService = utilsService;
    this.responseService = responseService;
  }

  @ApiOperation(
      value = ApiDoc.T_UPLOAD_IMAGE
    , notes = ApiDoc.N_UPLOAD_IMAGE
  )
  @ApiResponses({
      @ApiResponse(responseCode = "2-3", description = "Not Found Member")
    , @ApiResponse(responseCode = "3-0", description = "Request Validation Fail")
    , @ApiResponse(responseCode = "3-1", description = "Empty Value")
    , @ApiResponse(responseCode = "3-2", description = "Image Data Size Exceeded")
    , @ApiResponse(responseCode = "3-3", description = "File Type Fail")
  })
  @PostMapping("/v1/upload/image")
  // TODO: security path에 권한필요에 등록
  // TODO: 코드검증필요
  public ResponseEntity<ResponseDto> upLoadImage(@Validated @ModelAttribute ImageVo imageVo, @AuthInfo AuthDto auth) throws IllegalStateException, IOException {
    ResponseEntity<ResponseDto> checkValid = this.validateImageFile(imageVo);
    if(checkValid != null) return checkValid;
    return utilsService.upLoadImage(imageVo, auth);
  }

  /**
   * Private Area
   */

  private ResponseEntity<ResponseDto> validateImageFile(ImageVo imageVo) {
    if(imageVo.getFile().isEmpty()) return responseService.send(ResponseCode.F_EMPTY_VALID);
    String[] type = imageVo.getFile().getContentType().split("/");
    if(!imageType.contains(type[1])) return responseService.send(ResponseCode.F_FILE_TYPE);
    return null;
  }

}
