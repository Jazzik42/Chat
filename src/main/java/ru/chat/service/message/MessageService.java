package ru.chat.service.message;

import ru.chat.model.Message;
import ru.chat.service.Service;

import java.util.List;

public interface MessageService extends Service<Message> {

    List<Message> findAllByRoomId(int id);

    List<Message> findAllByPersonId(int id);
}
