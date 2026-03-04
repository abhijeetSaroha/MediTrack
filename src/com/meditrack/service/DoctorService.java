package com.meditrack.service;

import com.meditrack.entity.Doctor;
import com.meditrack.entity.Specialization;
import com.meditrack.util.DataStore;

import java.util.List;
import java.util.stream.Collectors;

public class DoctorService {
    private final DataStore<Doctor> doctorStore;

    public DoctorService(DataStore<Doctor> doctorStore) {
        this.doctorStore = doctorStore;
    }

    public void addDoctor(Doctor doctor) {
        doctorStore.save(doctor);
    }

    public Doctor getDoctor(String id) {
        return doctorStore.findById(id);
    }

    public List<Doctor> getAllDoctors() {
        return doctorStore.getAll();
    }

    public List<Doctor> getDoctorsBySpecialization(Specialization spec) {
        return doctorStore.getAll().stream()
                .filter(doc -> doc.getSpecialization() == spec)
                .collect(Collectors.toList());
    }

    public double computeAverageConsultationFee() {
        return doctorStore.getAll().stream()
                .mapToDouble(Doctor::getConsultationFee)
                .average()
                .orElse(0.0);
    }
}