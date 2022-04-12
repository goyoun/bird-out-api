package api.birdout.common.responseHandler;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseDto {

  private int status;
  private String code;
  private String message;
  private Map<String, Object> data;
  private ErrorDto error;
  private LocalDateTime responseDate = LocalDateTime.now();

  @Builder
  private ResponseDto(ResponseCode responseCode) {
    this.status = responseCode.status.value();
    this.code = responseCode.code;
    this.message = responseCode.message;
  }

  public void addData(Map<String, Object> data) {
    this.data = data;
  }

  public void addError(ErrorDto error) {
    this.error = error;
  }

}
