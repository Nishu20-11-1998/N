package com.login;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.login.dao.LoginDAO;

@WebServlet("/login")
public class login extends HttpServlet {
	LoginDAO loginDAO = new LoginDAO(); //Object of JDBC Class
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
		{
			String uname = request.getParameter("uname");
			String pword = request.getParameter("pword");

			HttpSession session = request.getSession();
			
			if(loginDAO.checkCredentials(uname, pword))
			{
				session.setAttribute("uname", uname);
				session.setAttribute("pword", pword);
				session.setAttribute("id", loginDAO.id);
				session.setAttribute("name", loginDAO.name);
				session.setAttribute("phone", loginDAO.phone);
				
				response.sendRedirect("dashboard.jsp");
			}
			else {
				session.setAttribute("wrongCredentials", "Invalid credentials! Try again");
				response.sendRedirect("login.jsp");				
			}
		}
}
