<%@page import="java.sql.ResultSet"%>
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
	String password = request.getParameter("pwd");
	
	try{
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/registration", "melak", "melake1994");
		Statement statement = con.createStatement();
		
		String query = "SELECT * FROM USER";
		ResultSet set = statement.executeQuery(query);
		int flag = 0;
		
		while (set.next()){
			if(userName.equals(set.getString(2)) && password.equals(set.getString(4))){
				flag = 1;
			}
		}
		
		if (flag == 1){
			response.sendRedirect("welcome.jsp");
		}else{
			response.sendRedirect("login.html");
		}
		
		
	}catch(Exception e){
		System.out.print(e);
	}
	
	
	%>
</body>
</html>