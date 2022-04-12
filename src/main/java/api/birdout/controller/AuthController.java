package api.birdout.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.birdout.entity.auth.MemberBas;
import api.birdout.repository.auth.MemberBasRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class AuthController {
  
  private final MemberBasRepository memberBasRepository;

  @Autowired
  public AuthController(MemberBasRepository memberBasRepository) {
    this.memberBasRepository = memberBasRepository;
  }

  @GetMapping("/v1/auth")
  public void test() {
    List<MemberBas> members = memberBasRepository.findAll();
    log.info("members : {}", members);

    // MemberBas memberById = memberBasRepository.findByMemberBasId("4");
    // log.info("memberById : {}", memberById);
  }
  
}
