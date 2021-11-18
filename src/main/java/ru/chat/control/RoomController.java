package ru.chat.control;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.chat.model.Room;
import ru.chat.service.room.RoomService;

import javax.validation.Valid;
import java.util.List;
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
    public List<Room> findAll() {
        List<Room> list = StreamSupport.stream(roomService.findAll()
                        .spliterator(),
                false).collect(Collectors.toList());
        if (list.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Rooms is not found");
        }
        return list;
    }

    @GetMapping("/{id}")
    public Room findById(@PathVariable int id) {
        return roomService.findById(id).orElseThrow(()
        -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Room is not found"));
    }

    @GetMapping("/byName/{name}")
    public Room findByName(@PathVariable String name) {
        return  roomService.findByName(name).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Role is not found."));
    }

    @PostMapping("/")
    public ResponseEntity<Room> save(@Valid @RequestBody Room room) {
        return new ResponseEntity<>(roomService.saveOrUpdate(room),
                HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Room> update(@Valid @RequestBody Room room) {
        return new ResponseEntity<>(roomService.saveOrUpdate(room),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        roomService.delete(id);
        return ResponseEntity.ok().build();
    }
}
