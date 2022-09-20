package it.euris.ires.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(RecordNotFoundException.class)
  public final ResponseEntity<ErrorResponse> handleUserNotFoundException(
      RecordNotFoundException ex, WebRequest request) {
    List<String> details = new ArrayList<>();
    details.add(ex.getLocalizedMessage());
    String errorMesssage = "INCORRECT_REQUEST";
    ErrorResponse error = new ErrorResponse(errorMesssage, details);
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }
}
