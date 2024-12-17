package com.betrybe.webinar.service;

import com.betrybe.webinar.entity.Webinar;
import com.betrybe.webinar.repository.WebinarRepository;
import com.betrybe.webinar.service.exception.WebinarNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebinarService {

  private final WebinarRepository webinarRepository;

  @Autowired
  public WebinarService(WebinarRepository webinarRepository) {
    this.webinarRepository = webinarRepository;
  }

  public Webinar create(Webinar webinar) {
    return webinarRepository.save(webinar);
  }

  public Webinar deleteById(Long id) throws WebinarNotFoundException {
    Webinar webinar = getById(id);

    webinarRepository.delete(webinar);

    return webinar;
  }

  public Webinar getById(Long id) throws WebinarNotFoundException {
    return webinarRepository.findById(id)
        .orElseThrow(WebinarNotFoundException::new);
  }

  public List<Webinar> getAll() {
    return webinarRepository.findAll();
  }
}
