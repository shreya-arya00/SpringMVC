package com.bobocode.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

/**
 * Servlet implementation class DateServlet
 * This servlet responds with the current date when accessed via the /date path.
 */
@WebServlet("/date")
public class DateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        LocalDate currentDate = LocalDate.now();

        PrintWriter out = response.getWriter();
        out.println("<html><body align=\"center\">");
        out.println("<h1>Current Date</h1>");
        out.println("<p>" + currentDate + "</p>");
        out.println("</body></html>");
    }
}
