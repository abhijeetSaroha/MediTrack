package com.meditrack.entity;

public class Appointment extends MedicalEntity implements Cloneable {
    private String appointmentId;
    private Patient patient;
    private Doctor doctor;
    private String date;
    private AppointmentStatus status;

    public Appointment(String appointmentId, Patient patient, Doctor doctor, String date) {
        super(appointmentId);
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.status = AppointmentStatus.PENDING; // Default status
    }

    public String getAppointmentId() { return appointmentId; }
    public Patient getPatient() { return patient; }
    public Doctor getDoctor() { return doctor; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public AppointmentStatus getStatus() { return status; }
    public void setStatus(AppointmentStatus status) { this.status = status; }

    @Override
    public Appointment clone() {
        try {
            Appointment cloned = (Appointment) super.clone();
            // Deep copy the patient object so modifying the clone's patient doesn't affect the original
            if (this.patient != null) {
                cloned.patient = this.patient.clone();
            }
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Cloning not supported", e);
        }
    }

    @Override
    public String toString() {
        return String.format("Apt ID: %s | Date: %s | Status: %s\n  Doctor: %s\n  Patient: %s",
                appointmentId, date, status, doctor.getName(), patient.getName());
    }
}