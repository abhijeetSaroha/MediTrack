package com.meditrack;

import com.meditrack.constants.Constants;
import com.meditrack.entity.*;
import com.meditrack.exception.AppointmentNotFoundException;
import com.meditrack.exception.InvalidDataException;
import com.meditrack.service.AppointmentService;
import com.meditrack.service.DoctorService;
import com.meditrack.service.PatientService;
import com.meditrack.test.TestRunner;
import com.meditrack.util.CSVUtil;
import com.meditrack.util.DataStore;
import com.meditrack.util.DateUtil;
import com.meditrack.util.IdGenerator;
import com.meditrack.util.Validator;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static DoctorService doctorService;
    private static PatientService patientService;
    private static AppointmentService appointmentService;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        TestRunner.runTests();

        DataStore<Doctor> doctorStore = new DataStore<>();
        DataStore<Patient> patientStore = new DataStore<>();
        DataStore<Appointment> appointmentStore = new DataStore<>();

        doctorService = new DoctorService(doctorStore);
        patientService = new PatientService(patientStore);
        appointmentService = new AppointmentService(appointmentStore);

        if (args.length > 0 && args[0].equals("--loadData")) {
            System.out.println("Loading data from CSV files...");
            List<Doctor> loadedDocs = CSVUtil.loadDoctors(Constants.DOCTORS_FILE);
            loadedDocs.forEach(doctorService::addDoctor);

            List<Patient> loadedPatients = CSVUtil.loadPatients(Constants.PATIENTS_FILE);
            loadedPatients.forEach(patientService::addPatient);
            System.out.println("Data successfully loaded!");
        }

        boolean exit = false;
        while (!exit) {
            System.out.println("\n=== MediTrack Management System ===");
            System.out.println("1. Add a Patient");
            System.out.println("2. Add a Doctor");
            System.out.println("3. Book Appointment");
            System.out.println("4. View All Appointments");
            System.out.println("5. Cancel Appointment");
            System.out.println("6. Generate Bill for Appointment");
            System.out.println("7. View Analytics (Streams & Lambdas)");
            System.out.println("8. Save Data to CSV & Exit");
            System.out.print("Select an option: ");

            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1: addPatientUI(); break;
                case 2: addDoctorUI(); break;
                case 3: bookAppointmentUI(); break;
                case 4: viewAppointmentsUI(); break;
                case 5: cancelAppointmentUI(); break;
                case 6: generateBillUI(); break;
                case 7: viewAnalyticsUI(); break;
                case 8:
                    System.out.println("Saving data...");
                    CSVUtil.saveDoctors(doctorService.getAllDoctors(), Constants.DOCTORS_FILE);
                    CSVUtil.savePatients(patientService.getAllPatients(), Constants.PATIENTS_FILE);
                    System.out.println("Data saved. Exiting...");
                    exit = true;
                    break;
                default: System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }

    private static void addPatientUI() {
        try {
            System.out.print("Enter Patient Name: ");
            String name = scanner.nextLine();
            Validator.validateName(name);

            System.out.print("Enter Age: ");
            int age = Integer.parseInt(scanner.nextLine());
            Validator.validateAge(age);

            System.out.print("Enter Contact Number (10 digits): ");
            String contact = scanner.nextLine();
            Validator.validateContact(contact);

            System.out.print("Enter Blood Group: ");
            String bloodGroup = scanner.nextLine();

            String id = IdGenerator.getInstance().generatePatientId();
            Patient patient = new Patient(id, name, age, contact, bloodGroup);
            patientService.addPatient(patient);
            System.out.println("Patient added successfully with ID: " + id);

        } catch (InvalidDataException | NumberFormatException e) {
            System.out.println("Error adding patient: " + e.getMessage());
        }
    }

    private static void addDoctorUI() {
        try {
            System.out.print("Enter Doctor Name: ");
            String name = scanner.nextLine();
            Validator.validateName(name);

            System.out.print("Enter Age: ");
            int age = Integer.parseInt(scanner.nextLine());
            Validator.validateAge(age);

            System.out.print("Enter Contact Number (10 digits): ");
            String contact = scanner.nextLine();
            Validator.validateContact(contact);

            System.out.println("Available Specializations: CARDIOLOGIST, DERMATOLOGIST, NEUROLOGIST, ORTHOPEDIC, PEDIATRICIAN, GENERAL_PHYSICIAN");
            System.out.print("Enter Specialization: ");
            Specialization spec = Specialization.valueOf(scanner.nextLine().toUpperCase());

            System.out.print("Enter Consultation Fee: ");
            double fee = Double.parseDouble(scanner.nextLine());

            String id = IdGenerator.getInstance().generateDoctorId();
            Doctor doctor = new Doctor(id, name, age, contact, spec, fee);
            doctorService.addDoctor(doctor);
            System.out.println("Doctor added successfully with ID: " + id);

        } catch (Exception e) {
            System.out.println("Error adding doctor: " + e.getMessage());
        }
    }

    private static void bookAppointmentUI() {
        System.out.print("Enter Patient ID: ");
        Patient p = patientService.searchPatient(scanner.nextLine().toUpperCase());
        if (p == null) { System.out.println("Patient not found."); return; }

        System.out.print("Enter Doctor ID: ");
        Doctor d = doctorService.getDoctor(scanner.nextLine().toUpperCase());
        if (d == null) { System.out.println("Doctor not found."); return; }

        System.out.print("Enter Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        if (!DateUtil.isValidDate(date)) {
            System.out.println("Invalid date format. Booking failed.");
            return;
        }

        String id = IdGenerator.getInstance().generateAppointmentId();
        Appointment apt = new Appointment(id, p, d, date);
        appointmentService.bookAppointment(apt);
        System.out.println("Appointment Booked! ID: " + id);
    }

    private static void viewAppointmentsUI() {
        List<Appointment> apts = appointmentService.getAllAppointments();
        if (apts.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }
        System.out.println("--- All Appointments ---");
        apts.forEach(System.out::println);
    }

    private static void cancelAppointmentUI() {
        System.out.print("Enter Appointment ID to cancel: ");
        String id = scanner.nextLine().toUpperCase();
        try {
            appointmentService.cancelAppointment(id);
            System.out.println("Appointment cancelled successfully.");
        } catch (AppointmentNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void generateBillUI() {
        System.out.print("Enter Appointment ID: ");
        Appointment apt = appointmentService.getAppointment(scanner.nextLine().toUpperCase());
        if (apt == null) { System.out.println("Appointment not found."); return; }

        Bill bill = new Bill(IdGenerator.getInstance().generateBillId(), apt);
        bill.printPaymentStatus();

        BillSummary summary = bill.generateSummary();
        System.out.println("--- BILL GENERATED ---");
        System.out.println(summary.toString());
    }

    private static void viewAnalyticsUI() {
        System.out.println("--- Analytics (Powered by Streams) ---");
        System.out.printf("Average Consultation Fee across all Doctors: $%.2f%n",
                doctorService.computeAverageConsultationFee());

        System.out.println("\nDoctors by Specialization (e.g., CARDIOLOGIST):");
        System.out.print("Enter Specialization to filter: ");
        try {
            Specialization spec = Specialization.valueOf(scanner.nextLine().toUpperCase());
            List<Doctor> filteredDocs = doctorService.getDoctorsBySpecialization(spec);
            if (filteredDocs.isEmpty()) {
                System.out.println("No doctors found for this specialization.");
            } else {
                filteredDocs.forEach(d -> System.out.println(d.getName() + " - Fee: $" + d.getConsultationFee()));
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid specialization.");
        }
    }
}