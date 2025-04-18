package br.com.eduardoAssisSilva.controllers;

import br.com.eduardoAssisSilva.model.Person;
import br.com.eduardoAssisSilva.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Person> findById(@PathVariable("id") Long id) {
        Person person = personService.findById(id);
        return ResponseEntity.ok(person);
    }

    @GetMapping
    public ResponseEntity<List<Person>> findAll() {
        List<Person> people = personService.findAll();
        return ResponseEntity.ok(people);
    }

    @PostMapping
    public ResponseEntity<Person> create(@RequestBody Person person){
        Person created = personService.create(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping
    public ResponseEntity<Person> update(@RequestBody Person person){
        Person updated = personService.update(person);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
