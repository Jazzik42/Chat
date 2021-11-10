package ru.chat.control;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.chat.model.Person;
import ru.chat.service.person.PersonService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private BCryptPasswordEncoder encoder;
    private PersonService users;

    public UserController(PersonService users,
                          BCryptPasswordEncoder encoder) {
        this.users = users;
        this.encoder = encoder;
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody Person person) {
        person.setPassword(encoder.encode(person.getPassword()));
        users.saveOrUpdate(person);
    }

    @GetMapping("/all")
    public List<Person> findAll() {
        return users.findAll();
    }
}