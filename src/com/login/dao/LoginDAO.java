package com.login.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;

public class LoginDAO {
	public String name,phone,id;
	String url= "jdbc:mysql://127.0.0.1:3306/webbuilder";
	String username = "root";
	String password = "12345678"; //Set your own MYSQL password
	String sqlCheckLogin = "select * from users where email=? and pword=?";
	
	public boolean checkCredentials(String uname, String pword)
	{
		try {
			/*Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, username, password);*/
		    String connectionURL = "jdbc:mysql://127.0.0.1:3306/webbuilder"; 
		    
		    
		    Class.forName("com.mysql.jdbc.Driver").newInstance(); 
		    
		    Connection con = DriverManager.getConnection(connectionURL, "root", "12345678");
			PreparedStatement statement = con.prepareStatement(sqlCheckLogin);
			statement.setString(1,uname);
			statement.setString(2, pword);
			ResultSet rs = statement.executeQuery();
			if(rs.next())
			{
				id=String.valueOf(rs.getInt(1));
				name=rs.getString(2);
				phone = rs.getString(4);
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
