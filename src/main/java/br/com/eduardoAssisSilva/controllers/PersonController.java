package br.com.eduardoAssisSilva.controllers;

import br.com.eduardoAssisSilva.controllers.docs.PersonControllerDocs;
import br.com.eduardoAssisSilva.data.dto.v1.PersonDTO;
import br.com.eduardoAssisSilva.services.PersonService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person/v1")
@Tag(name="People", description = "Endpoints for Managing People")
public class PersonController implements PersonControllerDocs {

    @Autowired
    private PersonService personService;


    @Override
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE})
    public ResponseEntity<PersonDTO> findById(@PathVariable("id") Long id) {
        PersonDTO person = personService.findById(id);
        return ResponseEntity.ok(person);
    }

    @Override
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE})
    public ResponseEntity<List<PersonDTO>> findAll() {
        List<PersonDTO> people = personService.findAll();
        return ResponseEntity.ok(people);
    }

    @Override
    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE})
    public ResponseEntity<PersonDTO> create(@RequestBody PersonDTO person) {
        PersonDTO created = personService.create(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Override
    @PutMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE})
    public ResponseEntity<PersonDTO> update(@RequestBody PersonDTO person) {
        PersonDTO updated = personService.update(person);
        return ResponseEntity.ok(updated);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
