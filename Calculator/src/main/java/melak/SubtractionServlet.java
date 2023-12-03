package melak;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/subtraction")
public class SubtractionServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int num1 = Integer.parseInt(request.getParameter("num1"));
        int num2 = Integer.parseInt(request.getParameter("num2"));
        int diff = num1 - num2;

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h2>Result: " + diff + "</h2>");
        out.close();
    }
}