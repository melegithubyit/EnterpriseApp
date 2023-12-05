<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
	
	String userName = request.getParameter("un");
	String email = request.getParameter("email");
	String password = request.getParameter("pwd");
	
	try{
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/registration", "melak", "melake1994");
		Statement statement = con.createStatement();
		
		String query = "insert into user(`name`, `email`, `password`) values('"+userName+"', '"+email+"','"+password+"')";
		statement.executeUpdate(query);
		response.sendRedirect("confirmation.jsp");
		
		
		
	}catch(Exception e){
		System.out.print(e);
	}
	
	
	%>
</body>
</html>