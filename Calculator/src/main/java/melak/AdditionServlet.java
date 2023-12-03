package melak;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addition")
public class AdditionServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int num1 = Integer.parseInt(request.getParameter("num1"));
        int num2 = Integer.parseInt(request.getParameter("num2"));
        int sum = num1 + num2;

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h2>Result: " + sum + "</h2>");
        out.close();
    }
}