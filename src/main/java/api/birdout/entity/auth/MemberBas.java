package api.birdout.entity.auth;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import api.birdout.common.consts.Const;
import api.birdout.dto.auth.AuthTokenSetDto;
import lombok.AccessLevel;
import lombok.Builder;
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

  @Column(name = "access_token")
  private String accessToken;

  @Column(name = "refresh_token")
  private String refreshToken;

  @Column(name = "image_type")
  private String imageType;

  @Column(name = "image")
  private String image;

  @Column(name = "nick_name")
  private String nickName;

  @Column(name = "email")
  private String email;

  @Column(name = "status")
  private String status;
  
  @Column(name = "signup_date")
  private LocalDateTime signupDate;
  
  @Builder
  public MemberBas(
    int memberBasId
    , String role
    , String nickName
    , String emial
    , String status
    , String accessToken
    , String refreshToken
    , String imageType
    , String image
    , LocalDateTime signupDate
  ) {
    this.memberBasId = memberBasId;
    this.role = role;
    this.nickName = nickName;
    this.email = emial;
    this.status = status;
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
    this.imageType = imageType;
    this.image = image;
    this.signupDate = signupDate;
  }

  public boolean isNormalMember() {
    return (this.status.equals(Const.ZERO.val)) ? true : false;
  }

  public void updateTokenSet(AuthTokenSetDto tokenSet) {
    this.accessToken = tokenSet.getAccessToken();
    this.refreshToken = tokenSet.getRefreshToken();
  }

  public void updateAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public void signOut() {
    this.accessToken = null;
    this.refreshToken = null;
  }

}
