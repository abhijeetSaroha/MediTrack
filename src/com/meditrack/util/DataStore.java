package com.meditrack.util;

import com.meditrack.entity.MedicalEntity;
import com.meditrack.interfaces.Searchable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataStore<T extends MedicalEntity> implements Searchable<T> {
    private final Map<String, T> store = new HashMap<>();

    public void save(T entity) {
        if (entity != null && entity.getId() != null) {
            store.put(entity.getId(), entity);
        }
    }

    public void delete(String id) {
        store.remove(id);
    }

    public List<T> getAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public T findById(String id) {
        if (!isValidSearchQuery(id)) return null;
        return store.get(id);
    }

    // Default search (can be overridden or handled via Streams later)
    @Override
    public List<T> searchByName(String name) {
        return new ArrayList<>(); // Basic implementation, specialized in Services
    }
}