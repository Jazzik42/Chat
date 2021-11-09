package ru.chat.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String text;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false, updatable = false)
    private Person person;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false, updatable = false)
    private Room room;
}
