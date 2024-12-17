package com.betrybe.webinar.service.exception;

public class PersonNotFoundException extends NotFoundException {

  public PersonNotFoundException() {
    super("Pessoa não encontrada!");
  }
}
