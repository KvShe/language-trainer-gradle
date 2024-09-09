package ru.languagetrainer.data_bases;

import java.util.List;

public interface DataBase<T> {
    List<T> getAll();

    void add(T object);
}
