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

public class BookDAO {

    // Create Book
    public static void CreateBook(String authorsId, String bookId, String title, String genre) {
        String sql = "INSERT INTO public.\"Books\" (\"Authors_id\", \"Book_id\", \"Title\", \"Genre\")\n" +
                     "VALUES (?, ?, ?, ?)";

        try (Connection conn = DataHandling.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, authorsId);
            stmt.setString(2, bookId);
            stmt.setString(3, title);
            stmt.setString(4, genre);
            stmt.executeUpdate();
            System.out.println("Book created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read Book
    public static List<Map<String, Object>> readBook() {
        // Use a plain SQL SELECT statement
        String sql = "SELECT \"Authors_id\", \"Book_id\", \"Title\", \"Genre\" FROM public.\"Books\"";

        List<Map<String, Object>> books = new ArrayList<>(); // List to store book data

        try (Connection conn = DataHandling.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Execute the query and get the result set
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // Create a map for each row to store book details
                Map<String, Object> book = new HashMap<>();
                book.put("Authors_id", rs.getString("Authors_id"));
                book.put("Book_id", rs.getString("Book_id"));
                book.put("Title", rs.getString("Title"));
                book.put("Genre", rs.getString("Genre"));

                // Add the map to the list
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Return the list of book data maps
        return books;
    }



// Update Book
public static void updateBook(String authorsId, String bookId, String title, String genre) {
    // Use a plain SQL UPDATE statement with placeholders
    String sql = "UPDATE public.\"Books\" " +
                 "SET \"Authors_id\" = ?, \"Title\" = ?, \"Genre\" = ? " +
                 "WHERE \"Book_id\" = ?";

    try (Connection conn = DataHandling.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        // Set the parameters for the update
        stmt.setString(1, authorsId);
        stmt.setString(2, title);
        stmt.setString(3, genre);
        stmt.setString(4, bookId);

        // Execute the update
        stmt.executeUpdate();
        System.out.println("Book updated successfully.");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


// Delete Book
public static void deleteBook(String bookId) {
    // Use a plain SQL DELETE statement with a placeholder
    String sql = "DELETE FROM public.\"Books\" WHERE \"Book_id\" = ?";

    try (Connection conn = DataHandling.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        // Set the parameter for the book ID
        stmt.setString(1, bookId);

        // Execute the delete operation
        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Book deleted successfully.");
        } else {
            System.out.println("No book found with the specified ID.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

}

