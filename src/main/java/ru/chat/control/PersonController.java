package ru.chat.control;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.chat.model.Person;
import ru.chat.service.person.PersonService;

import javax.validation.Valid;
import java.util.List;
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
    public List<Person> findAll() {
        List<Person> list = StreamSupport.stream(personService.findAll()
                        .spliterator(), false)
                .collect(Collectors.toList());
        if (list.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Persons is not found.");
        }
        return list;
    }

    @GetMapping("/{personId}")
    public Person findById(@PathVariable int personId) {
        return personService.findById(personId).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person is not found"));
    }

    @GetMapping("/byName/{name}")
    public Person findByName(@PathVariable String name) {
        return personService.findByName(name).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person is not found"));
    }

    @PostMapping("/")
    public ResponseEntity<Person> save(@Valid @RequestBody Person person) {
        return new ResponseEntity<>(personService.saveOrUpdate(person),
                HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@Valid @RequestBody Person person) {
        personService.saveOrUpdate(person);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{personId}")
    public ResponseEntity<Void> delete(@PathVariable int personId) {
        personService.delete(personId);
        return ResponseEntity.ok().build();
    }
}
