package api.birdout.common.exceptionHandler;

public class ExternalServer5xx extends RuntimeException {
  
  public ExternalServer5xx(String name) {
    super(name);
  }

}
