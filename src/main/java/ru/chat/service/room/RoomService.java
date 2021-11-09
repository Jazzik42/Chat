package ru.chat.service.room;

import ru.chat.model.Room;
import ru.chat.service.Service;

import java.util.Optional;

public interface RoomService extends Service<Room> {

    Optional<Room> findByName(String name);
}
