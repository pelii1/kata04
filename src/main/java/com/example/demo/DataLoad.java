package com.example.demo;

public interface DataLoad<T> {

    T convertFromString(String row);

    boolean valid(T item);
}
