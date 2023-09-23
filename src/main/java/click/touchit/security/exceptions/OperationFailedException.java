package click.touchit.security.exceptions;

public class OperationFailedException extends RuntimeException {
  public OperationFailedException(String message){
    super(message);
  }
}
