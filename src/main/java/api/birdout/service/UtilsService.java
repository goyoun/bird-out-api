package api.birdout.service;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import api.birdout.common.consts.Const;
import api.birdout.common.responseHandler.ResponseCode;
import api.birdout.common.responseHandler.ResponseDto;
import api.birdout.common.responseHandler.ResponseService;
import api.birdout.dto.auth.AuthDto;
import api.birdout.dto.utils.FileDto;
import api.birdout.entity.auth.MemberBas;
import api.birdout.entity.utils.File;
import api.birdout.repository.auth.MemberBasRepository;
import api.birdout.repository.utils.FileRepository;
import api.birdout.vo.utils.ImageVo;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UtilsService {

  @Value("${custom.file.image-upload-path}")
  private String imagePath;

  private final ResponseService responseService;
  private final FileRepository fileRepository;
  private final MemberBasRepository memberBasRepository;

  @Autowired
  public UtilsService(ResponseService responseService, FileRepository fileRepository, MemberBasRepository memberBasRepository) {
    this.responseService = responseService;
    this.fileRepository = fileRepository;
    this.memberBasRepository = memberBasRepository;
  }
  
  @Transactional
  // FIXME: 현재 프로필 사진 업로드 시 기존 파일 삭제 안하고있음(DB랑 폴더)
  public ResponseEntity<ResponseDto> upLoadImage(ImageVo imageVo, AuthDto auth) throws IllegalStateException, IOException {
    FileDto file = FileDto.getFileData(imageVo.getFile(), imagePath);
    imageVo.getFile().transferTo(file.getPath().toFile());
    this.saveFileInfo(file, imageVo.getType());

    if(!imageVo.getType().equals(Const.ZERO.val)) return responseService.send(ResponseCode.S_OK);
    boolean isSaveInfo = this.saveImageInfoToMember(file, auth.getMemberBasId());
    if(!isSaveInfo) return responseService.send(ResponseCode.F_NOT_FOUND_MEMBER);

    return responseService.send(ResponseCode.S_OK);
  }

  /**
   * Private Area
   */

  private void saveFileInfo(FileDto file, String type) {
    fileRepository.save(File.builder()
                            .type(type)
                            .originFileName(file.getOriginalName())
                            .saveFileName(file.getSaveName())
                            .extension(file.getExtension())
                            .size(file.getSize())
                            .path(file.getPathToSt())
                            .createDate(LocalDateTime.now())
                            .build());
  }

  private boolean saveImageInfoToMember(FileDto file, int memberBasId) {
    MemberBas memberBasInfo = memberBasRepository.findByMemberBasId(memberBasId);
    if(memberBasInfo == null) return false;
    memberBasInfo.updateImageLocal(file.getPathToSt());
    memberBasRepository.save(memberBasInfo);
    return true;
  }

}
