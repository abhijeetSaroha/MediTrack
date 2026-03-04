package com.meditrack.constants;

public class Constants {
    public static final double TAX_RATE = 0.18; // 18% tax
    public static final String DATA_DIRECTORY = "data/";
    public static final String PATIENTS_FILE = DATA_DIRECTORY + "patients.csv";
    public static final String DOCTORS_FILE = DATA_DIRECTORY + "doctors.csv";

    static {
        System.out.println("MediTrack System Configuration Loaded.");
    }

    private Constants() {
        // Prevent instantiation
    }
}