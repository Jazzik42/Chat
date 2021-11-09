package ru.chat.control;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.chat.model.Person;
import ru.chat.service.person.PersonService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Person>> findAll() {
        List<Person> list = StreamSupport.stream(personService.findAll()
                        .spliterator(), false)
                .collect(Collectors.toList());
        return new ResponseEntity<>(list,
                list.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @GetMapping("/byRoom/{id}")
    public ResponseEntity<List<Person>> findAllByRoom(@PathVariable int id) {
        List<Person> list = StreamSupport.stream(personService.findAll()
                        .spliterator(), false)
                .collect(Collectors.toList());
        return new ResponseEntity<>(list,
                list.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @GetMapping("/{personId}")
    public ResponseEntity<Person> findById(@PathVariable int personId) {
        Optional<Person> person = personService.findById(personId);
        return new ResponseEntity<>(person.orElseGet(Person::new),
                person.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/byName/{name}")
    public ResponseEntity<Person> findByName(@PathVariable String name) {
        Optional<Person> person = personService.findByName(name);
        return new ResponseEntity<>(person.orElseGet(Person::new),
                person.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping("/")
    public ResponseEntity<Person> save(@RequestBody Person person) {
        return new ResponseEntity<>(personService.saveOrUpdate(person),
                HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Person person) {
        personService.saveOrUpdate(person);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{personId}")
    public ResponseEntity<Void> delete(@PathVariable int personId) {
        personService.delete(personId);
        return ResponseEntity.ok().build();
    }
}
