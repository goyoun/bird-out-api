package api.birdout.dto.auth;

import org.springframework.beans.factory.annotation.Value;

import api.birdout.common.consts.Const;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberInfoDto {
  
  private int memberId;
  private String nickName;
  private String imageType;
  private String image;
  @Value("${public.image-path}")
  private String imagePath;

  @Builder
  public MemberInfoDto(int memberId, String nickName, String imageType, String image) {
    this.memberId = memberId;
    this.nickName = nickName;
    this.imageType = imageType;
    this.image = image;
  }
  
  // FIXME: 이미지 경로 설정되면 코드 수정 필요
  public void setImageUrl() {
    if(!this.imageType.equals(Const.KAKAO.val)) this.image += this.imagePath;
  }

}
