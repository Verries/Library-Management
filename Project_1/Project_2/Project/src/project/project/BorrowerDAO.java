/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BorrowerDAO {

    // Create Borrower
    // Create Borrower
public static void createBorrower(String borrowerId, String name, String surname, String phone, String email, String address) {
    // Use a plain SQL INSERT statement with placeholders
    String sql = "INSERT INTO public.\"Borrowers\" (\"Borrower_id\", \"Name\", \"Surname\", \"Phone\", \"Email\", \"Address\") " +
                 "VALUES (?, ?, ?, ?, ?, ?)";

    try (Connection conn = DataHandling.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        // Set the parameters for the insert operation
        stmt.setString(1, borrowerId);
        stmt.setString(2, name);
        stmt.setString(3, surname);
        stmt.setString(4, phone);
        stmt.setString(5, email);
        stmt.setString(6, address);

        // Execute the insert operation
        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Borrower created successfully.");
        } else {
            System.out.println("No rows affected. Borrower might not have been created.");
        }
    } catch (SQLException e) {
        // Print detailed error message
        System.err.println("SQL Exception occurred:");
        e.printStackTrace();
    } catch (Exception e) {
        // Handle any other exceptions
        System.err.println("An unexpected error occurred:");
        e.printStackTrace();
    }
}


    // Read Borrower
public static List<Map<String, Object>> readBorrower() {
        // SQL query to fetch all borrower data
        String sql = "SELECT \"Borrower_id\", \"Name\", \"Surname\", \"Phone\", \"Email\", \"Address\" " +
                     "FROM public.\"Borrowers\"";

        // List to store borrower data
        List<Map<String, Object>> borrowers = new ArrayList<>();

        try (Connection conn = DataHandling.getConnection();  // Get database connection
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Execute the query and get the result set
            ResultSet rs = stmt.executeQuery();

            // Loop through the result set
            while (rs.next()) {
                // Create a map for each borrower
                Map<String, Object> borrower = new HashMap<>();
                borrower.put("Borrower_id", rs.getString("Borrower_id"));
                borrower.put("Name", rs.getString("Name"));
                borrower.put("Surname", rs.getString("Surname"));
                borrower.put("Phone", rs.getString("Phone"));
                borrower.put("Email", rs.getString("Email"));
                borrower.put("Address", rs.getString("Address"));

                // Add the map to the list
                borrowers.add(borrower);
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Handle SQL exceptions
        }

        // Return the list of borrower data
        return borrowers;
    }


// Update Borrower
public static void updateBorrower(String borrowerId, String name, String surname, String phone, String email, String address) {
    // Use a plain SQL UPDATE statement with placeholders
    String sql = "UPDATE public.\"Borrowers\" " +
                 "SET \"Name\" = ?, \"Surname\" = ?, \"Phone\" = ?, \"Email\" = ?, \"Address\" = ? " +
                 "WHERE \"Borrower_id\" = ?";

    try (Connection conn = DataHandling.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        // Set the parameters for the update
        stmt.setString(1, name);       // Set the borrower's name
        stmt.setString(2, surname);    // Set the borrower's surname
        stmt.setString(3, phone);      // Set the borrower's phone
        stmt.setString(4, email);      // Set the borrower's email
        stmt.setString(5, address);    // Set the borrower's address
        stmt.setString(6, borrowerId); // Set the borrower ID to find the specific borrower

        // Execute the update
        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Borrower updated successfully.");
        } else {
            System.out.println("No borrower found with the specified ID.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


// Delete Borrower
public static void deleteBorrower(String borrowerId) {
    // Use a plain SQL DELETE statement with a placeholder
    String sql = "DELETE FROM public.\"Borrowers\" WHERE \"Borrower_id\" = ?";

    try (Connection conn = DataHandling.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        // Set the parameter for the borrower ID
        stmt.setString(1, borrowerId);

        // Execute the delete operation
        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Borrower deleted successfully.");
        } else {
            System.out.println("No borrower found with the specified ID.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

}

