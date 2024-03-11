package com.wipro.controller;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wipro.model.Book;
import com.wipro.service.MySqlService;
import com.wipro.service.impl.MySqlServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/BookServlet")
public class BookServlet extends HttpServlet {
	
	private static final Logger logger = LogManager.getLogger(HttpServlet.class);
	
	private static final long serialVersionUID = 4824519493477840594L;
	private MySqlService mySqlService;

	@Override
	public void init() throws ServletException {
		super.init();
		mySqlService = new MySqlServiceImpl();

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("doGet methods got called..");
		req.setAttribute("books", mySqlService.findAllBooks());
		req.getRequestDispatcher("book_list.jsp")
		.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("doPost method got called and action is: "+req.getParameter("action"));
		String action = req.getParameter("action");
		if(action.equalsIgnoreCase("delete")) {
			mySqlService.deleteBook(Integer.parseInt(req.getParameter("id")));
			req.getRequestDispatcher("delete.jsp").forward(req, resp);
		}
		if(action.equalsIgnoreCase("edit")) {
			Book book = mySqlService.getBookById(Integer.parseInt(req.getParameter("id")));
			req.setAttribute("book", book);
			req.getRequestDispatcher("edit.jsp").forward(req, resp);
		}
		if(action.equalsIgnoreCase("update")) {
			Book book = new Book();
			book.setId(Integer.parseInt(req.getParameter("bid")));
			book.setBookTitle(req.getParameter("btitle"));
			book.setAuthor(req.getParameter("bauthor"));
			book.setGenre(req.getParameter("bgenre"));
			book.setBookCopies(Integer.parseInt(req.getParameter("bcopies")));
			mySqlService.updateBook(book);
			req.getRequestDispatcher("update.jsp").forward(req, resp);
		}
		if(action.equalsIgnoreCase("add")) {
			Book book = new Book();
			book.setId(Integer.parseInt(req.getParameter("bid")));
			book.setBookTitle(req.getParameter("btitle"));
			book.setAuthor(req.getParameter("bauthor"));
			book.setGenre(req.getParameter("bgenre"));
			book.setBookCopies(Integer.parseInt(req.getParameter("bcopies")));
			
			mySqlService.addBook(book);
			req.getRequestDispatcher("add.jsp").forward(req, resp);
		}
		
	}
}
