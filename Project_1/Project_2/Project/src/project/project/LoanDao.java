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
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.List;

public class LoanDao {
    // Create Loan
public void createLoan(String loanId, String bookId, String borrowerId, java.sql.Date loanDate, java.sql.Date returnDate, short returned) throws SQLException {
    // Use a plain SQL INSERT statement with placeholders
    String sql = "INSERT INTO public.\"Loans\" (\"Loan_id\", \"Book_id\", \"Borrower_id\", \"Loan_date\", \"Return_date\", \"Returned\") " +
                 "VALUES (?, ?, ?, ?, ?, ?)";

    try (Connection conn = DataHandling.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        // Set the parameters for the insert operation
        stmt.setString(1, loanId);         // Set the loan ID
        stmt.setString(2, bookId);         // Set the book ID
        stmt.setString(3, borrowerId);     // Set the borrower ID
        stmt.setDate(4, loanDate);         // Set the loan date
        stmt.setDate(5, returnDate);       // Set the return date
        stmt.setShort(6, returned);        // Set the returned flag (e.g., 0 for not returned, 1 for returned)

        // Execute the insert operation
        stmt.executeUpdate();
        System.out.println("Loan created successfully.");
    } catch (SQLException e) {
        e.printStackTrace();
        throw e; // Optionally re-throw the exception if needed
    }
}

    
    // Read Loan
public static List<Map<String, Object>> readLoan(String borrowerId) throws SQLException {
    // SQL query with a placeholder
    String sql = "SELECT \"Loan_id\", \"Book_id\", \"Borrower_id\", \"LoanDate\", \"ReturnDate\", \"Returned\" " +
                 "FROM public.\"Loans\" WHERE \"Borrower_id\" = ?";

    // Create a list to hold the result
    List<Map<String, Object>> results = new ArrayList<>();

    try (Connection conn = DataHandling.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        // Set the parameter for the borrower ID
        stmt.setString(1, borrowerId);

        // Execute the query and get the result set
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // Create a map to hold each row's data
                Map<String, Object> row = new HashMap<>();
                row.put("Loan_id", rs.getString("Loan_id"));
                row.put("Book_id", rs.getString("Book_id"));
                row.put("Borrower_id", rs.getString("Borrower_id"));
                row.put("Loan_date", rs.getDate("LoanDate"));
                row.put("Return_date", rs.getDate("ReturnDate"));
                row.put("Returned", rs.getShort("Returned"));

                // Add the map to the list
                results.add(row);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        throw e; // Optionally re-throw the exception if needed
    }

    return results;
}


    
    // Update Loan
public void updateLoan(String loanId, String bookId, String borrowerId, java.sql.Date loanDate, java.sql.Date returnDate, short returned) throws SQLException {
    // Use a plain SQL UPDATE statement with placeholders
    String sql = "UPDATE public.\"Loans\" " +
                 "SET \"Book_id\" = ?, \"Borrower_id\" = ?, \"Loan_date\" = ?, \"Return_date\" = ?, \"Returned\" = ? " +
                 "WHERE \"Loan_id\" = ?";

    try (Connection conn = DataHandling.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        // Set the parameters for the update operation
        stmt.setString(1, bookId);         // Set the book ID
        stmt.setString(2, borrowerId);     // Set the borrower ID
        stmt.setDate(3, loanDate);         // Set the loan date
        stmt.setDate(4, returnDate);       // Set the return date
        stmt.setShort(5, returned);        // Set the returned flag
        stmt.setString(6, loanId);         // Set the loan ID (for the WHERE clause)

        // Execute the update operation
        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Loan updated successfully.");
        } else {
            System.out.println("No loan found with the specified ID.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        throw e; // Optionally re-throw the exception if needed
    }
}

    
    // Delete Loan
public void deleteLoan(String loanId) throws SQLException {
    // Use a plain SQL DELETE statement with a placeholder
    String sql = "DELETE FROM public.\"Loans\" WHERE \"Loan_id\" = ?";

    try (Connection conn = DataHandling.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        // Set the parameter for the loan ID
        stmt.setString(1, loanId);

        // Execute the delete operation
        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Loan deleted successfully.");
        } else {
            System.out.println("No loan found with the specified ID.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        throw e; // Optionally re-throw the exception if needed
    }
}

}

