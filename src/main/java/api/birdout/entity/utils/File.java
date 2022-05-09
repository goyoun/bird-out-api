package api.birdout.entity.utils;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tb_file")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class File {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "file_id")
  private int fileId;
  @Column(name = "type")
  private String type;
  @Column(name = "origin_file_name")
  private String originFileName;
  @Column(name = "save_file_name")
  private String saveFileName;
  @Column(name = "extension")
  private String extension;
  @Column(name = "size")
  private long size;
  @Column(name = "path")
  private String path;
  @Column(name = "create_date")
  private LocalDateTime createDate;

  @Builder
  public File(int fileId, String type, String originFileName, String saveFileName, String extension, long size, String path, LocalDateTime createDate) {
    this.fileId = fileId;
    this.type = type;
    this.originFileName = originFileName;
    this.saveFileName = saveFileName;
    this.extension = extension;
    this.size = size;
    this.path = path;
    this.createDate = createDate;
  }

}
