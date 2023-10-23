package emerick.igor.javatodolist.shared.infra.errors.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import emerick.igor.javatodolist.shared.infra.errors.HttpError;

@ControllerAdvice
public class ErrorHandler {
  @ExceptionHandler(HttpError.class)
  public ResponseEntity<String> httpErrorHandler(HttpError error) {
    return ResponseEntity.status(error.getStatus()).body(error.getMessage());
  }
}
