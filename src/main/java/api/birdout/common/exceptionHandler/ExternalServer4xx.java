package api.birdout.common.exceptionHandler;

public class ExternalServer4xx extends RuntimeException {
  
  public ExternalServer4xx(String name) {
    super(name);
  }

}
