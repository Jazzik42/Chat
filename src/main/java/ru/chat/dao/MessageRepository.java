package ru.chat.dao;

import org.springframework.data.repository.CrudRepository;
import ru.chat.model.Message;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Integer> {

    List<Message> findAllByRoomId(int id);

    List<Message> findAllByPersonId(int id);
}
