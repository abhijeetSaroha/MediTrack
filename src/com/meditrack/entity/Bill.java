package com.meditrack.entity;

import com.meditrack.constants.Constants;
import com.meditrack.interfaces.Payable;

public class Bill implements Payable {
    private String billId;
    private Appointment appointment;
    private double baseAmount;
    private boolean isPaid;

    public Bill(String billId, Appointment appointment) {
        this.billId = billId;
        this.appointment = appointment;
        this.baseAmount = appointment.getDoctor().getConsultationFee();
        this.isPaid = false;
    }

    public String getBillId() { return billId; }
    public Appointment getAppointment() { return appointment; }

    public double calculateTotalWithTax() {
        return baseAmount + (baseAmount * Constants.TAX_RATE);
    }

    public BillSummary generateSummary() {
        return new BillSummary(
                this.billId,
                appointment.getPatient().getName(),
                appointment.getDoctor().getName(),
                calculateTotalWithTax(),
                appointment.getDate()
        );
    }

    @Override
    public void pay() {
        this.isPaid = true;
    }

    @Override
    public boolean isPaid() {
        return isPaid;
    }

    @Override
    public double getAmount() {
        return calculateTotalWithTax();
    }
}