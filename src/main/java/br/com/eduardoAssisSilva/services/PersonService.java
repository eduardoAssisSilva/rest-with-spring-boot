package br.com.eduardoAssisSilva.services;

import br.com.eduardoAssisSilva.data.dto.v1.PersonDTO;
import br.com.eduardoAssisSilva.data.dto.v2.PersonDTOV2;
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

    public <D> List<D> findAll(Class<D> dtoClass) {
        logger.info("Finding all persons!");
        return parseListObjects(personRepository.findAll(), dtoClass);
    }

    public <D> D findById(Long id, Class<D> dtoClass) {
        logger.info("Finding one Person!");

        var entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No record found for this ID!"));

        return parseObject(entity, dtoClass);
    }

    public <D> D create(D person, Class<D> dtoClass) {
        logger.info("Creating one Person!");

        var entity = parseObject(person, Person.class);
        return parseObject(personRepository.save(entity), dtoClass);
    }

    public PersonDTO update(PersonDTO personDTO) {
        logger.info("Updating one Person!");
        Person entity = personRepository.findById(personDTO.getId()).orElseThrow(()-> new ResourceNotFoundException("No record found for this ID!"));
        entity.setFirstName(personDTO.getFirstName());
        entity.setLastName(personDTO.getLastName());
        entity.setAddress(personDTO.getAddress());
        entity.setGender(personDTO.getGender());

        return parseObject(personRepository.save(entity), PersonDTO.class);
    }

    public PersonDTOV2 update(PersonDTOV2 personDTO) {
        logger.info("Updating one Person!");

        Person entity = personRepository.findById(personDTO.getId()).orElseThrow(()-> new ResourceNotFoundException("No record found for this ID!"));
        entity.setFirstName(personDTO.getFirstName());
        entity.setLastName(personDTO.getLastName());
        entity.setBirthday(personDTO.getBirthday());
        entity.setAddress(personDTO.getAddress());
        entity.setGender(personDTO.getGender());

        return parseObject(personRepository.save(entity), PersonDTOV2.class);
    }

    public void delete (Long id) {
        logger.info("Deleting person.");
        Person person = personRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No record found for this ID!"));
        personRepository.delete(person);
    }
}