package com.meditrack.util;

import com.meditrack.exception.InvalidDataException;

public class Validator {

    public static void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidDataException("Name cannot be empty.");
        }
    }

    public static void validateAge(int age) {
        if (age <= 0 || age > 150) {
            throw new InvalidDataException("Invalid age provided: " + age);
        }
    }

    public static void validateContact(String contact) {
        if (contact == null || !contact.matches("\\d{10}")) {
            throw new InvalidDataException("Contact must be exactly 10 digits.");
        }
    }
}