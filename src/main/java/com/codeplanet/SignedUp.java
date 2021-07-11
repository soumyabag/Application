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
@WebServlet("/signedup")

public class SignedUp extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		String val = req.getParameter("email");
		String val1 = req.getParameter("username");
		String val2 = req.getParameter("psw");
		String otp=null;
		req.setAttribute("email", val);
		SendMail mail = new SendMail();
		SecureRandom random = new SecureRandom();
		int num = random.nextInt(100000);
		String str = String.format("%05d", num);
		mail.send(val, "Verification COde", "Your verification code for registration  is: "+str);
	
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "S24@bag1996");
            PreparedStatement pst= con.prepareStatement("insert into form values(?,?,?,?,?)");
			pst.setString(1, val);
			pst.setString(2, val2);
			pst.setString(3, val1);
			pst.setString(4, otp);
			pst.setString(5, str);
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
		RequestDispatcher dispatcher = req.getRequestDispatcher("Welcome1.jsp");
		dispatcher.forward(req, res);

		
}
	
}

