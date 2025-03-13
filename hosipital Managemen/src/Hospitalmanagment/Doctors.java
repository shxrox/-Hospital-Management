package Hospitalmanagment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctors {
    private Connection connection;

    public Doctors(Connection connection) {
        this.connection = connection;
    }

    public void viewDoctor() {
        String query = "SELECT * FROM doctors";
        try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            System.out.println("\nDoctors List:");
            System.out.println("ID | Name | Department");
            while (rs.next()) {
                System.out.printf("%d | %s | %s%n", rs.getInt("id"), rs.getString("name"), rs.getString("dept"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean getDoctor(int id) {
        String query = "SELECT id FROM doctors WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            return ps.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
