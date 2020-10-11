package com.copyfolder.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Date ;
import java.text.SimpleDateFormat ; 
public class copyfolderdao {

		String url= "jdbc:mysql://127.0.0.1:3306/webbuilder";
		String username = "root";
		String password = "12345678"; //Set your own MYSQL password
		String sqlInsertData = "INSERT INTO project \r\n" + 
				"(\r\n" + 
				"`user_id`,\r\n" + 
				"`web_name`,\r\n" + 
				"`created_on`)\r\n" + 
				"VALUES(?,?,?)";
		
		public boolean insertData(String user_id, String web_name)
		{
			try {
				if(user_id==null || web_name==null )
					return false;
				else
				{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection(url, username, password);
					PreparedStatement statement = con.prepareStatement(sqlInsertData);

					SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd");
					String t_date=dateTimeInGMT.format(new Date());
					statement.setString(1,user_id);
					statement.setString(2, web_name);
					statement.setString(3, t_date);
					
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
