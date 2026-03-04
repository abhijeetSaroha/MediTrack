package com.meditrack.entity;

public class Doctor extends Person {
    private Specialization specialization;
    private double consultationFee;

    public Doctor(String id, String name, int age, String contactNumber, Specialization specialization, double consultationFee) {
        super(id, name, age, contactNumber);
        this.specialization = specialization;
        this.consultationFee = consultationFee;
    }

    public Specialization getSpecialization() { return specialization; }
    public void setSpecialization(Specialization specialization) { this.specialization = specialization; }

    public double getConsultationFee() { return consultationFee; }
    public void setConsultationFee(double consultationFee) { this.consultationFee = consultationFee; }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Specialization: %s | Fee: $%.2f", specialization, consultationFee);
    }
}