package com.wipro.controller;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/SecondServlet")
public class SecondServlet extends HttpServlet {

	private static final Logger logger = LogManager.getLogger(SecondServlet.class);
	private static final long serialVersionUID = 1899715868332784990L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("doGet method called.");
		response.setContentType("text/html");
		PrintWriter printwrite = response.getWriter();
		printwrite.println("<html>");
		printwrite.println("<head>");
		printwrite.println("<title> Using Annotations </title>");
		printwrite.println("</head>");
		printwrite.println("<body>");
		printwrite.println("<p> Using annotation based servlet configuration</p>");

		printwrite.println("</body>");
		printwrite.println("</html>");
	}
}
