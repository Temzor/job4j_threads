package ru.job4j.atomicity;

public class CacheSecond {
    private static CacheSecond cacheSecond;

    public static synchronized CacheSecond instOf() {
        if (cacheSecond == null) {
            cacheSecond = new CacheSecond();
        }
        return cacheSecond;
    }
}