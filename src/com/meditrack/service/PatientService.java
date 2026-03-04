package com.meditrack.service;

import com.meditrack.entity.Patient;
import com.meditrack.util.DataStore;

import java.util.List;
import java.util.stream.Collectors;

public class PatientService {
    private final DataStore<Patient> patientStore;

    public PatientService(DataStore<Patient> patientStore) {
        this.patientStore = patientStore;
    }

    public void addPatient(Patient patient) {
        patientStore.save(patient);
    }

    public List<Patient> getAllPatients() {
        return patientStore.getAll();
    }

    public Patient searchPatient(String id) {
        return patientStore.findById(id);
    }

    public List<Patient> searchPatient(String name, boolean isNameSearch) {
        if (!isNameSearch) return null;
        return patientStore.getAll().stream()
                .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Patient> searchPatient(int age) {
        return patientStore.getAll().stream()
                .filter(p -> p.getAge() == age)
                .collect(Collectors.toList());
    }
}