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
@WebServlet("/update")

public class Update extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		String val1 = req.getParameter("otp");
		String val2 = req.getParameter("password");
		String val3 = req.getParameter("repeat-password");
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "S24@bag1996");
            PreparedStatement pst= con.prepareStatement("select * from form where otp = ?");
            pst.setString(1, val1);
            ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
            	Connection con1=null;
        		try {
        			Class.forName("com.mysql.cj.jdbc.Driver");
        			con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "S24@bag1996");
                    PreparedStatement pst1= con1.prepareStatement("update form set password=? where otp=?");
                    pst1.setString(1, val2);
                    pst1.setString(2, val1);
                    pst1.executeUpdate();

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
        		RequestDispatcher dispatcher = req.getRequestDispatcher("Update2.jsp");
        		dispatcher.forward(req, res);	
 		
            }
            else
            {
            	RequestDispatcher dispatcher = req.getRequestDispatcher("Update1.jsp");
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
