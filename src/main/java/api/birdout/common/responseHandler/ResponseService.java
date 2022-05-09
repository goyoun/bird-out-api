package api.birdout.common.responseHandler;

import java.time.LocalDateTime;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import api.birdout.config.jackson.CustomLocalDateTimeSerializer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseService {
  
  public ResponseEntity<ResponseDto> send(ResponseCode responseCode) {
    return new ResponseEntity<ResponseDto>(setBasicResponse(responseCode), responseCode.status);
  }

  public Mono<ResponseEntity<ResponseDto>> rsSend(ResponseCode responseCode) {
    return Mono.just(this.send(responseCode));
  }

  public ResponseEntity<ResponseDto> sendData(ResponseCode responseCode, Map<String, Object> data) {
    ResponseDto basicResponse = setBasicResponse(responseCode);
    basicResponse.addData(data);
    return new ResponseEntity<ResponseDto>(basicResponse, responseCode.status);
  }

  public Mono<ResponseEntity<ResponseDto>> rsSendData(ResponseCode responseCode, Map<String, Object> data) {
    return Mono.just(this.sendData(responseCode, data));
  }

  public ResponseEntity<ResponseDto> sendError(ResponseCode responseCode, ErrorDto error) {
    ResponseDto basicResponse = setBasicResponse(responseCode);
    basicResponse.addError(error);
    return new ResponseEntity<ResponseDto>(basicResponse, responseCode.status);
  }

  public Mono<ResponseEntity<ResponseDto>> rsSendError(ResponseCode responseCode, ErrorDto error) {
    return Mono.just(this.sendError(responseCode, error));
  }

  public String sendTextOfJson(ResponseCode status) throws JsonProcessingException {
    ResponseDto resDto = ResponseDto
                          .builder()
                          .responseCode(status)
                          .build();
                          
    SimpleModule simpleModule = new SimpleModule();
    simpleModule.addSerializer(LocalDateTime.class, new CustomLocalDateTimeSerializer());

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(simpleModule);
    
    return objectMapper.writeValueAsString(resDto);
  }

  private ResponseDto setBasicResponse(ResponseCode responseCode) {
    return ResponseDto.builder()
                      .responseCode(responseCode)
                      .build()
                      ;
  }

}
