package ru.job4j.cache;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CacheTest {

    @Test
    public void whenAddThenTrue() {
        Cache cache = new Cache();
        assertThat(cache.add(new Base(0, 0))).isTrue();
    }

    @Test
    public void whenDoubleAddThenTrue() {
        Cache cache = new Cache();
        assertThat(cache.add(new Base(0, 0))).isTrue();
        assertThat(cache.add(new Base(0, 0))).isFalse();
    }

    @Test
    public void whenUpdateThenTrue() {
        Cache cache = new Cache();
        Base base = new Base(0, 0);
        cache.add(base);
        assertThat(cache.update(base)).isTrue();
    }

    @Test
    public void whenUpdateThenException() {
        assertThrows(OptimisticException.class,
                () -> {
                    Cache cache = new Cache();
                    Base base = new Base(0, 0);
                    cache.add(base);
                    cache.update(base);
                    cache.update(base);
                });
    }

    @Test
    public void whenDelete() {
        Cache cache = new Cache();
        Base base = new Base(0, 0);
        assertThat(cache.add(base)).isTrue();
        assertThat(cache.add(new Base(0, 0))).isFalse();
        cache.delete(base);
        assertThat(cache.add(new Base(0, 0))).isTrue();
    }
}