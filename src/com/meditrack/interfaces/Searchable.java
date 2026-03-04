package com.meditrack.interfaces;

import java.util.List;

public interface Searchable<T> {
    T findById(String id);
    List<T> searchByName(String name);

    default boolean isValidSearchQuery(String query) {
        return query != null && !query.trim().isEmpty();
    }
}