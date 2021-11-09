package ru.chat.service.message;

import org.springframework.stereotype.Service;
import ru.chat.dao.MessageRepository;
import ru.chat.model.Message;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MessageServiceImp implements MessageService {

    private MessageRepository messageRep;

    public MessageServiceImp(MessageRepository messageRep) {
        this.messageRep = messageRep;
    }

    @Override
    public Optional<Message> findById(int id) {
        return messageRep.findById(id);
    }

    @Override
    public List<Message> findAll() {
        return StreamSupport.stream(messageRep.findAll().spliterator(),
                false).collect(Collectors.toList());
    }

    @Override
    public void delete(int id) {
        messageRep.deleteById(id);
    }

    @Override
    public Message saveOrUpdate(Message message) {
       return messageRep.save(message);
    }

    @Override
    public List<Message> findAllByRoomId(int id) {
        return messageRep.findAllByRoomId(id);
    }

    @Override
    public List<Message> findAllByPersonId(int id) {
        return messageRep.findAllByPersonId(id);
    }
}
