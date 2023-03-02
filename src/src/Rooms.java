package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Rooms{
    private static final String CONNECTION_URL = "jdbc:sqlserver://localhost:1433;" +
            "databaseName=HotelDBMS;" +
            "encrypt=true;" +
            "trustServerCertificate=true";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "root";
    private static final String TABLE_NAME = "Rooms";
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
				System.out.println("Room ID: " + rs.getInt("id"));
				System.out.println("Room_Type ID: " + rs.getString("room_type_id"));
				System.out.println("hotel ID  : " + rs.getString("hotel_id"));
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
	}