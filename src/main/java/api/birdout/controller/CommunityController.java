package api.birdout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import api.birdout.common.responseHandler.ResponseDto;
import api.birdout.service.CommunityBasService;

@RestController
@RequestMapping("/api")
public class CommunityController {
  
  private final CommunityBasService communityBasService;

  @Autowired
  public CommunityController(CommunityBasService communityBasService) {
    this.communityBasService = communityBasService;
  }

  @GetMapping("/v1/communities")
  public ResponseEntity<ResponseDto> getCommunityAll(@RequestParam("type") String type, Pageable pageable) {
    return communityBasService.getCommunityAll(type, pageable);
  }

  // @GetMapping("/v1/community")
  // public void getCommunity() {
    
  // }

  // // 등록
  // @PostMapping("/v1/community")
  // public void getCommunity() {
    
  // }

  // // 수정
  // @PostMapping("/v1/community/update")
  // public void getCommunity() {
    
  // }

  // // 본인만 삭제
  // @PostMapping("/v1/community/delete")
  // public void getCommunity() {
    
  // }

}
