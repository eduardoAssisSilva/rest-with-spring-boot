package br.com.eduardoAssisSilva.controllers;

import br.com.eduardoAssisSilva.data.dto.v1.PersonDTO;
import br.com.eduardoAssisSilva.data.dto.v2.PersonDTOV2;
import br.com.eduardoAssisSilva.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/v1/{id}")
    public ResponseEntity<PersonDTO> findById(@PathVariable("id") Long id) {
        PersonDTO person = personService.findById(id, PersonDTO.class);
        return ResponseEntity.ok(person);
    }

    @GetMapping(value = "/v2/{id}")
    public ResponseEntity<PersonDTOV2> findByIdV2(@PathVariable("id") Long id) {
        PersonDTOV2 person = personService.findById(id, PersonDTOV2.class);
        return ResponseEntity.ok(person);
    }

    @GetMapping(value = "/v1")
    public ResponseEntity<List<PersonDTO>> findAll() {
        List<PersonDTO> people = personService.findAll(PersonDTO.class);
        return ResponseEntity.ok(people);
    }
    @GetMapping(value = "/v2")
    public ResponseEntity<List<PersonDTOV2>> findAllV2() {
        List<PersonDTOV2> people = personService.findAll(PersonDTOV2.class);
        return ResponseEntity.ok(people);
    }

    @PostMapping(value ="/v1")
    public ResponseEntity<PersonDTO> create(@RequestBody PersonDTO person){
        PersonDTO created = personService.create(person, PersonDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping(value ="/v2")
    public ResponseEntity<PersonDTOV2> create(@RequestBody PersonDTOV2 person){
        PersonDTOV2 created = personService.create(person, PersonDTOV2.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping(value = "/v1")
    public ResponseEntity<PersonDTO> update(@RequestBody PersonDTO person){
        PersonDTO updated = personService.update(person);
        return ResponseEntity.ok(updated);
    }

    @PutMapping(value ="/v2")
    public ResponseEntity<PersonDTOV2> update(@RequestBody PersonDTOV2 person){
        PersonDTOV2 updated = personService.update(person);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping(value = "/v1/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
