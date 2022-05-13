package model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Unit {
	
	int UnitId;
	String accountNo;
	String CusName;
	int unit; 
	String month;
	double amount;

	
	
	private Connection connect()
	{
		Connection con = null;
		try
			{
				Class.forName("com.mysql.jdbc.Driver");
				//Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/powerunit", "root", "");
			}
		catch (Exception e)
			{
				e.printStackTrace();
			}
			return con;
	}
	
	public String insertUnit(String accountNo, String CusName, String unit, String  month, String amount)
	{
	   String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
		{
			
			return "Error while connecting to the database for inserting."; 
		}
	
		  // create a prepared statement
			String query = " insert into units(`UnitID`,`AccountNo`,`CusName`,`Unit`,`Month`,`Amount`)"+ " values (?, ?, ?, ?, ?, ?)";
	        PreparedStatement preparedStmt = con.prepareStatement(query);
	       
	      // binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, accountNo);
			preparedStmt.setString(3, CusName);
			preparedStmt.setInt(4, Integer.parseInt(unit));
			preparedStmt.setString(5, month);
			preparedStmt.setDouble(6, Double.parseDouble(amount));
	
		  // execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		}
		catch (Exception e)
		{
			output = "Error while inserting the unit.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String readunit()
	{
		String output = "";
		  try
		  {
			  Connection con = connect();
			  if (con == null)
		  {	
			  return "Error while connecting to the database for reading."; 
	      }
			  
		  	// Prepare the html table to be displayed
			  output = "<table border='1'><tr><th>UnitId</th><th>AccountNO</th>"+"<th>User Name</th>" +
					  "<th>Usage Unit</th>" + "<th>Usage Month</th>" + 
					  "<th>Amount</th>" +
					  "<th>Update</th><th>Remove</th></tr>";
			  String query = "select * from units";
			  Statement stmt = con.createStatement();
			  ResultSet rs = stmt.executeQuery(query);
		
		   // iterate through the rows in the result set
		  
		  while (rs.next())
		  {
				String UnitID = Integer.toString(rs.getInt("UnitID"));
				String AccountNo = rs.getString("AccountNo");
				String CusName = rs.getString("CusName");
				String Unit = Integer.toString(rs.getInt("Unit"));
				String Month = rs.getString("Month");
				String Amount = Double.toString(rs.getDouble("Amount"));
				// Add into the html table
				output += "<tr><td>" + UnitID + "</td>";
				output += "<td>" + AccountNo + "</td>";
				output += "<td>" + CusName + "</td>";
				output += "<td>" + Unit + "</td>";
				output += "<td>" + Month + "</td>";
				output += "<td>" + Amount + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='submit' value='Update' class='btn btn-secondary' data-id='" + UnitID + "'></td>" + "<td>"
				+ "<form method='post' action='unit.jsp'>" + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger''"
						+ " "+ "data-itemid='" + UnitID + "'>"
				
				+ "</form></td></tr>";
		  }
				con.close();
				// Complete the html table
				output += "</table>";
		  }
		  catch (Exception e)
		  {
				output = "Error while reading the items.";
				System.err.println(e.getMessage());
		  }
		return output;
	}
	
	public String updateUnit(String ID, String accountNo, String CusName, String unit, String  month, String amount)
	{
		String output = "";
		  try
		  {
			  Connection con = connect();
			  if (con == null)
		  {
			  return "Error while connecting to the database for updating."; 
		  }
			  
			  // create a prepared statement
				  String query = "UPDATE units SET AccountNo=?,CusName=?,Unit=?,Month=?,Amount=? WHERE UnitID=?";
				  PreparedStatement preparedStmt = con.prepareStatement(query);
			  
			  // binding values
				  preparedStmt.setString(1, accountNo);
					preparedStmt.setString(2, CusName);
					preparedStmt.setInt(3, Integer.parseInt(unit));
					preparedStmt.setString(4, month);
					preparedStmt.setDouble(5, Double.parseDouble(amount));
			
				preparedStmt.setInt(6, Integer.parseInt(ID));
			 
			  // execute the statement
				preparedStmt.execute();
				con.close();
				output = "Updated successfully";
		  }
		  catch (Exception e)
		  {
				output = "Error while updating the unit.";
				System.err.println(e.getMessage());
		  }
		return output;
	}
			
	public String deleteUnit(String UnitID)
	{
		String output = "";
		  try
		  {
			  	Connection con = connect();
			  	if (con == null)
		  {
				return "Error while connecting to the database for deleting."; 
		  }
			  	
			// create a prepared statement
			  	String query = "delete from units where UnitID=?";
			  	PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			  	preparedStmt.setInt(1, Integer.parseInt(UnitID));
			
			// execute the statement
			  	preparedStmt.execute();
			  	con.close();
			  	output = "Deleted successfully";
		  }
		  catch (Exception e)
		  {
			  	output = "Error while deleting the unit.";
			  	System.err.println(e.getMessage());
		  }
		  return output;
			}

}
