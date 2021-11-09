package ru.chat.control;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.chat.model.Room;
import ru.chat.service.room.RoomService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Room>> findAll() {
        List<Room> list = StreamSupport.stream(roomService.findAll()
                        .spliterator(),
                false).collect(Collectors.toList());
        return new ResponseEntity<>(list,
                list.isEmpty() ? HttpStatus.NOT_FOUND
                        : HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> findById(@PathVariable int id) {
        Optional<Room> room = roomService.findById(id);
        return new ResponseEntity<>(room.orElseGet(Room::new),
                room.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/byName/{name}")
    public ResponseEntity<Room> findByName(@PathVariable String name) {
        Optional<Room> room = roomService.findByName(name);
        return new ResponseEntity<>(room.orElseGet(Room::new),
                room.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping("/")
    public ResponseEntity<Room> save(@RequestBody Room room) {
        return new ResponseEntity<>(roomService.saveOrUpdate(room),
                HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Room> update(@RequestBody Room room) {
        return new ResponseEntity<>(roomService.saveOrUpdate(room),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        roomService.delete(id);
        return ResponseEntity.ok().build();
    }
}
