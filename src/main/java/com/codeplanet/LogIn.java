package com.codeplanet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
@WebServlet("/login")

public class LogIn extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		String val1 = req.getParameter("email");
		String val2 = req.getParameter("password");
		req.setAttribute("email", val2);
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "S24@bag1996");
            PreparedStatement pst= con.prepareStatement("select * from form where email = ?");
            pst.setString(1, val1);
            ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
            	if(val2.equals(rs.getString("password")))
            	{
            		RequestDispatcher dispatcher = req.getRequestDispatcher("SignIn.jsp");
            		dispatcher.forward(req, res);
            	}
            	else
            	{
            		RequestDispatcher dispatcher = req.getRequestDispatcher("SignIn1.jsp");
            		dispatcher.forward(req, res);	
            	} 		
            }
            else
            {
            	RequestDispatcher dispatcher = req.getRequestDispatcher("SignIn2.jsp");
        		dispatcher.forward(req, res);	
            }
            
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

		
	}

}
