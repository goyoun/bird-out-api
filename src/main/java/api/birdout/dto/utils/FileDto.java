package api.birdout.dto.utils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class FileDto {
  
  private String originalName;
  private String extension;
  private String saveName;
  private long size;
  private Path path;
  private String pathToSt;

  private FileDto(String originalName, String extension, String saveName, long size, Path path, String pathToSt) {
    this.originalName = originalName;
    this.extension = extension;
    this.saveName = saveName;
    this.size = size;
    this.path = path;
    this.pathToSt = pathToSt;
  }

  public static FileDto getFileData(MultipartFile file, String path) {
    String originalName = file.getOriginalFilename();
    String extension = originalName.substring(originalName.lastIndexOf("."));
    String saveName = UUID.randomUUID().toString().replaceAll("-", "") + extension;
    long size = file.getSize();
    Path pathObj = Paths.get(path + saveName).toAbsolutePath();
    String pathToSt =  path.toString();
    return new FileDto(originalName, extension, saveName, size, pathObj, pathToSt);
  }

}
