package com.update;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.login.dao.SignupDAO;
import com.update.dao.UpdateDAO;

@WebServlet("/update")
public class update extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		UpdateDAO updateDAO = new UpdateDAO();
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String uname = request.getParameter("email");
		String pword = request.getParameter("pword");
		String id= String.valueOf(session.getAttribute("id"));
		
		if(updateDAO.insertData(id, name, uname, phone, pword))
		{
			session.setAttribute("updateSuccessful", "Values updated successfully");
			
			session.setAttribute("name", name);
			session.setAttribute("uname", uname);
			session.setAttribute("phone", phone);
			session.setAttribute("pword", pword);

			session.setAttribute("updateSuccessful", "Profile Updated Successfully");
			response.sendRedirect("Profile.jsp");
		}
		else {
			session.setAttribute("updateFailed", "Could not update data! Try again");
			response.sendRedirect("Profile.jsp");				
		}
	}

}
