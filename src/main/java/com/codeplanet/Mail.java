package com.codeplanet;

import java.io.IOException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/Mail")

public class Mail extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		
		String email = req.getParameter("email");
		SendMail mail = new SendMail();
		SecureRandom random = new SecureRandom();
		int num = random.nextInt(100000);
		String str = String.format("%05d", num);
		mail.send(email, "Forgot Password Email", "Your otp is: "+str);
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "S24@bag1996");
            PreparedStatement pst= con.prepareStatement("update form set otp=? where email=? ");
            pst.setString(1, str);
            pst.setString(2, email);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		req.setAttribute("email",email);
		RequestDispatcher dispatcher = req.getRequestDispatcher("Password.jsp");
		dispatcher.forward(req, res);
		
	}
}
