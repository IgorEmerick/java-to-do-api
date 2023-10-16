package emerick.igor.javatodolist.errors;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class HttpError extends Throwable {
  private Integer status;
  private String message;

  public HttpError(Integer status, String message) {
    this.status = status;
    this.message = message;
  }
}
