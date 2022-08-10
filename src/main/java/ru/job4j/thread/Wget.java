package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Wget implements Runnable {
    private final String url;
    private final String file;
    private final int speed;

    public Wget(String url, String file, int speed) {
        this.url = url;
        this.file = file;
        this.speed = speed;
    }

    @Override
    public void run() {
        /* Скачать файл*/
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream out = new FileOutputStream(file)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            long bytesWrite = 0;
            long startTime = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                out.write(dataBuffer, 0, bytesRead); /*bytesRead - Это кол-во прочтенных за раз байт*/
                bytesWrite += bytesRead;
                if (bytesWrite >= speed) {
                    long deltaTime = System.currentTimeMillis() - startTime;
                    if (deltaTime < 1000) {
                        Thread.sleep(1000 - deltaTime);
                    }
                    startTime = System.currentTimeMillis();
                    bytesWrite = 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public static void validateArgs(String[] args) {
        if (args.length < 3) {
            throw new IllegalArgumentException("""
                    Invalid arguments, please correct parameters: \s
                     args[0] -> internet url
                     args[1] -> download speed Bytes pes 1 second
                     args[2] -> name file"""
            );
        }
    }

    public static void main(String[] args) throws InterruptedException {
        validateArgs(args);
        String url = args[0];
        System.out.println("Download: " + url);
        String file = args[1];
        System.out.println("File name " + file);
        int speed = Integer.parseInt(args[2]);
        System.out.println("Download speed: " + speed + "Byte/sec");
        Thread thread = new Thread(new Wget(url, file, speed));
        long start = System.currentTimeMillis();
        thread.start();
        thread.join();
        System.out.println("Download: ok. time="
                + (System.currentTimeMillis() - start)
                + " ms.");
    }
}