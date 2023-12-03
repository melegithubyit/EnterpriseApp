import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")

public class AddEmployeeServlet extends HttpServlet {
	
	private final static String query = "insert into employees(name, email, designation, salary) values(?,?,?,?)";
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		get the PrintWriter
		PrintWriter pw = res.getWriter();
// let set the content type
		res.setContentType("text/html");
		pw.println("<link rel='stylesheet' href = 'css/bootstrap.css'></link>");
		
		
//		 get the values
		String name = req.getParameter("userName");
		String email = req.getParameter("email");
		String designation = req.getParameter("designation");
		String salary = req.getParameter("salary");
		
//		lets load the JDBC driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(Exception e){
			e.printStackTrace();
		}
		
//		generate the connection
	
		try(Connection con = DriverManager.getConnection("jdbc:mysql:///employeemgt", "melak", "melake1994");
				PreparedStatement ps = con.prepareStatement(query);){
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, designation);
			ps.setString(4, salary);
			
//			execute the query
			
			int count = ps.executeUpdate();
			pw.println("<div class = 'card' style='margin:auto;width:300px;margin-top:300px'>");
			if(count == 1) {
				pw.println("<h2 class = 'bg-success text-light text-center'>Registered Successfully </h2>");
			}
			else {
				pw.println("<h2 class = 'bg-danger text-light text-center'>Failed to Register</h2>");
			}
			
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

