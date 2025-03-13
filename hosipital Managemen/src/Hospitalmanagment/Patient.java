package Hospitalmanagment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {
    private Connection connection;
    private Scanner scn;

    public Patient(Connection connection, Scanner scn) {
        this.connection = connection;
        this.scn = scn;
    }

    public void addPatient() {
        System.out.print("Enter Patient Name: ");
        String name = scn.next();
        System.out.print("Enter Patient Age: ");
        int age = scn.nextInt();
        System.out.print("Enter Patient Gender (Male/Female/Other): ");
        String gender = scn.next();

        String query = "INSERT INTO patients(name, age, gender) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, gender);
            int affectedRows = ps.executeUpdate();

            System.out.println(affectedRows > 0 ? "Patient added successfully!" : "Failed to add patient!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewPatient() {
        String query = "SELECT * FROM patients";
        try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            System.out.println("\nPatients List:");
            System.out.println("ID | Name | Age | Gender");
            while (rs.next()) {
                System.out.printf("%d | %s | %d | %s%n", rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getString("gender"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean getPatientId(int id) {
        String query = "SELECT id FROM patients WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            return ps.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
