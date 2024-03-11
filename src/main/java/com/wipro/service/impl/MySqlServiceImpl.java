package com.wipro.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wipro.model.Book;
import com.wipro.service.DatabaseConnection;
import com.wipro.service.MySqlService;

import jakarta.servlet.http.HttpServlet;

public class MySqlServiceImpl implements MySqlService {

	private static final Logger logger = LogManager.getLogger(MySqlServiceImpl.class);
	private static Connection connection;

	static {
		try {
			connection = DatabaseConnection.getConnection();
			initDb();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage());
		}
	}

	private static void initDb() throws SQLException {
		try {

			// Clear data from the books table
			clearTable("books");

			logger.info("Table data cleared successfully.");

			logger.info("Generating TEST data");
			int[] bookID = { 10, 20, 30, 40 };
			String[] bookTitles = { "Spring in action", "ReactJS Book", "Harry Potter", "The Sports Day" };
			String[] authorNames = { "Craig Walls", "Robin", "JK Rowling", "Sachin tendulkar" };
			String[] genre = { "Technology", "Technology", "Adventure", "Sport"};
			int[] booksCopies = { 200, 300, 100, 500 };

			String booksInsertQuery = "INSERT INTO books (id, title, author, genre, book_copies) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement booksInsertStatement = connection.prepareStatement(booksInsertQuery);
			for (int i = 0; i < bookID.length; i++) {
				booksInsertStatement.setInt(1, bookID[i]);
				booksInsertStatement.setString(2, bookTitles[i]);
				booksInsertStatement.setString(3, authorNames[i]);
				booksInsertStatement.setString(4, genre[i]);
				booksInsertStatement.setInt(5, booksCopies[i]);
				booksInsertStatement.addBatch();
			}
			booksInsertStatement.executeBatch();

			// Closing resources
			booksInsertStatement.close();
			// connection.close();

			logger.info("Books data inserted successfully.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Method to clear data from a table
	public static void clearTable(String tableName) throws SQLException {
		String deleteQuery = "DELETE FROM " + tableName;
		try (Statement statement = connection.createStatement()) {
			statement.executeUpdate(deleteQuery);
		}
	}

	@Override
	public List<Book> findAllBooks() {

		List<Book> books = new ArrayList<>();

		String query = "select * from books";

		try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {

			while (resultSet.next()) {

				Book book = new Book();
				book.setId(resultSet.getInt("id"));
				book.setBookTitle(resultSet.getString("title"));
				book.setAuthor(resultSet.getString("author"));
				book.setGenre(resultSet.getString("genre"));
				book.setBookCopies(resultSet.getInt("book_copies"));
				
				books.add(book);

			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return books;
	}

	@Override
	public void addBook(Book book) {

		try (PreparedStatement query = connection.prepareStatement("INSERT INTO books VALUES (?, ?, ?, ?, ?)");) {
			query.setInt(1, book.getId());
			query.setString(2, book.getBookTitle());
			query.setString(3, book.getAuthor());
			query.setString(4, book.getGenre());
			query.setInt(5, book.getBookCopies());
			
			query.executeUpdate();

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

	}

	@Override
	public void deleteBook(int bookId) {

		try (PreparedStatement query = connection.prepareStatement("DELETE FROM books WHERE id = ?");) {
			query.setInt(1, bookId);
			query.executeUpdate();

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

	}

	@Override
	public Book getBookById(int bookId) {
		Book book = new Book();

		try (PreparedStatement query = connection.prepareStatement("select * FROM books WHERE id = ?");) {
			query.setInt(1, bookId);
			ResultSet resultSet = query.executeQuery();

			while (resultSet.next()) {
				book.setId(resultSet.getInt("id"));
				book.setBookTitle(resultSet.getString("title"));
				book.setAuthor(resultSet.getString("author"));
				book.setGenre(resultSet.getString("genre"));
				book.setBookCopies(resultSet.getInt("book_copies"));
				
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return book;
	}

	@Override
	public void updateBook(Book book) {
		
		try (PreparedStatement query = connection.prepareStatement("update books set title=? , author=? , genre=? , "
				+ "book_copies=? where id =? ");) {
			
			query.setString(1, book.getBookTitle());
			query.setString(2, book.getAuthor());
			query.setString(3, book.getGenre());
			query.setInt(4, book.getBookCopies());
						
			query.setInt(5, book.getId());

			query.executeUpdate();

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		
	}

}
