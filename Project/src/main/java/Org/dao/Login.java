package Org.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Login extends GenericServlet {
	
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		String email=req.getParameter("email");
		String pass = req.getParameter("pass");
		
		String url = "jdbc:mysql://localhost:3306";
		String uname = "root";
		String pwd="root";
		String qry = "select email from ajay.student where email = ? and pass=?";
		
		PrintWriter pw = null;
		pw=res.getWriter();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, uname, pwd);
			PreparedStatement ps = con.prepareStatement(qry);
			ps.setString(1, email);
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();
			
		    if (rs.next()) {
		    	RequestDispatcher rd=req.getRequestDispatcher("Home.html");
			    rd.forward(req, res);
		    	//pw.println("Loging succeful");
				
			} else {
				pw.println("invalid email or password");

			}
		    
		} catch (ClassNotFoundException | SQLException e) {
		
			e.printStackTrace();
		}	
	}
}



