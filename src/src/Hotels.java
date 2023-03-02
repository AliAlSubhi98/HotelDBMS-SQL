package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class Hotels{
	private static final String CONNECTION_URL = "jdbc:sqlserver://localhost:1433;" +
            "databaseName=HotelDBMS;" +
            "encrypt=true;" +
            "trustServerCertificate=true";
	private static final String USERNAME = "sa";
	private static final String PASSWORD = "root";
	private static final String TABLE_NAME = "hotels";
/////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void readFromTable() {
		try (Connection conn = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + TABLE_NAME)) {

			int numRows = 0;
			while (numRows <= 0) {
				System.out.print("Enter number of rows to print: ");
				if (Main.scanner.hasNextInt()) {
					numRows = Main.scanner.nextInt();
				} else {
					Main.scanner.next();
					System.out.println("Invalid input. Please enter a positive integer.");
				}
			}

			ResultSet rs = stmt.executeQuery();

			int row = 0;
			while (rs.next() && row < numRows) {
				System.out.println("Hotel ID: " + rs.getInt("id"));
				System.out.println("Hotel Name: " + rs.getString("hotel_name"));
				System.out.println("Location: " + rs.getString("hotel_location"));
				System.out.println("Created Date: " + rs.getString("created_date"));
				System.out.println("Updated Date: " + rs.getString("updated_date"));
				System.out.println("Is Active: " + rs.getBoolean("is_active"));
				System.out.println();
				row++;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void getById() {
		try (Connection conn = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE id = ?")) {

			// Prompt the user for the ID input
			System.out.print("Enter the ID of the hotel to retrieve: ");
			int id = Main.scanner.nextInt();

			// Set the ID parameter in the prepared statement
			stmt.setInt(1, id);
			
			// Execute the query and retrieve the result set
			ResultSet rs = stmt.executeQuery();

			// Print the result set to the console
			if (rs.next()) {
				System.out.println("Hotel ID: " + rs.getInt("id"));
				System.out.println("Hotel Name: " + rs.getString("hotel_name"));
				System.out.println("Location: " + rs.getString("hotel_location"));
				System.out.println("Created Date: " + rs.getString("created_date"));
				System.out.println("Updated Date: " + rs.getString("updated_date"));
				System.out.println("Is Active: " + rs.getBoolean("is_active"));
			} else {
				System.out.println("No hotel found with ID " + id);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void updateById() {
		try (Connection conn = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement("UPDATE " + TABLE_NAME
						+ " SET hotel_name = ?, hotel_location = ?, updated_date = GETDATE(), is_active = ? WHERE id = ?")) {

			System.out.print("Enter the ID of the hotel to update: ");
			int id = Main.scanner.nextInt();

			System.out.print("Enter the new hotel name: ");
			String hotelName = Main.scanner.next();

			System.out.print("Enter the new hotel location: ");
			String hotelLocation = Main.scanner.next();

			System.out.print("Enter the new is_active value (true/false): ");
			boolean isActive = Main.scanner.nextBoolean();

			// Set the parameters in the prepared statement
			stmt.setString(1, hotelName);
			stmt.setString(2, hotelLocation);
			stmt.setBoolean(3, isActive);
			stmt.setInt(4, id);

			// Execute the update statement and print the number of rows updated
			int rowsUpdated = stmt.executeUpdate();
			System.out.println("Updated " + rowsUpdated + " row(s)");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void deleteById() {
		System.out.print("Enter the ID of the hotel to delete: ");
		int id = Main.scanner.nextInt();

		String deleteQuery = "DELETE FROM hotels WHERE id = ?";

		try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;"
				+ "databaseName=HotelDBMS;" + "encrypt=true;" + "trustServerCertificate=true", "sa", "root");
				PreparedStatement stmt = conn.prepareStatement(deleteQuery)) {
			stmt.setInt(1, id);
			int rowsDeleted = stmt.executeUpdate();

			if (rowsDeleted > 0) {
				System.out.println("Successfully deleted the hotel with ID " + id);
			} else {
				System.out.println("No hotel was found with the ID " + id);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void makeIsActiveFalseById() {
		System.out.print("Enter the hotel ID to update: ");
		int hotelId = Main.scanner.nextInt();

		System.out.print("Do you want to activate or deactivate the hotel? (A/D): ");
		String choice = Main.scanner.next().toLowerCase();

		String updateQuery = "UPDATE hotels SET is_active = ? WHERE id = ?";

		try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;"
				+ "databaseName=HotelDBMS;" 
				+"encrypt=true;" 
				+"trustServerCertificate=true", "sa", "root");
				PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

			boolean isActive = false;
			if (choice.equals("a")) {
				isActive = true;
			} else if (choice.equals("d")) {
				isActive = false;
			} else {
				System.out.println("Invalid choice.");
				return;
			}

			stmt.setBoolean(1, isActive);
			stmt.setInt(2, hotelId);

			int result = stmt.executeUpdate();
			System.out.println("Updated " + result + " row(s) in hotels table.");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void insertIntoTable() {
		String insertQuery = "INSERT INTO hotels (hotel_name, hotel_location, created_date, updated_date, is_active)" +
				"VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;"
				+ "databaseName=HotelDBMS;" + "encrypt=true;" + "trustServerCertificate=true", "sa", "root");
				PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
			System.out.println("How many rows do you want to insert? ");
			int numRows = Main.scanner.nextInt();
			Random rn = new Random();

			for (int i = 1; i <= numRows; i++) {
				stmt.setString(1, "hotel " + i);
				stmt.setString(2, "location " + rn.nextInt(10));
				stmt.setString(3, "GETDATE()");
				stmt.setString(4, "GETDATE()");
				stmt.setBoolean(5, false);
				stmt.addBatch();
			}

			int[] results = stmt.executeBatch();
			System.out.println("Inserted " + results.length + " rows into hotels table.");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////


}
