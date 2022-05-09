package api.birdout.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import api.birdout.entity.auth.MemberBas;

public interface MemberBasRepository extends JpaRepository<MemberBas, Integer> {
  
  public MemberBas findByMemberBasId(int id);
  public MemberBas findByEmail(String email);
  public MemberBas findByNickName(String nickName);
  public MemberBas findByEmailAndRefreshToken(String email, String refreshToken);
  
}