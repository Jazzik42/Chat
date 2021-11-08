package ru.chat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "person", orphanRemoval = true)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private List<Message> messages = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "person_room",
            joinColumns = { @JoinColumn(name = "person_id",
                    nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "room_id",
                    nullable = false, updatable = false) })
    private Set<Room> rooms = new HashSet<>();

    @ManyToOne
    private Role role;
}
