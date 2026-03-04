package com.meditrack.entity;

import java.util.ArrayList;
import java.util.List;

public class Patient extends Person implements Cloneable {
    private String bloodGroup;
    private List<String> allergies;

    public Patient(String id, String name, int age, String contactNumber, String bloodGroup) {
        super(id, name, age, contactNumber);
        this.bloodGroup = bloodGroup;
        this.allergies = new ArrayList<>();
    }

    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }

    public List<String> getAllergies() { return allergies; }
    public void addAllergy(String allergy) { this.allergies.add(allergy); }

    @Override
    public Patient clone() {
        try {
            Patient cloned = (Patient) super.clone();

            cloned.allergies = new ArrayList<>(this.allergies);

            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Cloning not supported", e);
        }
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Blood Group: %s | Allergies: %s", bloodGroup, allergies);
    }
}