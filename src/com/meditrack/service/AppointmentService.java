package com.meditrack.service;

import com.meditrack.entity.Appointment;
import com.meditrack.entity.AppointmentStatus;
import com.meditrack.entity.Doctor;
import com.meditrack.exception.AppointmentNotFoundException;
import com.meditrack.util.DataStore;

import java.util.List;

public class AppointmentService {
    private final DataStore<Appointment> appointmentStore;

    public AppointmentService(DataStore<Appointment> appointmentStore) {
        this.appointmentStore = appointmentStore;
    }

    public void bookAppointment(Appointment appointment) {
        appointment.setStatus(AppointmentStatus.CONFIRMED);
        appointmentStore.save(appointment);
    }

    public void cancelAppointment(String appointmentId) throws AppointmentNotFoundException {
        Appointment apt = appointmentStore.findById(appointmentId);
        if (apt == null) {
            throw new AppointmentNotFoundException("Cannot cancel. Appointment ID " + appointmentId + " not found.");
        }
        apt.setStatus(AppointmentStatus.CANCELLED);
    }

    public Appointment getAppointment(String id) {
        return appointmentStore.findById(id);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentStore.getAll();
    }

    public long countAppointmentsForDoctor(Doctor doctor) {
        return appointmentStore.getAll().stream()
                .filter(apt -> apt.getDoctor().getId().equals(doctor.getId()))
                .filter(apt -> apt.getStatus() == AppointmentStatus.CONFIRMED || apt.getStatus() == AppointmentStatus.COMPLETED)
                .count();
    }
}