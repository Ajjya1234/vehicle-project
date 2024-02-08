package Org.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import javax.servlet.RequestDispatcher;

public class Sign extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		String fname=req.getParameter("fname");
		String lname = req.getParameter("lname");
		String email=req.getParameter("email");
		String pass=req.getParameter("pass");
		String mno=req.getParameter("mno");
		PrintWriter pw = res.getWriter();
		
		String url = "jdbc:mysql://localhost:3306";
		String uname = "root";
		String pwd="root";
		String qry = "insert into ajay.student values(?,?,?,?,?)";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, uname, pwd);
			PreparedStatement ps = con.prepareStatement(qry);
			ps.setString(1, fname);
			ps.setString(2, lname);
			ps.setString(3, email);
			ps.setString(4, pass);
			ps.setString(5, mno);
		    ps.executeUpdate();
		    PrintWriter writer=res.getWriter();
		    
		    RequestDispatcher rd=req.getRequestDispatcher("Login.html");
		    rd.forward(req, res);
		    
//			writer.println("data chala gaya hai");
		    pw.println("<html><body><Button type='submit'>submit</button></a></body><html>");
		   
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
			
		//	 pw = res.getWriter();
		}
	}
}

