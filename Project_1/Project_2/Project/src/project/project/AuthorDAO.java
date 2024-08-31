/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.project;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author janme
 */
public class AuthorDAO {
// Create Author
public static void createAuthor(String authorId, String name, String surname, String bio) {
    // Use a plain SQL INSERT statement with placeholders
    String sql = "INSERT INTO public.\"Authors\" (\"Authors_id\", \"Name\", \"Surname\", \"Bio\") " +
                 "VALUES (?, ?, ?, ?)";

    try (Connection conn = DataHandling.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        // Set the parameters for the insert operation
        stmt.setString(1, authorId);
        stmt.setString(2, name);
        stmt.setString(3, surname);
        stmt.setString(4, bio);

        // Execute the insert operation
        stmt.executeUpdate();
        System.out.println("Author created successfully.");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


// Read Author
public static void readAuthor(String authorId) {
    // Use a plain SQL SELECT statement with a placeholder
    String sql = "SELECT \"Authors_id\", \"Name\", \"Surname\", \"Bio\" " +
                 "FROM public.\"Authors\" WHERE \"Authors_id\" = ?";

    try (Connection conn = DataHandling.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        // Set the parameter for the author ID
        stmt.setString(1, authorId);

        // Execute the query and get the result set
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            // Retrieve values from the result set
            String id = rs.getString("Authors_id");
            String name = rs.getString("Name");
            String surname = rs.getString("Surname");
            String bio = rs.getString("Bio");
            System.out.printf("ID: %s, Name: %s, Surname: %s, Bio: %s%n", id, name, surname, bio);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    // Update Author
public static void updateAuthor(String authorId, String name, String surname, String bio) {
    // Use a plain SQL UPDATE statement with placeholders
    String sql = "UPDATE public.\"Authors\" " +
                 "SET \"Name\" = ?, \"Surname\" = ?, \"Bio\" = ? " +
                 "WHERE \"Authors_id\" = ?";

    try (Connection conn = DataHandling.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        // Set the parameters for the update
        stmt.setString(1, name);       // Set the author's name
        stmt.setString(2, surname);    // Set the author's surname
        stmt.setString(3, bio);        // Set the author's bio
        stmt.setString(4, authorId);   // Set the author ID to find the specific author

        // Execute the update
        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Author updated successfully.");
        } else {
            System.out.println("No author found with the specified ID.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    // Delete Author
public static void deleteAuthor(String authorId) {
    // Use a plain SQL DELETE statement with a placeholder
    String sql = "DELETE FROM public.\"Authors\" WHERE \"Authors_id\" = ?";

    try (Connection conn = DataHandling.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        // Set the parameter for the author ID
        stmt.setString(1, authorId);

        // Execute the delete operation
        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Author deleted successfully.");
        } else {
            System.out.println("No author found with the specified ID.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

}
