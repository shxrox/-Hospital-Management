package Hospitalmanagment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class HospitalManagement {
    private static final String URL = "jdbc:mysql://localhost:3306/hospital";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connected to database successfully!");

            Scanner scn = new Scanner(System.in);
            Patient patient = new Patient(connection, scn);
            Doctors doctor = new Doctors(connection);

            while (true) {
                System.out.println("\nWelcome to ABC Hospital Management System");
                System.out.println("1. Add Patient");
                System.out.println("2. View Patients");
                System.out.println("3. View Doctors");
                System.out.println("4. Book Appointment");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                int choice = scn.nextInt();
                switch (choice) {
                    case 1 -> patient.addPatient();
                    case 2 -> patient.viewPatient();
                    case 3 -> doctor.viewDoctor();
                    case 4 -> bookAppointment(patient, doctor, connection, scn);
                    case 5 -> {
                        System.out.println("Exiting System...");
                        return;
                    }
                    default -> System.out.println("Invalid choice! Try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void bookAppointment(Patient patient, Doctors doctor, Connection connection, Scanner scn) {
        System.out.print("Enter Patient ID: ");
        int patientId = scn.nextInt();

        System.out.print("Enter Doctor ID: ");
        int doctorId = scn.nextInt();

        System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
        String appointmentDate = scn.next();

        if (patient.getPatientId(patientId) && doctor.getDoctor(doctorId)) {
            if (checkDoctorAvailability(doctorId, appointmentDate, connection)) {
                String appointmentQuery = "INSERT INTO appointments (patient_id, doctor_id, appointment_date) VALUES (?, ?, ?)";
                try (var p = connection.prepareStatement(appointmentQuery)) {
                    p.setInt(1, patientId);
                    p.setInt(2, doctorId);
                    p.setString(3, appointmentDate);

                    int rowsAffected = p.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Appointment booked successfully!");
                    } else {
                        System.out.println("Appointment booking failed!");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Doctor is not available on this date!");
            }
        } else {
            System.out.println("Invalid Patient ID or Doctor ID.");
        }
    }

    public static boolean checkDoctorAvailability(int doctorId, String appointmentDate, Connection connection) {
        String query = "SELECT COUNT(*) FROM appointments WHERE doctor_id = ? AND appointment_date = ?";
        try (var p = connection.prepareStatement(query)) {
            p.setInt(1, doctorId);
            p.setString(2, appointmentDate);
            var rs = p.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) == 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
