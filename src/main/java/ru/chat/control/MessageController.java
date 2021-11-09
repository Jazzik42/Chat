package ru.chat.control;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.chat.model.Message;
import ru.chat.model.Person;
import ru.chat.model.Room;
import ru.chat.service.message.MessageService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/person/{personId}/room/{roomId}/messages")
public class MessageController {

    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/")
    public List<Message> findByRoomId(@PathVariable int roomId) {
        return StreamSupport.stream(messageService.findAllByRoomId(roomId).spliterator(),
                        false)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> findById(@PathVariable int id) {
        Optional<Message> mess = this.messageService.findById(id);
        return new ResponseEntity<>(
                mess.orElseGet(Message::new),
                mess.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @PostMapping("/")
    public ResponseEntity<Message> create(@PathVariable Person personId,
                                          @PathVariable Room roomId,
                                          @RequestBody Message message) {
        message.setPerson(personId);
        message.setRoom(roomId);
        message.setCreateDate(LocalDateTime.now());
        return new ResponseEntity<>(messageService.saveOrUpdate(message),
                HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@PathVariable Person personId,
                                       @PathVariable Room roomId,
                                       @RequestBody Message message) {
        message.setRoom(roomId);
        message.setPerson(personId);
        message.setCreateDate(LocalDateTime.now());
        messageService.saveOrUpdate(message);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        messageService.delete(id);
        return ResponseEntity.ok().build();
    }
}
