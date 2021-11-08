package ru.chat.control;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.chat.model.Role;
import ru.chat.repository.RoleRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private RoleRepository roleRep;

    public RoleController(RoleRepository roleRep) {
        this.roleRep = roleRep;
    }

    @GetMapping("/")
    public ResponseEntity<List<Role>> findAll() {
        List<Role> list = StreamSupport.stream(roleRep.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return new ResponseEntity<>(list,
                list.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> findById(@PathVariable int id) {
        Optional<Role> role = roleRep.findById(id);
        return new ResponseEntity<>(role.orElseGet(Role::new),
                role.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping("/")
    public ResponseEntity<Role> save(@RequestBody Role role) {
        return new ResponseEntity<>(roleRep.save(role),
                HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Role role) {
        roleRep.save(role);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        roleRep.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
