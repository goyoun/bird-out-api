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

import api.birdout.common.responseHandler.ResponseCode;
import api.birdout.common.responseHandler.ResponseDto;
import api.birdout.common.responseHandler.ResponseService;
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
      value = "이미지 업로드"
    , notes = "이미지 업로드 테스트는 Postman 사용해주세요 \t\ntype종류는 아래와 같습니다 \t\n0 : user프로필이미지 \t\n1 : 커뮤니티 이미지 \t\n**(확장자는 jpg, jpeg, png만 가능합니다.)**"
  )
  @ApiResponses({
      @ApiResponse(responseCode = "3-0", description = "Request Validation Fail")
    , @ApiResponse(responseCode = "3-1", description = "Empty Value")
    , @ApiResponse(responseCode = "3-2", description = "Image Data Size Exceeded")
    , @ApiResponse(responseCode = "3-3", description = "File Type Fail")
  })
  @PostMapping("/v1/create/image")
  public ResponseEntity<ResponseDto> upLoadImage(@Validated @ModelAttribute ImageVo imageVo) throws IllegalStateException, IOException {
    ResponseEntity<ResponseDto> checkValid = this.validateImageFile(imageVo);
    if(checkValid != null) return checkValid;
    return utilsService.upLoadImage(imageVo);
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
