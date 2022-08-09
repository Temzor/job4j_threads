package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Wget implements Runnable {
    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        String outputFile = url.split("/")[url.split("/").length - 1];
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("out →" + outputFile)) {
            byte[] dataBuffer = new byte[speed];
            int bytesRead;
            long currentTime = 0;
            while ((bytesRead = in.read(dataBuffer, 0, speed)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                if (currentTime < 1000) {
                    try {
                        Thread.sleep(1000 - currentTime);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        if (!checkArgs(args)) {
            return;
        }
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        System.out.println("Download: " + url);
        System.out.println("Download speed: " + speed + "kByte/sec");
        Thread thread = new Thread(new Wget(url, speed));
        long start = System.currentTimeMillis();
        thread.start();
        thread.join();
        System.out.println("Download: ok. time="
                + (System.currentTimeMillis() - start)
                + " ms.");
    }

    private static boolean checkArgs(String[] args) {
        boolean result = args.length >= 3;
        if (!result) {
            System.out.println("""
                    Usage correct format for run *.jar -->
                     Wget <url> <speed>, where:
                     <url>          - internet url;
                     <speed>        - download speed kBytes pes 1 second.""");
        }
        return result;
    }
}