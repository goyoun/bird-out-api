package api.birdout.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.birdout.entity.auth.MemberBas;
import api.birdout.entity.community.CommunityBas;
import api.birdout.repository.auth.MemberBasRepository;
import api.birdout.repository.community.CommunityBasRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class AuthController {
  
  private final MemberBasRepository memberBasRepository;
  private final CommunityBasRepository communityBasRepository;

  @Autowired
  public AuthController(MemberBasRepository memberBasRepository, CommunityBasRepository communityBasRepository) {
    this.memberBasRepository = memberBasRepository;
    this.communityBasRepository = communityBasRepository;
  }

  @GetMapping("/v1/auth")
  public void test() {
    List<MemberBas> members = memberBasRepository.findAll();
    log.info("members : {}", members);

    List<CommunityBas> cb = communityBasRepository.findAll();
    log.info("cb : {}", cb);
    // log.info("cb : {}", cb.get(0).getMemberBas().getEmail());
    // log.info("cb : {}", cb.get(0).getMemberBas().getMemberBasId());
    // log.info("cb : {}", cb.get(0).getMemberBas().getNickName());

    // MemberBas memberById = memberBasRepository.findByMemberBasId("4");
    // log.info("memberById : {}", memberById);
  }
  
}
