package com.meditrack.entity;

public final class BillSummary {
    private final String billId;
    private final String patientName;
    private final String doctorName;
    private final double finalAmount;
    private final String date;

    public BillSummary(String billId, String patientName, String doctorName, double finalAmount, String date) {
        this.billId = billId;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.finalAmount = finalAmount;
        this.date = date;
    }

    public String getBillId() { return billId; }
    public String getPatientName() { return patientName; }
    public String getDoctorName() { return doctorName; }
    public double getFinalAmount() { return finalAmount; }
    public String getDate() { return date; }

    @Override
    public String toString() {
        return String.format("BillSummary [ID: %s | Date: %s | Patient: %s | Doctor: %s | Total: $%.2f]",
                billId, date, patientName, doctorName, finalAmount);
    }
}