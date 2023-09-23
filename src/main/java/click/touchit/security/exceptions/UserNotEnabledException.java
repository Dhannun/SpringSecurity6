package click.touchit.security.exceptions;

public class UserNotEnabledException extends RuntimeException{
  public UserNotEnabledException(String message) {
    super(message);
  }
}
