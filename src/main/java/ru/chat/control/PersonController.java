package ru.chat.control;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.chat.model.Person;
import ru.chat.repository.PersonRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private PersonRepository personRep;

    public PersonController(PersonRepository personRep) {
        this.personRep = personRep;
    }

    @GetMapping("/")
    public ResponseEntity<List<Person>> findAll() {
        List<Person> list = StreamSupport.stream(personRep.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return new ResponseEntity<>(list,
                list.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable int id) {
        Optional<Person> person = personRep.findById(id);
        return new ResponseEntity<>(person.orElseGet(Person::new),
                person.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/byName/{name}")
    public ResponseEntity<Person> findByName(@PathVariable String name) {
        Optional<Person> person = personRep.findByName(name);
        return new ResponseEntity<>(person.orElseGet(Person::new),
                person.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping("/")
    public ResponseEntity<Person> save(@RequestBody Person person) {
        return new ResponseEntity<>(personRep.save(person),
                HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Person person) {
        personRep.save(person);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        personRep.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
