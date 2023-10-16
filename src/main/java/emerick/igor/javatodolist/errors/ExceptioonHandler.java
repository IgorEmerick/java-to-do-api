package emerick.igor.javatodolist.errors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptioonHandler {
  @ExceptionHandler(HttpError.class)
  public ResponseEntity<String> httpErrorHandler(HttpError error) {
    return ResponseEntity.status(error.getStatus()).body(error.getMessage());
  }
}
