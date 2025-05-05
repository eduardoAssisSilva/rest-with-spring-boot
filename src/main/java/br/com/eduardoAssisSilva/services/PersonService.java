package br.com.eduardoAssisSilva.services;

import br.com.eduardoAssisSilva.controllers.PersonController;
import br.com.eduardoAssisSilva.data.dto.v1.PersonDTO;
import br.com.eduardoAssisSilva.exception.ResourceNotFoundException;
import static br.com.eduardoAssisSilva.mapper.ObjectMapper.parseListObjects;
import static br.com.eduardoAssisSilva.mapper.ObjectMapper.parseObject;

import br.com.eduardoAssisSilva.model.Person;
import br.com.eduardoAssisSilva.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

@Service
public class PersonService {

    private Logger logger = LoggerFactory.getLogger(PersonService.class.getName());
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public  List<PersonDTO> findAll() {
        logger.info("Finding all persons!");
        var dtos = parseListObjects(personRepository.findAll(), PersonDTO.class);

        dtos.forEach(this::addHateoasLinks);
        return dtos;
    }

    public PersonDTO  findById(Long id) {
        logger.info("Finding one Person!");

        var entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No record found for this ID!"));

        var dto = parseObject(entity, PersonDTO.class);

        addHateoasLinks(dto);
        return dto;
    }

    public PersonDTO  create(PersonDTO person) {
        logger.info("Creating one Person!");

        var entity = parseObject(person, Person.class);

        var dto = parseObject(personRepository.save(entity), PersonDTO.class);

        addHateoasLinks(dto);
        return dto;

    }

    public PersonDTO update(PersonDTO personDTO) {
        logger.info("Updating one Person!");

        Person entity = personRepository.findById(personDTO.getId()).orElseThrow(()-> new ResourceNotFoundException("No record found for this ID!"));
        entity.setFirstName(personDTO.getFirstName());
        entity.setLastName(personDTO.getLastName());
        entity.setAddress(personDTO.getAddress());
        entity.setGender(personDTO.getGender());

        var dto = parseObject(personRepository.save(entity), PersonDTO.class);

        addHateoasLinks(dto);
        return dto;
    }

    public void delete (Long id) {
        logger.info("Deleting person.");
        Person person = personRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No record found for this ID!"));
        personRepository.delete(person);
    }

    private void addHateoasLinks(PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }
}