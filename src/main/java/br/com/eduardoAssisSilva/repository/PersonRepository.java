package br.com.eduardoAssisSilva.repository;

import br.com.eduardoAssisSilva.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
