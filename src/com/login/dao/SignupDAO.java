package com.login.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class SignupDAO {
	public String name,phone;
	String url= "jdbc:mysql://127.0.0.1:3306/webbuilder";
	String username = "root";
	String password = "12345678"; //Set your own MYSQL password
	String sqlInsertData = "insert into users (name,email,phone,pword) values (?,?,?,?)";
	
	public boolean insertData(String name, String uname, String phone, String pword)
	{
		try {
			if(name==null || uname==null || phone==null || pword ==null)
				return false;
			else
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection(url, username, password);
				PreparedStatement statement = con.prepareStatement(sqlInsertData);
				
				statement.setString(1,name);
				statement.setString(2, uname);
				statement.setString(3, phone);
				statement.setString(4, pword);
				
				int count = statement.executeUpdate();
				if(count>0)
					return true;
				else
					return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
