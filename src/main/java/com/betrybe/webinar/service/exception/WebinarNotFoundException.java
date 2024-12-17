package com.betrybe.webinar.service.exception;

public class WebinarNotFoundException extends NotFoundException {

  public WebinarNotFoundException() {
    super("Evento não encontrado!");
  }
}
