package src;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class CreatingTables {
	
	public void createTablesInDataBase() {
	    String url = "jdbc:sqlserver://localhost:1433;" +
	            "databaseName=HotelDBMS;" +
	            "encrypt=true;" +
	            "trustServerCertificate=true";
	
	    String user = "sa";
	    String pass = "root";
	
	    Scanner scanner = new Scanner(System.in);
	
	  /*  System.out.println("enter name");
	    String name = scanner.next();
	
	    System.out.println("enter roll no");
	    Integer roll = scanner.nextInt();
	
	    System.out.println("enter class");
	    String cls = scanner.next();*/
	
	
	    Connection con = null;
	
	    try {
	
	        Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
	        DriverManager.registerDriver(driver);
	
	        con = DriverManager.getConnection(url, user, pass);
	
	
	        Statement st = con.createStatement();
	
	        String sql = "-- Create Hotels table\r\n"
	        		+ "CREATE TABLE Hotels (\r\n"
	        		+ "  id INTEGER PRIMARY KEY,\r\n"
	        		+ "  hotel_name VARCHAR(255) NOT NULL,\r\n"
	        		+ "  hotel_location VARCHAR(255),\r\n"
	        		+ "  created_date DATE NOT NULL,\r\n"
	        		+ "  updated_date DATE,\r\n"
	        		+ "  is_Active BIT NOT NULL\r\n"
	        		+ ");\r\n"
	        		+ "\r\n"
	        		+ "-- Create Room_Type table\r\n"
	        		+ "CREATE TABLE Room_Type (\r\n"
	        		+ "  id INTEGER PRIMARY KEY,\r\n"
	        		+ "  room_type_name VARCHAR(255) NOT NULL,\r\n"
	        		+ "  created_date DATE,\r\n"
	        		+ "  updated_date DATE,\r\n"
	        		+ "  is_Active BIT NOT NULL\r\n"
	        		+ ");\r\n"
	        		+ "\r\n"
	        		+ "-- Create Rooms table\r\n"
	        		+ "CREATE TABLE Rooms (\r\n"
	        		+ "  id INTEGER PRIMARY KEY,\r\n"
	        		+ "  room_type_id INTEGER,\r\n"
	        		+ "  hotel_id INTEGER,\r\n"
	        		+ "  created_date DATE NOT NULL,\r\n"
	        		+ "  updated_date DATE,\r\n"
	        		+ "  is_Active BIT NOT NULL,\r\n"
	        		+ "  FOREIGN KEY (room_type_id) REFERENCES Room_Type(id),\r\n"
	        		+ "  FOREIGN KEY (hotel_id) REFERENCES Hotels(id)\r\n"
	        		+ ");\r\n"
	        		+ "\r\n"
	        		+ "-- Create Guests table\r\n"
	        		+ "CREATE TABLE Guests (\r\n"
	        		+ "  id INTEGER PRIMARY KEY,\r\n"
	        		+ "  guest_name VARCHAR(255) NOT NULL,\r\n"
	        		+ "  guest_phone VARCHAR(20) NOT NULL,\r\n"
	        		+ "  guest_accompanying_members INTEGER NOT NULL,\r\n"
	        		+ "  guest_payment_amount INTEGER NOT NULL,\r\n"
	        		+ "  room_id INTEGER,\r\n"
	        		+ "  hotel_id INTEGER,\r\n"
	        		+ "  created_date DATE NOT NULL,\r\n"
	        		+ "  updated_date DATE,\r\n"
	        		+ "  is_Active BIT NOT NULL,\r\n"
	        		+ "  FOREIGN KEY (room_id) REFERENCES Rooms(id),\r\n"
	        		+ "  FOREIGN KEY (hotel_id) REFERENCES Hotels(id)\r\n"
	        		+ ");\r\n"
	        		+ "\r\n"
	        		+ "-- Create Employee_Type table\r\n"
	        		+ "CREATE TABLE Employee_Type (\r\n"
	        		+ "  id INTEGER PRIMARY KEY,\r\n"
	        		+ "  employee_type_name VARCHAR(255) NOT NULL,\r\n"
	        		+ "  created_date DATE NOT NULL,\r\n"
	        		+ "  updated_date DATE,\r\n"
	        		+ "  is_Active BIT NOT NULL\r\n"
	        		+ ");\r\n"
	        		+ "\r\n"
	        		+ "-- Create Employees table\r\n"
	        		+ "CREATE TABLE Employees (\r\n"
	        		+ "  id INTEGER PRIMARY KEY,\r\n"
	        		+ "  employee_type_id INTEGER,\r\n"
	        		+ "  room_id INTEGER,\r\n"
	        		+ "  created_date DATE NOT NULL,\r\n"
	        		+ "  updated_date DATE,\r\n"
	        		+ "  is_Active BIT NOT NULL,\r\n"
	        		+ "  FOREIGN KEY (employee_type_id) REFERENCES Employee_Type(id),\r\n"
	        		+ "  FOREIGN KEY (room_id) REFERENCES Rooms(id)\r\n"
	        		+ ");\r\n"
	        		+ "";
	
	        st.executeUpdate(sql);
	        System.out.println("THE TABLES CREATED SUCCESSFULLY");
	        con.close();
	    } catch (Exception ex) {
	        System.err.println(ex);
	    }

	}
}
