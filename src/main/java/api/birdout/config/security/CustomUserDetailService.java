package api.birdout.config.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import api.birdout.common.consts.Const;
import api.birdout.common.responseHandler.ResponseCode;
import api.birdout.dto.auth.AuthDto;
import api.birdout.entity.auth.MemberBas;
import api.birdout.repository.auth.MemberBasRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomUserDetailService implements UserDetailsService {

  private MemberBasRepository memberBasRepository;

  public CustomUserDetailService(MemberBasRepository memberBasRepository) {
    this.memberBasRepository = memberBasRepository;
  }
  
  @Override
  public AuthDto loadUserByUsername(String email) throws UsernameNotFoundException {
    MemberBas authData = memberBasRepository.findByEmail(email);
    if(authData == null || !authData.getStatus().equals(Const.ZERO.val) || authData.getAccessToken() == null) {
      log.info("[Log] : Not Found Member || Not Status || Logout Status");
      throw new UsernameNotFoundException(ResponseCode.F_FORBIDDEN.toString());
    }

    List<GrantedAuthority> roles = new ArrayList<>();
    roles.add(new SimpleGrantedAuthority(authData.getRole()));

    // save user information
    return new AuthDto(authData.getEmail(), authData.getAccessToken(), roles, authData.getMemberBasId());
  }
  
}
