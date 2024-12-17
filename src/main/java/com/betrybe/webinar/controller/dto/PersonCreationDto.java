package com.betrybe.webinar.controller.dto;

import com.betrybe.webinar.entity.Person;

public record PersonCreationDto(
    String fullname,
    String email,
    String username,
    String password,
    String role,
    String age


) {

  public Person toEntity() {
    return new Person(null, fullname, email, username, password, role, Integer.parseInt(age));
  }
}
