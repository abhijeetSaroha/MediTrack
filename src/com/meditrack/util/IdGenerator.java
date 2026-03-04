package com.meditrack.util;

import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {
    // Lazy Singleton instance
    private static IdGenerator instance;

    // Thread-safe counter
    private final AtomicInteger counter = new AtomicInteger(1000);

    // Private constructor prevents instantiation
    private IdGenerator() {}

    public static synchronized IdGenerator getInstance() {
        if (instance == null) {
            instance = new IdGenerator();
        }
        return instance;
    }

    public String generatePatientId() {
        return "P" + counter.getAndIncrement();
    }

    public String generateDoctorId() {
        return "D" + counter.getAndIncrement();
    }

    public String generateAppointmentId() {
        return "A" + counter.getAndIncrement();
    }

    public String generateBillId() {
        return "B" + counter.getAndIncrement();
    }
}