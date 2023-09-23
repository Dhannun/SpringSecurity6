package click.touchit.security.universal;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@ToString
@JsonInclude(NON_NULL)
public class ApiResponse {

  private String message;
  private Object body;

  public ApiResponse(String message) {
    this.message = message;
  }

  public ApiResponse(String message, Object body) {
    this.message = message;
    this.body = body;
  }
}
