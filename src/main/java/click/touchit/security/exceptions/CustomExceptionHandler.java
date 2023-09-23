package click.touchit.security.exceptions;

import click.touchit.security.universal.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class CustomExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public final ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException exception) {
    return new ResponseEntity<>(new ApiResponse(exception.getMessage()), NOT_FOUND);
  }

  @ExceptionHandler(InvalidResourceException.class)
  public final ResponseEntity<ApiResponse> handleInvalidResourceException(InvalidResourceException exception) {
    return new ResponseEntity<>(new ApiResponse(exception.getMessage()), BAD_REQUEST);
  }

  @ExceptionHandler(OperationFailedException.class)
  public final ResponseEntity<ApiResponse> handleRegistrationFailedException(OperationFailedException exception) {
    return new ResponseEntity<>(new ApiResponse(exception.getMessage()), EXPECTATION_FAILED);
  }

  @ExceptionHandler(UserNotEnabledException.class)
  public final ResponseEntity<ApiResponse> handleUserNotEnabledException(UserNotEnabledException exception) {
    return new ResponseEntity<>(new ApiResponse(exception.getMessage()), FORBIDDEN);
  }
}
