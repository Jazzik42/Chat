package ru.chat.control;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.chat.model.Message;
import ru.chat.repository.MessageRepository;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private MessageRepository messageRep;

    public MessageController(MessageRepository messageRep) {
        this.messageRep = messageRep;
    }

    @GetMapping("/")
    public List<Message> findAll() {
        return StreamSupport.stream(messageRep.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @GetMapping("/byRoom/{id}")
    public List<Message> findByRoomId(@PathVariable int id) {
        return StreamSupport.stream(messageRep.findAllByRoomId(id).spliterator(), false)
                .collect(Collectors.toList());
    }

    @GetMapping("/byPerson/{id}")
    public List<Message> findByPersonId(@PathVariable int id) {
        return StreamSupport.stream(messageRep.findAllByPersonId(id).spliterator(), false)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> findById(@PathVariable int id) {
        Optional<Message> mess = this.messageRep.findById(id);
        return new ResponseEntity<>(
                mess.orElseGet(Message::new),
                mess.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @PostMapping("/")
    public ResponseEntity<Message> create(@RequestBody Message message) {
        return new ResponseEntity<>(messageRep.save(message), HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Message message) {
        messageRep.save(message);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        messageRep.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
