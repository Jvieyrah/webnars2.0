package com.betrybe.webinar.controller;

import com.betrybe.webinar.controller.dto.PersonCreationDto;
import com.betrybe.webinar.controller.dto.PersonDto;
import com.betrybe.webinar.entity.Person;
import com.betrybe.webinar.service.PersonService;
import com.betrybe.webinar.service.exception.PersonNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
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
@RequestMapping("/persons")
public class PersonController {

  private final PersonService personService;

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @GetMapping
  @PreAuthorize("hasAuthority('ADMIN') or @personService.loadUserByUsername(authentication.principal.username).email matches '^[a-zA-Z0-9._-]+@betrybe[.]com$'")
  public List<PersonDto> getAllPersons() {
    return personService.getAll().stream()
        .map(PersonDto::fromEntity)
        .collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  public PersonDto getPersonById(@PathVariable Long id) throws PersonNotFoundException {
    return PersonDto.fromEntity(
        personService.getById(id)
    );
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PersonDto createPerson(@RequestBody PersonCreationDto personDto) {
    Person savedPerson = personService.create(
        personDto.toEntity()
    );

    return PersonDto.fromEntity(savedPerson);
  }

  @DeleteMapping("/{id}")
  public PersonDto deletePerson(@PathVariable Long id) throws PersonNotFoundException {
    return PersonDto.fromEntity(
        personService.deleteById(id)
    );
  }
}
