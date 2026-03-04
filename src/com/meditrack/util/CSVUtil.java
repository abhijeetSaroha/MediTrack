package com.meditrack.util;

import com.meditrack.entity.Doctor;
import com.meditrack.entity.Patient;
import com.meditrack.entity.Specialization;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVUtil {

    public static void saveDoctors(List<Doctor> doctors, String filePath) {
        File file = new File(filePath);
        file.getParentFile().mkdirs();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Doctor d : doctors) {
                bw.write(String.format("%s,%s,%d,%s,%s,%.2f\n",
                        d.getId(), d.getName(), d.getAge(), d.getContactNumber(), d.getSpecialization(), d.getConsultationFee()));
            }
        } catch (IOException e) {
            System.err.println("Error saving doctors: " + e.getMessage());
        }
    }

    public static List<Doctor> loadDoctors(String filePath) {
        List<Doctor> doctors = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) return doctors;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    Doctor doc = new Doctor(parts[0], parts[1], Integer.parseInt(parts[2]),
                            parts[3], Specialization.valueOf(parts[4]), Double.parseDouble(parts[5]));
                    doctors.add(doc);
                }
            }
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Error loading doctors: " + e.getMessage());
        }
        return doctors;
    }

    public static void savePatients(List<Patient> patients, String filePath) {
        File file = new File(filePath);
        file.getParentFile().mkdirs();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Patient p : patients) {
                String allergiesStr = String.join(";", p.getAllergies());
                if (allergiesStr.isEmpty()) allergiesStr = "NONE";

                bw.write(String.format("%s,%s,%d,%s,%s,%s\n",
                        p.getId(), p.getName(), p.getAge(), p.getContactNumber(), p.getBloodGroup(), allergiesStr));
            }
        } catch (IOException e) {
            System.err.println("Error saving patients: " + e.getMessage());
        }
    }

    public static List<Patient> loadPatients(String filePath) {
        List<Patient> patients = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) return patients;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    Patient patient = new Patient(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3], parts[4]);
                    if (!parts[5].equals("NONE")) {
                        String[] allergies = parts[5].split(";");
                        for (String allergy : allergies) {
                            patient.addAllergy(allergy);
                        }
                    }
                    patients.add(patient);
                }
            }
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Error loading patients: " + e.getMessage());
        }
        return patients;
    }
}