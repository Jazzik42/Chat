package ru.chat.dao;

import org.springframework.data.repository.CrudRepository;
import ru.chat.model.Person;

import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Integer> {

    Optional<Person> findByName(String name);
}
