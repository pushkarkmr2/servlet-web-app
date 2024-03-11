package com.wipro.service;

import java.util.List;
import com.wipro.model.Book;


public interface MySqlService {

	List<Book> findAllBooks();
	Book getBookById(int bookId);
	void addBook(Book book);
	void updateBook(Book book);
	void deleteBook(int bookId);

}