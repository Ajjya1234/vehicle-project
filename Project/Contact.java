package Org.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Contact extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		String fname=req.getParameter("firstname");
		String lname = req.getParameter("lastname");
		String country=req.getParameter("country");
		String subject=req.getParameter("Subject");
	
		PrintWriter pw = res.getWriter();
		
		String url = "jdbc:mysql://localhost:3306";
		String uname = "root";
		String pwd="root";
	//	String qry = "create table ajay.Coustomer(fname varchar(30),lname varchar(30),country varchar(30),Subject varchar(30))";
		String qry = "insert into ajay.Coustomer values(?,?,?,?)";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, uname, pwd);
			PreparedStatement ps = con.prepareStatement(qry);
			ps.setString(1, fname);
			ps.setString(2, lname);
			ps.setString(3, country);
			ps.setString(4, subject);

		    ps.executeUpdate();
		    System.out.println("table create succesfully");
		    PrintWriter writer=res.getWriter();
		    
//		    RequestDispatcher rd=req.getRequestDispatcher("Contact.html");
//		    rd.forward(req, res);
		    
			writer.println("data chala gaya hai");
		//    pw.println("<html><body><Button type='submit'>submit</button></a></body><html>");
		   
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
			
		//	 pw = res.getWriter();
		}
		
	}

}
