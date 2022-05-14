package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Billing {
	// A common method to connect to the DB
	private Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/eloctrogride_clientside", "root", "");

			// For testing
			System.out.print("Successfully connected");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	public String readEmployee() {
		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Account No</th>" + "<th>From Date</th>" +"<th>To Date</th>"
					+ "<th>Current Reading</th>" + "<th>Status</th>" + "<th>Update</th>" +"<th>Remove</th></tr>";

			String query = "select * from billing";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {

				String eID = Integer.toString(rs.getInt("eID"));
				String eName = rs.getString("eName");
				String eAddress = rs.getString("eAddress");
				String eEmail = rs.getString("eEmail");
				String eDate = rs.getString("eDate");
				String pno = rs.getString("pno");

				// Add into the html table

				output += "<tr><td><input id='hideIDUpdate' name='hideIDUpdate' type='hidden' value='"
						+ eID + "'>" + eName + "</td>";
				output += "<td>" + eAddress + "</td>";
				output += "<td>" + eEmail + "</td>";
				output += "<td>" + eDate + "</td>";
				output += "<td>" + pno + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-eID='"
						+ eID + "'>" + "</td></tr>";

			}

			con.close();

			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Billing Details.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	// Insert Customer
	public String insertEmployee(String eName, String eAddress, String eEmail, String eDate,
			String pno) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = " insert into billing (`eID`,`eName`,`eAddress`,`eEmail`,`eDate`,`pno`)"
					+ " values (?, ?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, eName);
			preparedStmt.setString(3, eAddress);
			preparedStmt.setString(4, eEmail);
			preparedStmt.setString(5, eDate);
			preparedStmt.setString(6, pno);

			// execute the statement
			preparedStmt.execute();
			con.close();

			// Create JSON Object to show successful msg.
			String newEmployee = readEmployee();
			output = "{\"status\":\"success\", \"data\": \"" + newEmployee + "\"}";
		} catch (Exception e) {
			// Create JSON Object to show Error msg.
			output = "{\"status\":\"error\", \"data\": \"Error while Inserting Billing.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}

	// Update Customer
	public String updateEmployee(String eID, String eName, String eAddress, String eEmail,String eDate,
			String pno) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE billing SET eName=?,eAddress=?,eEmail=?,eDate=?,pno=? WHERE eID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, eName);
			preparedStmt.setString(2, eAddress);
			preparedStmt.setString(3, eEmail);
			preparedStmt.setString(4, eDate);
			preparedStmt.setString(5, pno);
			preparedStmt.setInt(6, Integer.parseInt(eID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			// create JSON object to show successful msg
			String newEmployee = readEmployee();
			output = "{\"status\":\"success\", \"data\": \"" + newEmployee + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while Updating Billing Details.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String deleteEmployee(String eID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "DELETE FROM billing WHERE eID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(eID));
			// execute the statement
			preparedStmt.execute();
			con.close();

			// create JSON Object
			String newEmployee = readEmployee();
			output = "{\"status\":\"success\", \"data\": \"" + newEmployee + "\"}";
		} catch (Exception e) {
			// Create JSON object
			output = "{\"status\":\"error\", \"data\": \"Error while Deleting Customer.\"}";
			System.err.println(e.getMessage());

		}

		return output;
	}

}
