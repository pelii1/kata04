package com.example.demo;

public interface DatFile<T> {
    void load();

    T calculate(Calculate<T> operation);

}
