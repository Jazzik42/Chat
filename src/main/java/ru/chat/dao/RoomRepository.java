package ru.chat.dao;

import org.springframework.data.repository.CrudRepository;
import ru.chat.model.Room;

import java.util.Optional;

public interface RoomRepository extends CrudRepository<Room, Integer> {

    Optional<Room> findByName(String name);
}
