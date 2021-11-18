package ru.chat.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "name is required field")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "room")
    private List<Message> messages = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "person_room",
            joinColumns = { @JoinColumn(name = "room_id",
                    nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "person_id",
                    nullable = false, updatable = false) })
    private Set<Person> persons = new HashSet<>();
}
