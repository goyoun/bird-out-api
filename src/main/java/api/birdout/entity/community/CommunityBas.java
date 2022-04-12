package api.birdout.entity.community;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_community_bas")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CommunityBas {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "community_bas_id")
  private int communityBasId;

  @Column(name = "member_bas_id")
  private int memberBasId;

  @Column(name = "type")
  private String type;

  @Column(name = "status")
  private String status;

  @Column(name = "title")
  private String title;

  @Column(name = "contents")
  private String contents;

  @Column(name = "short_contents")
  private String shortContents;

  @Column(name = "views")
  private int views;

  @Column(name = "create_date")
  private LocalDateTime createDate;

  @Column(name = "modify_date")
  private LocalDateTime modify_date;

}
