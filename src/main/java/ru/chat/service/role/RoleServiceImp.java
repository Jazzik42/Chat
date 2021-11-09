package ru.chat.service.role;

import org.springframework.stereotype.Service;
import ru.chat.dao.RoleRepository;
import ru.chat.model.Role;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RoleServiceImp implements RoleService {
    private RoleRepository roleRep;

    public RoleServiceImp(RoleRepository roleRep) {
        this.roleRep = roleRep;
    }

    @Override
    public Optional<Role> findById(int id) {
        return roleRep.findById(id);
    }

    @Override
    public List<Role> findAll() {
        return StreamSupport.stream(roleRep.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(int id) {
        roleRep.deleteById(id);
    }

    @Override
    public Role saveOrUpdate(Role role) {
        return roleRep.save(role);
    }
}
