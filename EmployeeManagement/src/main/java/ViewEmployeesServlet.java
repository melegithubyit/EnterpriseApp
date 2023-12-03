import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/viewdata")

public class ViewEmployeesServlet extends HttpServlet {
	private final static String query = "select * from employees";
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		pw.println("<link rel='stylesheet' href = 'css/bootstrap.css'></link>");
		pw.println("<h1 class='text-center'>Employee list</h1>");
		
		
//		lets load the JDBC driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(Exception e){
			e.printStackTrace();
		}
		
//		generate the connection
		try(Connection con = DriverManager.getConnection("jdbc:mysql:///employeemgt", "melak", "melake1994");
				PreparedStatement ps = con.prepareStatement(query);){
			
//			result set
			ResultSet rs = ps.executeQuery();
			pw.println("<div style='margin:auto;margin-top:100px;width:800px'>");
			pw.println("<table class = 'table table-hover table-striped'>");
			pw.println("<tr>");
			pw.println("<th>ID</th>");
			pw.println("<th>Name</th>");
			pw.println("<th>Email</th>");
			pw.println("<th>Designation</th>");
			pw.println("<th>Salary</th>");
			pw.println("<th>Edit</th>");
			pw.println("<th>Delete</th>");
			pw.println("</tr>");
			
			while (rs.next()) {
			    pw.println("<tr>");
			    pw.println("<td>"+rs.getInt(1)+"</td>");
			    pw.println("<td>"+rs.getString(2)+"</td>");
			    pw.println("<td>"+rs.getString(3)+"</td>");
			    pw.println("<td>"+rs.getString(4)+"</td>");
			    pw.println("<td>"+rs.getString(5)+"</td>");
			    pw.println("<td><a href='editurl?id ="+rs.getString(1)+"'>Edit</a></td>");
			    pw.println("<td><a href='deleteurl?id ="+rs.getString(1)+"'>Delete</a></td>");
			    pw.println("</tr>"); // Correct closing tag
			}
			pw.println("</table>");
			
		}catch(SQLException se) {
			pw.println("<h2 class = 'bg-danger text-light text-center'>" + se.getMessage() + "</h2>");
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		pw.println("<a href='Home.html'><button class ='btn btn-outline-success text-center'>Home</button></a>");
		pw.println("</div>");
		pw.close();
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}
