package api.birdout.dto.auth;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

@Getter
public class AuthDto extends User {

  private int memberBasId;
  private String email;
  private String accessToken;
  private List<? extends GrantedAuthority> roles;

  public AuthDto(String username, String password, Collection<? extends GrantedAuthority> authorities, int memberBasId) {
    super(username, password, authorities);
    this.memberBasId = memberBasId;
    this.email = username;
    this.accessToken = password;
    this.roles = authorities.stream().collect(Collectors.toList());
	}

}
