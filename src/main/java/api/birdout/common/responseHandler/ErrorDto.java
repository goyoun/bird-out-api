package api.birdout.common.responseHandler;

import java.util.List;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorDto {
  
  private List<Object> list;
  
  @Builder
  private ErrorDto(List<Object> list) {
    this.list = list;
  }

}
