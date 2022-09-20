package ru.job4j.io;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

public class SaveFile {
    private final File file;

    public SaveFile(File file) {
        this.file = file;
    }

    private synchronized void saveContent(String content) throws IOException {
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
            for (int i = 0; i < content.length(); i += 1) {
                out.write(content.charAt(i));
            }

        } catch (IOException e) {
            throw new RuntimeException(LocalDateTime.now() + " " + e + " RuntimeException");
        }
    }
}