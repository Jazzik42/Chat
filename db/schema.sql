drop table role;
drop table person;
drop table room;
drop table person_room;
drop table message;

create table role
(
    id   serial primary key,
    name varchar(200) not null
);

create table person
(
    id       serial primary key,
    name     varchar(200) not null,
    password varchar(200) not null,
    role_id  int references role (id)
);

create table room
(
    id   serial primary key,
    name varchar(200) not null unique
);

create table person_room
(
    person_id int references person (id) not null,
    room_id   int references room (id)   not null
);

create table message
(
    id        serial primary key,
    text      text                       not null,
    person_id int references person (id) not null,
    room_id   int references room (id)   not null,
    created   timestamp
);