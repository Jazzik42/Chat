package ru.chat.service.person;

import ru.chat.model.Person;
import ru.chat.service.Service;

import java.util.Optional;

public interface PersonService extends Service<Person> {

    Optional<Person> findByName(String name);
}
