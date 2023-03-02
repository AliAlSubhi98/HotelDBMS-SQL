package src;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class CreatingTables {
	
	public static void createTablesInDataBase() {
	    String url = "jdbc:sqlserver://localhost:1433;" +
	            "databaseName=HotelDBMS;" +
	            "encrypt=true;" +
	            "trustServerCertificate=true";
	
	    String user = "sa";
	    String pass = "root";
	

	    Connection con = null;
	  //-----------------------------------------------------------------------------------------------------------------------------------

	    try {
	
	        Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
	        DriverManager.registerDriver(driver);
	
	        con = DriverManager.getConnection(url, user, pass);
	
	
	        Statement st = con.createStatement();
	
	        String sql1 = "IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Hotels]') AND type in (N'U'))\r\n"
	        		+ "CREATE TABLE Hotels (\r\n"
	        		+ "  id INTEGER IDENTITY PRIMARY KEY,\r\n"
	        		+ "  hotel_name VARCHAR(255) NOT NULL,\r\n"
	        		+ "  hotel_location VARCHAR(255),\r\n"
	        		+ "  created_date DATE NOT NULL,\r\n"
	        		+ "  updated_date DATE,\r\n"
	        		+ "  is_Active BIT NOT NULL\r\n"
	        		+ ");\r\n"
	        		+ "";
	
	        st.executeUpdate(sql1);
	        System.out.println("THE Hotels TABLE CREATED SUCCESSFULLY");
	        con.close();
	    } catch (Exception ex) {
	        System.err.println(ex);
	    }

	
//-----------------------------------------------------------------------------------------------------------------------------------
	    try {
	    	
	        Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
	        DriverManager.registerDriver(driver);
	
	        con = DriverManager.getConnection(url, user, pass);
	
	
	        Statement st = con.createStatement();
	
	        String sql2 = "IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Room_Type]') AND type in (N'U'))\r\n"
	        		+ "CREATE TABLE Room_Type (\r\n"
	        		+ "  id INTEGER PRIMARY KEY,\r\n"
	        		+ "  room_type_name VARCHAR(255) NOT NULL,\r\n"
	        		+ "  created_date DATE,\r\n"
	        		+ "  updated_date DATE,\r\n"
	        		+ "  is_Active BIT NOT NULL\r\n"
	        		+ ");\r\n"
	        		+ "";
	
	        st.executeUpdate(sql2);
	        System.out.println("THE Room_Type TABLE CREATED SUCCESSFULLY");
	        con.close();
	    } catch (Exception ex) {
	        System.err.println(ex);
	    }
//-----------------------------------------------------------------------------------------------------------------------------------
	    try {
	    	
	        Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
	        DriverManager.registerDriver(driver);
	
	        con = DriverManager.getConnection(url, user, pass);
	
	
	        Statement st = con.createStatement();
	
	        String sql3 = "IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Rooms]') AND type in (N'U'))\r\n"
	        		+ "CREATE TABLE Rooms (\r\n"
	        		+ "  id INTEGER PRIMARY KEY,\r\n"
	        		+ "  room_type_id INTEGER,\r\n"
	        		+ "  hotel_id INTEGER,\r\n"
	        		+ "  created_date DATE NOT NULL,\r\n"
	        		+ "  updated_date DATE,\r\n"
	        		+ "  is_Active BIT NOT NULL,\r\n"
	        		+ "  FOREIGN KEY (room_type_id) REFERENCES Room_Type(id),\r\n"
	        		+ "  FOREIGN KEY (hotel_id) REFERENCES Hotels(id)\r\n"
	        		+ ");";
	
	        st.executeUpdate(sql3);
	        System.out.println("THE Rooms TABLE CREATED SUCCESSFULLY");
	        con.close();
	    } catch (Exception ex) {
	        System.err.println(ex);
	    }
//-----------------------------------------------------------------------------------------------------------------------------------

	    try {
	    	
	        Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
	        DriverManager.registerDriver(driver);
	
	        con = DriverManager.getConnection(url, user, pass);
	
	
	        Statement st = con.createStatement();
	
	        String sql4 = "IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Guests]') AND type in (N'U'))\r\n"
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
	        		+ ");";
	
	        st.executeUpdate(sql4);
	        System.out.println("THE Guests TABLE CREATED SUCCESSFULLY");
	        con.close();
	    } catch (Exception ex) {
	        System.err.println(ex);
	    }
//-----------------------------------------------------------------------------------------------------------------------------------
	    try {
	    	
	        Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
	        DriverManager.registerDriver(driver);
	
	        con = DriverManager.getConnection(url, user, pass);
	
	
	        Statement st = con.createStatement();
	
	        String sql5 = "IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Employee_Type]') AND type in (N'U'))\r\n"
	        		+ "CREATE TABLE Employee_Type (\r\n"
	        		+ "  id INTEGER PRIMARY KEY,\r\n"
	        		+ "  employee_type_name VARCHAR(255) NOT NULL,\r\n"
	        		+ "  created_date DATE NOT NULL,\r\n"
	        		+ "  updated_date DATE,\r\n"
	        		+ "  is_Active BIT NOT NULL\r\n"
	        		+ ");";
	
	        st.executeUpdate(sql5);
	        System.out.println("THE Employee_Type TABLE CREATED SUCCESSFULLY");
	        con.close();
	    } catch (Exception ex) {
	        System.err.println(ex);
	    }
//-----------------------------------------------------------------------------------------------------------------------------------
	    try {
	    	
	        Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
	        DriverManager.registerDriver(driver);
	
	        con = DriverManager.getConnection(url, user, pass);
	
	
	        Statement st = con.createStatement();
	
	        String sql6 = "IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Employees]') AND type in (N'U'))\r\n"
	        		+ "CREATE TABLE Employees (\r\n"
	        		+ "  id INTEGER PRIMARY KEY,\r\n"
	        		+ "  employee_type_id INTEGER,\r\n"
	        		+ "  room_id INTEGER,\r\n"
	        		+ "  created_date DATE NOT NULL,\r\n"
	        		+ "  updated_date DATE,\r\n"
	        		+ "  is_Active BIT NOT NULL,\r\n"
	        		+ "  FOREIGN KEY (employee_type_id) REFERENCES Employee_Type(id),\r\n"
	        		+ "  FOREIGN KEY (room_id) REFERENCES Rooms(id)\r\n"
	        		+ ");";
	
	        st.executeUpdate(sql6);
	        System.out.println("THE Employees TABLE CREATED SUCCESSFULLY");
	        con.close();
	    } catch (Exception ex) {
	        System.err.println(ex);
	    }
	  //-----------------------------------------------------------------------------------------------------------------------------------
	}
}
