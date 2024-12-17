package com.betrybe.webinar.controller;

import com.betrybe.webinar.controller.dto.WebinarCreationDto;
import com.betrybe.webinar.controller.dto.WebinarDto;
import com.betrybe.webinar.entity.Webinar;
import com.betrybe.webinar.service.WebinarService;
import com.betrybe.webinar.service.exception.WebinarNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webinars")
public class WebinarController {

  private final WebinarService webinarService;

  @Autowired
  public WebinarController(WebinarService webinarService) {
    this.webinarService = webinarService;
  }


  @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER') or hasAuthority('VIEWER')")
  @GetMapping
  public List<WebinarDto> getAllWebinars() {
    return webinarService.getAll().stream()
        .map(WebinarDto::fromEntity)
        .collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  public WebinarDto getWebinarById(@PathVariable Long id) throws WebinarNotFoundException {
    return WebinarDto.fromEntity(
        webinarService.getById(id)
    );
  }

  @PreAuthorize("@personService.loadUserByUsername(authentication.principal.username).age > 18")

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public WebinarDto createWebinar(@RequestBody WebinarCreationDto webinarDto) {
    Webinar savedWebinar = webinarService.create(
        webinarDto.toEntity()
    );

    return WebinarDto.fromEntity(savedWebinar);
  }

  @DeleteMapping("/{id}")
  public WebinarDto deleteWebinar(@PathVariable Long id) throws WebinarNotFoundException {
    return WebinarDto.fromEntity(
        webinarService.deleteById(id)
    );
  }
}
