package ru.chat.service.room;

import org.springframework.stereotype.Service;
import ru.chat.dao.RoomRepository;
import ru.chat.model.Room;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class  RoomServiceImp implements RoomService {

    private RoomRepository roomRep;

    public RoomServiceImp(RoomRepository roomRep) {
        this.roomRep = roomRep;
    }

    @Override
    public Optional<Room> findById(int id) {
        return roomRep.findById(id);
    }

    @Override
    public List findAll() {
        return StreamSupport.stream(roomRep.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(int id) {
        roomRep.deleteById(id);
    }

    @Override
    public Room saveOrUpdate(Room room) {
        return roomRep.save(room);
    }

    @Override
    public Optional<Room> findByName(String name) {
        return roomRep.findByName(name);
    }
}
