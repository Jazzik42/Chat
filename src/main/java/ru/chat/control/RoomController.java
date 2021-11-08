package ru.chat.control;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.chat.model.Room;
import ru.chat.repository.RoomRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private RoomRepository roomRep;

    public RoomController(RoomRepository roomRep) {
        this.roomRep = roomRep;
    }

    @GetMapping("/")
    public ResponseEntity<List<Room>> findAll() {
        List<Room> list = StreamSupport.stream(roomRep.findAll().spliterator(),
                false).collect(Collectors.toList());
        return new ResponseEntity<>(list,
                list.isEmpty() ? HttpStatus.NOT_FOUND
                        : HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> findById(@PathVariable int id) {
        Optional<Room> room = roomRep.findById(id);
        return new ResponseEntity<>(room.orElseGet(Room::new),
                room.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/byName/{name}")
    public ResponseEntity<Room> findByName(@PathVariable String name) {
        Optional<Room> room = roomRep.findByName(name);
        return new ResponseEntity<>(room.orElseGet(Room::new),
                room.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping("/")
    public ResponseEntity<Room> save(@RequestBody Room room) {
        return new ResponseEntity<>(roomRep.save(room),
                HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Room> update(@RequestBody Room room) {
        return new ResponseEntity<>(roomRep.save(room),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        roomRep.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
