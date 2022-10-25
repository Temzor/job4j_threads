package ru.job4j.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) throws OptimisticException {
        return memory.computeIfPresent(model.getId(), (id, data) -> {
            if (model.getVersion() != data.getVersion()) {
                throw new OptimisticException("Versions are not equal");
            }
            Base newBase = new Base(id, data.getVersion() + 1);
            newBase.setName(model.getName());
            return newBase;
        }) != null;
    }

    public void delete(Base model) {
        memory.remove(model.getId(), model);
    }
}
