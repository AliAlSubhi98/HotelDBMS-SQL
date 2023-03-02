package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;


public class Main {
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		// CreatingTables.createTablesInDataBase();
		// insert100Hotels();
		// Hotels.readFromTable();
		// Rooms.readFromTable();
		// Hotels.updateById();
		// Hotels.deleteById();
		// Hotels.makeIsActiveFalseById();
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void insert100Hotels() {
		String insertQuery = "INSERT INTO hotels ( hotel_name, hotel_location, created_date, updated_date, is_active)" +
				"VALUES ( ?, ?, GETDATE(), GETDATE(), ?)";

		try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;"
				+	            "databaseName=HotelDBMS;" +
				"encrypt=true;" +
				"trustServerCertificate=true", "sa", "root");
				PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
			Random rn = new Random();

			for (int i = 1; i <= 100; i++) {

				stmt.setString(1, "hotel " + i);
				stmt.setString(2, "location " + rn.nextInt(10));
				stmt.setBoolean(3, true);
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