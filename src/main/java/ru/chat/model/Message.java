package ru.chat.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
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
    @Size(min = 3, message = "text must be min 3 chars")
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
