package api.birdout.entity.community;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import api.birdout.entity.auth.MemberBas;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tb_community_bas")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class CommunityBas {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "community_bas_id")
  private int communityBasId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name ="member_bas_id")
  @JsonIgnore
  private MemberBas memberBas;

  @Column(name = "file_id")
  private String fileId;

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

  @Column(name = "country_code")
  private String countryCode;

  @Column(name = "city_code")
  private String cityCode;

  @Column(name = "create_date")
  private LocalDateTime createDate;

  @Column(name = "modify_date")
  private LocalDateTime modify_date;

}
