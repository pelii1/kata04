package com.example.demo;

import lombok.Getter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Getter
public class FileOperate<T> {
    private Path filePath;
    private List<T> items;
    private Calculate<T> calculate;
    private DataLoad<T> dataLoad;

    public static <T> FileOperate<T> of(Path filePath, Calculate<T> calculate, DataLoad<T> dataLoad) {
        return new FileOperate<>(filePath, calculate, dataLoad);

    }

    public FileOperate(Path filePath, Calculate<T> calculate, DataLoad<T> dataLoad) {
        this.filePath = filePath;
        this.calculate = calculate;
        this.dataLoad = dataLoad;

        items = new ArrayList<>();
    }

    public void checkFile() {
        if (!Files.exists(filePath)) {
            throw new DatFileException("File open error. " + getFilePath().toString());
        }
    }

    public T calculate() {
        T result = null;

        for (T item : getItems()) {
            if (result == null) {
                result = item;
            } else {
                result = calculate.calculation(result, item);
            }
        }
        return result;

    }

    public void load() {
        checkFile();
        loadDataFromFile(dataLoad);
    }

    public void addItem(T item) {
        items.add(item);
    }

    protected String convertRow(String row) {
        return row.trim().replaceAll("[\\s]+", ";").replace("*", "");
    }

    private void loadDataFromFile(DataLoad<T> dataLoad) {
        try {
            try (Stream<String> lines = Files.lines(getFilePath())) {
                lines.forEach(row -> {
                    T item = dataLoad.convertFromString(convertRow(row));
                    if (dataLoad.valid(item)) {
                        addItem(item);
                    }
                });
            }
        } catch (Exception e) {
            throw new DatFileException("File load error", e);
        }
    }
}
