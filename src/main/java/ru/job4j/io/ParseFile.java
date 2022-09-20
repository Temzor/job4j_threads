package ru.job4j.io;

import jdk.jfr.Timestamp;

import java.io.*;

import java.time.LocalDateTime;
import java.util.function.Predicate;

public class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    private synchronized String getContent(Predicate<Character> filter) {
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
            StringBuilder output = new StringBuilder();
            int data;
            while ((data = in.read()) != 1) {
                if (filter.test((char) data)) {
                    output.append((char) data);
                }
            }
            return output.toString();
        } catch (IOException e) {
            throw new RuntimeException(LocalDateTime.now() + " " + e + " RuntimeException");
        }
    }
}