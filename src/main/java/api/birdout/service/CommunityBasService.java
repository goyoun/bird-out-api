package api.birdout.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import api.birdout.common.responseHandler.ResponseCode;
import api.birdout.common.responseHandler.ResponseDto;
import api.birdout.common.responseHandler.ResponseService;
import api.birdout.entity.community.CommunityBas;
import api.birdout.repository.community.CommunityBasRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommunityBasService {
  
  private final CommunityBasRepository communityBasRepository;
  private final ResponseService responseService;

  @Autowired
  public CommunityBasService(CommunityBasRepository communityBasRepository, ResponseService responseService) {
    this.communityBasRepository = communityBasRepository;
    this.responseService = responseService;
  }
  
  public ResponseEntity<ResponseDto> getCommunityAll(String type, Pageable pageable) {
    Page<CommunityBas> communities = communityBasRepository.findByType(type, pageable);

    // MemberBas mem = communities.get(0).getMemberBas();
    // log.info("회원을 찾겠소!!! : {}", mem);
    
    Map<String, Object> result = new HashMap<>();
    result.put("list", communities);
    return responseService.sendData(ResponseCode.S_OK, result);
  }

}
