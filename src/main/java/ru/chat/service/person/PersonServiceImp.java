package ru.chat.service.person;

import org.springframework.stereotype.Service;
import ru.chat.dao.PersonRepository;
import ru.chat.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PersonServiceImp implements PersonService {
    private PersonRepository personRep;

    public PersonServiceImp(PersonRepository personRep) {
        this.personRep = personRep;
    }

    @Override
    public Optional<Person> findByName(String name) {
        return personRep.findByName(name);
    }

    @Override
    public Optional<Person> findById(int id) {
        return personRep.findById(id);
    }

    @Override
    public List<Person> findAll() {
        return StreamSupport.stream(personRep.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(int id) {
        personRep.deleteById(id);
    }

    @Override
    public Person saveOrUpdate(Person person) {
        return personRep.save(person);
    }
}
