package ru.chat.dao;

import org.springframework.data.repository.CrudRepository;
import ru.chat.model.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
}
