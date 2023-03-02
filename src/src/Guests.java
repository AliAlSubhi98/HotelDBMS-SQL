package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Guests{
    private static final String CONNECTION_URL = "jdbc:sqlserver://localhost:1433;" +
            "databaseName=HotelDBMS;" +
            "encrypt=true;" +
            "trustServerCertificate=true";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "root";
    private static final String TABLE_NAME = "Guests";
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
				System.out.println("Guests ID: " + rs.getInt("id"));
				System.out.println("guest name : " + rs.getString("guest_name"));
				System.out.println("guest phone : " + rs.getInt("guest_phone"));
				System.out.println("guest_accompanying_members : " + rs.getString("guest_accompanying_members"));
				System.out.println("guest_payment_amount : " + rs.getInt("guest_payment_amount"));
				System.out.println("room_id : " + rs.getInt("room_id"));
				System.out.println("hotel_id : " + rs.getInt("hotel_id"));
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