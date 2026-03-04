package com.meditrack.test;

import com.meditrack.entity.Patient;
import com.meditrack.exception.InvalidDataException;
import com.meditrack.util.IdGenerator;
import com.meditrack.util.Validator;

public class TestRunner {

    public static void runTests() {
        System.out.println("--- Running Manual Tests ---");
        testValidation();
        testDeepCopyCloning();
        testSingletonIdGenerator();
        System.out.println("--- All Tests Completed ---\n");
    }

    private static void testValidation() {
        System.out.println("1. Testing Validation & Exceptions...");
        try {
            Validator.validateAge(-5);
            System.err.println("FAIL: Negative age should throw exception.");
        } catch (InvalidDataException e) {
            System.out.println("PASS: Caught invalid age (" + e.getMessage() + ")");
        }

        try {
            Validator.validateContact("12345"); // Not 10 digits
            System.err.println("FAIL: Invalid contact should throw exception.");
        } catch (InvalidDataException e) {
            System.out.println("PASS: Caught invalid contact (" + e.getMessage() + ")");
        }
    }

    private static void testDeepCopyCloning() {
        System.out.println("2. Testing Deep Copy (Cloneable)...");
        IdGenerator idGen = IdGenerator.getInstance();
        Patient p1 = new Patient(idGen.generatePatientId(), "John Doe", 30, "1234567890", "O+");
        p1.addAllergy("Peanuts");

        Patient p2 = p1.clone();
        p2.setName("John Clone");
        p2.addAllergy("Dust"); // Modifying clone's allergy list

        System.out.println("Original Patient Allergies: " + p1.getAllergies());
        System.out.println("Cloned Patient Allergies: " + p2.getAllergies());

        if (!p1.getAllergies().contains("Dust")) {
            System.out.println("PASS: Deep copy successful. Modifying clone did not affect original.");
        } else {
            System.err.println("FAIL: Shallow copy detected! Original was modified.");
        }
    }

    private static void testSingletonIdGenerator() {
        System.out.println("3. Testing Singleton IdGenerator...");
        IdGenerator gen1 = IdGenerator.getInstance();
        IdGenerator gen2 = IdGenerator.getInstance();

        if (gen1 == gen2) {
            System.out.println("PASS: Both instances are exactly the same object in memory.");
        } else {
            System.err.println("FAIL: Multiple instances of Singleton created.");
        }
    }
}