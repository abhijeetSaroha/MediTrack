package com.meditrack.interfaces;

public interface Payable {
    void pay();
    boolean isPaid();
    double getAmount();

    default void printPaymentStatus() {
        String status = isPaid() ? "PAID" : "PENDING";
        System.out.printf("Payment Status: %s | Amount Due: $%.2f%n", status, getAmount());
    }
}