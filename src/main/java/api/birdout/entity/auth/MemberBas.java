package api.birdout.entity.auth;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import api.birdout.entity.community.CommunityBas;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tb_member_bas")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class MemberBas {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "member_bas_id")
  private int memberBasId;

  @Column(name = "role")
  private String role;

  @Column(name = "nick_name")
  private String nickName;

  @Column(name = "email")
  private String email;

  @Column(name = "status")
  private String status;
  
  @Column(name = "create_date")
  private LocalDateTime createDate;

  @OneToMany(mappedBy = "communityBasId", fetch = FetchType.LAZY)
  private List<CommunityBas> communityBas;
  
}
