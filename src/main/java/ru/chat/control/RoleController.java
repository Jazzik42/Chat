package ru.chat.control;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.chat.model.Role;
import ru.chat.service.role.RoleService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/")
    public List<Role> findAll() {
        List<Role> list = StreamSupport.stream(roleService.findAll().spliterator(),
                        false)
                .collect(Collectors.toList());
        if (list.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Roles is not found");
        }
        return list;
    }

    @GetMapping("/{id}")
    public Role findById(@PathVariable int id) {
        return roleService.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role is not found"));
    }

    @PostMapping("/")
    public ResponseEntity<Role> save(@RequestBody Role role) {
        return new ResponseEntity<>(roleService.saveOrUpdate(role),
                HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Role role) {
        roleService.saveOrUpdate(role);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        roleService.delete(id);
        return ResponseEntity.ok().build();
    }
}
