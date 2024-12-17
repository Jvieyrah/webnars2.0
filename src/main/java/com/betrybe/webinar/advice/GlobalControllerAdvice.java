package com.betrybe.webinar.advice;

import com.betrybe.webinar.service.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerAdvice {

  @ExceptionHandler
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String handleNotFound(NotFoundException exception) {
    return exception.getMessage();
  }
}
