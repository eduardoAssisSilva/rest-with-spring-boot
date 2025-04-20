package br.com.eduardoAssisSilva.services;

import br.com.eduardoAssisSilva.data.dto.PersonDTO;
import br.com.eduardoAssisSilva.exception.ResourceNotFoundException;
import static br.com.eduardoAssisSilva.mapper.ObjectMapper.parseListObjects;
import static br.com.eduardoAssisSilva.mapper.ObjectMapper.parseObject;

import br.com.eduardoAssisSilva.model.Person;
import br.com.eduardoAssisSilva.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private Logger logger = LoggerFactory.getLogger(PersonService.class.getName());
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonDTO> findAll(){
        logger.info("Finding all persons!");
        return parseListObjects(personRepository.findAll(), PersonDTO.class);
    }

    public PersonDTO findById(Long id){
        logger.info("Finding one Person!");

        var entity =  personRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No record found for this ID!"));
        return parseObject(entity, PersonDTO.class);
    }

    public PersonDTO create(PersonDTO person) {
        logger.info("Creating one Person!");

        var entity = parseObject(person, Person.class);
        return parseObject(personRepository.save(entity), PersonDTO.class);
    }

    public PersonDTO update(PersonDTO person) {
        logger.info("Updating one Person!");
        Person entity = personRepository.findById(person.getId()).orElseThrow(()-> new ResourceNotFoundException("No record found for this ID!"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return parseObject(personRepository.save(entity), PersonDTO.class);
    }

    public void delete (Long id) {
        logger.info("Deleting person.");
        Person person = personRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No record found for this ID!"));
        personRepository.delete(person);
    }
}