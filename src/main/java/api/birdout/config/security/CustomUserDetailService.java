// package api.birdout.config.security;

// import java.util.ArrayList;
// import java.util.List;

// import com.msgmart2.msg_mart2api.common.consts.Const;
// import com.msgmart2.msg_mart2api.common.responseHandler.ResponseCode;
// import com.msgmart2.msg_mart2api.dto.auth.AuthBasDto;
// import com.msgmart2.msg_mart2api.entity.auth.AuthBas;
// import com.msgmart2.msg_mart2api.repository.AuthBasRepository;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// import lombok.extern.slf4j.Slf4j;

// @Service
// @Slf4j
// public class CustomUserDetailService implements UserDetailsService {

//   private AuthBasRepository authBasRepository;

//   public CustomUserDetailService(AuthBasRepository authBasRepository) {
//     this.authBasRepository = authBasRepository;
//   }
  
//   @Override
//   public AuthBasDto loadUserByUsername(String loginId) throws UsernameNotFoundException {
//     AuthBas authData = authBasRepository.findByLoginId(loginId);
//     if(authData == null || !authData.getStatus().equals(Const.STATUS_Y.val)) {
//       log.info("Log : [User Detail Service] User Data Not Found");
//       throw new UsernameNotFoundException(ResponseCode.F_AUTH_INFO.message);
//     }

//     List<GrantedAuthority> roles = new ArrayList<>();
//     roles.add(new SimpleGrantedAuthority(authData.getRole()));

//     // save successful login user information
//     return new AuthBasDto(authData.getLoginId(), authData.getPassword(), roles, authData.getAuthBasId());
//   }
  
// }
