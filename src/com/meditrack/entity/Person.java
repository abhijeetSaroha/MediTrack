package com.meditrack.entity;

public abstract class Person extends MedicalEntity {
    private String name;
    private int age;
    private String contactNumber;

    public Person(String id, String name, int age, String contactNumber) {
        super(id);
        this.name = name;
        this.age = age;
        this.contactNumber = contactNumber;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    @Override
    public String toString() {
        return String.format("ID: %s | Name: %s | Age: %d | Contact: %s",
                getId(), name, age, contactNumber);
    }
}