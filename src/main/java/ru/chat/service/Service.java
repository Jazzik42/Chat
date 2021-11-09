package ru.chat.service;

import java.util.List;
import java.util.Optional;

public interface Service<T> {
    Optional<T> findById(int id);

    List<T> findAll();

    void delete(int id);

    T saveOrUpdate(T t);
}
