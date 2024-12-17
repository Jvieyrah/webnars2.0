package com.betrybe.webinar.controller.dto;

import com.betrybe.webinar.entity.Webinar;
import java.time.LocalDateTime;

public record WebinarCreationDto(
    String title,
    String url,
    LocalDateTime dateTime) {

  public Webinar toEntity() {
    return new Webinar(null, title, url, dateTime);
  }
}
