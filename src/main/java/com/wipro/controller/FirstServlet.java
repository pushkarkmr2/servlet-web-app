package com.wipro.controller;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FirstServlet extends HttpServlet {
	
	private static final Logger logger = LogManager.getLogger(FirstServlet.class);
	private static final long serialVersionUID = 4968418750213464445L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		config.getServletContext()
				.setAttribute("name", "Pushkar");
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("doGet method called.");
		response.setContentType("text/html");
		PrintWriter printwrite = response.getWriter();
		printwrite.println("<html>");
		printwrite.println("<head>");
		String greet = getServletConfig().getInitParameter("greeting");
		String name = getServletContext().getAttribute("name")
				.toString();
		printwrite.println("<title>" + greet + "</title>");
		printwrite.println("</head>");
		printwrite.println("<body>");
		printwrite.println("<h1>" + greet + "</h1>");
		printwrite.println("<h2>" + name + "</h2>");
		printwrite.println("</body>");
		printwrite.println("</html>");
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/SecondServlet");
		requestDispatcher.forward(request, response);

	}

}
