package dao;

import java.awt.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;

//import com.mysql.fabric.xmlrpc.base.Member;

import javafx.scene.control.TableColumn;
import dao.BankDAO.tblBill;
import dao.BankDAO.tblBook;
import dao.BankDAO.tblMember;
import dao.BankDAO.tblUser;
import model.Bill;
import model.Book;
import model.Novel;
import model.Periodical;
import model.memberRecords;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class BankDAO {

	// Connection object
	private Connection connection;
	// Database connection parameters
	private String url = "jdbc:mysql://www.papademas.net:3306/510labs";
	private String username = "db510";
	private String password = "510";

	int random = (int) (Math.random() * 500 + 1);

	public tblBook addBooks(Book book, Novel novel) {
		// Get a connection
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
		tblBook booktable = new tblBook();
		// Query to insert a record to the bank table
		String query = "INSERT INTO pthimmojibook (bookId,bookName, bookAuthor, noOfBooks, typeOfBooks, bookStatus, bookLimitDays) VALUES (?, ?, ?, ?, ?, ?, ?) ;";
		// Use prepared statements to avoid SQL injection attacks
		try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			// Set the parameters to the query
			statement.setInt(1, random);
			statement.setString(2, book.getBookName());
			statement.setString(3, book.getBookAuthor());
			statement.setInt(4, book.getNoOfBooks());
			statement.setString(5, book.getTypeOfBooks());
			statement.setString(6, book.getBookStatus());
			statement.setInt(7, book.getBookLimitDays());

			booktable.bookId.set(Integer.toString(random));
			booktable.bookName.set(book.getBookName());
			booktable.bookAuthor.set(book.getBookAuthor());
			booktable.noOfBooks.set(Integer.toString(book.getNoOfBooks()));
			booktable.typeOfBooks.set(book.getTypeOfBooks());
			booktable.bookStatus.set(book.getBookStatus());
			booktable.bookLimitDays.set(Integer.toString(book.getBookLimitDays()));

			// Execute the insert
			statement.executeUpdate();
			// To get the primary key (id) of the newly inserted record
			ResultSet resultSet = statement.getGeneratedKeys();

		} catch (SQLException e) {
			book = null;
			System.out.println("Error Creating Book: " + e);
		}

		String query1 = "INSERT INTO pthimmojinovel (bookId,genre, language, published, pages) VALUES (?, ?, ?, ?, ?) ;";

		/*
		 * Date DPCurrentDate1 =
		 * java.sql.Date.valueOf(dateText.getText()(novel.getPublished());
		 */ // Use prepared statements to avoid SQL injection attacks
		try (PreparedStatement statement = connection.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS)) {
			// Set the parameters to the query
			statement.setInt(1, random);
			statement.setString(2, novel.getGenre());
			statement.setString(3, novel.getLanguage());
			statement.setDate(4, (Date) novel.getPublished());
			statement.setInt(5, novel.getPages());

			booktable.genre.set(novel.getGenre());
			booktable.language.set(novel.getLanguage());

			String convertedDate = novel.getPublished().toString();
			System.out.println("converted is:" + convertedDate);

			booktable.published.set(convertedDate);
			booktable.pages.set(Integer.toString(novel.getPages()));

			// Execute the insert
			statement.executeUpdate();
			// To get the primary key (id) of the newly inserted record
			ResultSet resultSet = statement.getGeneratedKeys();

		} catch (SQLException e) {
			novel = null;
			System.out.println("Error Creating Novel: " + e);
		}
		finally{

			// Close the connection to the database - Very important!!!
			try {
	
				connection.close();
				connection = null;
			} catch (SQLException e) {
				System.out.println("Error closing connection: " + e);
			}
		}
		// Return the bank object that was inserted with the id field set.
		return booktable;
	}

	private ObservableList<ObservableList> data;

	public ObservableList<ObservableList> DevelopBook() {
		data = FXCollections.observableArrayList();
		try {
			try {
				connection = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				System.out.println("Error creating connection to database: " + e);
				System.exit(-1);
			}

			String query = "Select * from pthimmojibook;";
			ResultSet rs = connection.createStatement().executeQuery(query);

			while (rs.next()) {

				ObservableList<String> row = FXCollections.observableArrayList();

				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					// Iterate Column
					row.add(rs.getString(i));
				}
				System.out.println("Row [1] added " + row);
				data.add(row);

			}
			rs.close();

			/*
			 * table.setItems(data);
			 */ } catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		return data;
	}

	private ObservableList<Book> data1;

	public ObservableList<Book> Booksearch() {
		data = FXCollections.observableArrayList();
		try {
			try {
				connection = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				System.out.println("Error creating connection to database: " + e);
				System.exit(-1);
			}

			String query = "Select * from pthimmojibook;";
			ResultSet rs = connection.createStatement().executeQuery(query);

			while (rs.next()) {

				ObservableList<String> row = FXCollections.observableArrayList();

				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					// Iterate Column
					row.add(rs.getString(i));
				}
				System.out.println("Row [1] added " + row);
				data1.add((Book) row);

			}
			rs.close();

			/*
			 * table.setItems(data);
			 */ } catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		return data1;
	}

	public class tblBook {
		public SimpleStringProperty bookId = new SimpleStringProperty();
		public SimpleStringProperty bookName = new SimpleStringProperty();
		public SimpleStringProperty bookAuthor = new SimpleStringProperty();
		public SimpleStringProperty noOfBooks = new SimpleStringProperty();
		public SimpleStringProperty typeOfBooks = new SimpleStringProperty();
		public SimpleStringProperty bookStatus = new SimpleStringProperty();
		public SimpleStringProperty bookLimitDays = new SimpleStringProperty();
		public SimpleStringProperty genre = new SimpleStringProperty();
		public SimpleStringProperty pages = new SimpleStringProperty();
		public SimpleStringProperty published = new SimpleStringProperty();
		public SimpleStringProperty language = new SimpleStringProperty();
		public SimpleStringProperty format = new SimpleStringProperty();
		public SimpleStringProperty edition = new SimpleStringProperty();
		public SimpleStringProperty schedule = new SimpleStringProperty();
		public SimpleStringProperty memberId = new SimpleStringProperty();
		public SimpleStringProperty librarianId = new SimpleStringProperty();

		public String getBookId() {
			return bookId.get();
		}

		public String getBookName() {
			return bookName.get();
		}

		public String getBookAuthor() {
			return bookAuthor.get();
		}

		public String getNoOfBooks() {
			return noOfBooks.get();
		}

		public String getTypeOfBooks() {
			return typeOfBooks.get();
		}

		public String getBookStatus() {
			return bookStatus.get();
		}

		public String getBookLimitDays() {
			return bookLimitDays.get();
		}

		public String getGenre() {
			return genre.get();
		}

		public String getPages() {
			return pages.get();
		}

		public String getPublished() {
			return published.get();
		}

		public String getLanguage() {
			return language.get();
		}

		public String getFormat() {
			return format.get();
		}

		public String getSchedule() {
			return schedule.get();
		}

		public String getEdition() {
			return edition.get();
		}

		public String getMemberId() {
			return schedule.get();
		}

		public String getLibrarianId() {
			return edition.get();
		}

	}

	public class tblBill {

		public SimpleStringProperty billNo = new SimpleStringProperty();
		public SimpleStringProperty billDate = new SimpleStringProperty();
		public SimpleStringProperty billAmount = new SimpleStringProperty();
		public SimpleStringProperty bookId = new SimpleStringProperty();
		public SimpleStringProperty memberId = new SimpleStringProperty();
		public SimpleStringProperty librarianId = new SimpleStringProperty();
		public SimpleStringProperty dateIssued = new SimpleStringProperty();
		public SimpleStringProperty dateReturned = new SimpleStringProperty();
		public SimpleStringProperty billStatus = new SimpleStringProperty();

		public String getBookId() {
			return bookId.get();
		}

		public String getBillNo() {
			return billNo.get();
		}

		public String getBillDate() {
			return billDate.get();
		}

		public String getMemberId() {
			return memberId.get();
		}

		public String getLibrarianId() {
			return librarianId.get();
		}

		public String getDateIssued() {
			return dateIssued.get();
		}

		public String getDateReturned() {
			return dateReturned.get();
		}

		public String getBillAmount() {
			return billAmount.get();
		}

		public String getBillStatus() {
			return billStatus.get();
		}

	}

	public class Listlogin {

		public SimpleStringProperty username = new SimpleStringProperty();
		public SimpleStringProperty userpassword = new SimpleStringProperty();

		public String getUsername() {
			return username.get();
		}

		public String getUserpassword() {
			return userpassword.get();
		}

	}

	public class tblMember {
		public SimpleStringProperty memberId = new SimpleStringProperty();
		public SimpleStringProperty memberName = new SimpleStringProperty();
		public SimpleStringProperty memberAddress = new SimpleStringProperty();
		public SimpleStringProperty memberPhone = new SimpleStringProperty();
		public SimpleStringProperty typeOfMember = new SimpleStringProperty();
		public SimpleStringProperty noOfBooksIssued = new SimpleStringProperty();
		public SimpleStringProperty memberPassword = new SimpleStringProperty();

		public String getMemberId() {
			return memberId.get();
		}

		public String getMemberName() {
			return memberName.get();
		}

		public String getMemberAddress() {
			return memberAddress.get();
		}

		public String getMemberPhone() {
			return memberPhone.get();
		}

		public String getTypeOfMember() {
			return typeOfMember.get();
		}

		public String getNoOfBooksIssued() {
			return noOfBooksIssued.get();
		}

		public String getMemberPassword() {
			return memberPassword.get();
		}

	}

	public class tblUser {

		public SimpleStringProperty bookId = new SimpleStringProperty();
		public SimpleStringProperty bookName = new SimpleStringProperty();
		public SimpleStringProperty bookAuthor = new SimpleStringProperty();

		public SimpleStringProperty dateIssued = new SimpleStringProperty();

		public String getBookId() {
			return bookId.get();
		}

		public String getBookName() {
			return bookName.get();
		}

		public String getDateIssued() {
			return dateIssued.get();
		}

		public String getBookAuthor() {
			return bookAuthor.get();
		}

	}

	private ObservableList<tblBook> allProductsList;

	public ObservableList<tblBook> getAllGeneralProducts() {
		allProductsList = FXCollections.observableArrayList();
		try {
			try {
				connection = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				System.out.println("Error creating connection to database: " + e);
				System.exit(-1);
			}

			String query = "Select * from pthimmojibook b,pthimmojinovel n where b.bookId=n.bookId;";
			ResultSet rs = connection.createStatement().executeQuery(query);

			while (rs.next()) {

				tblBook booktable = new tblBook();
				booktable.bookId.set(rs.getString("bookId"));
				booktable.bookName.set(rs.getString("bookName"));
				booktable.bookAuthor.set(rs.getString("bookAuthor"));
				booktable.noOfBooks.set(rs.getString("noOfBooks"));
				booktable.typeOfBooks.set(rs.getString("typeOfBooks"));
				booktable.bookStatus.set(rs.getString("bookStatus"));
				booktable.bookLimitDays.set(rs.getString("bookLimitDays"));
				booktable.genre.set(rs.getString("genre"));
				booktable.pages.set(rs.getString("pages"));
				booktable.published.set(rs.getString("published"));
				booktable.language.set(rs.getString("language"));

				allProductsList.add(booktable);

			}
			rs.close();

			/*
			 * table.setItems(data);
			 */ } catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		return allProductsList;
	}

	public void delete(Book book) {

		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}

		// Query to insert a record to the book table
		String query = "delete from Book where bookId=? ;";
		// Use prepared statements to avoid SQL injection attacks
		try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			// Set the parameters to the query
			statement.setInt(1, book.getBookId());

			// Execute the insert
			statement.executeUpdate();
			// To get the primary key (id) of the newly inserted record

		} catch (SQLException e) {

			System.out.println("Error Creating Book: " + e);
		}
		finally{
		// Close the connection to the database - Very important!!!
		try {

			connection.close();
			connection = null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
		}
		}
		// Return the bank object that was inserted with the id field set.

	}

	public tblBook Update(Book book, Novel novel) {
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}

		tblBook booktable = new tblBook();

		String query = "update pthimmojibook b set b.bookName=?,b.bookAuthor=?,b.noOfBooks=?,b.typeOfBooks=?,b.bookStatus=?,b.bookLimitDays=? where bookId=? ;";
		// Use prepared statements to avoid SQL injection attacks
		try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			// Set the parameters to the query
			statement.setString(1, book.getBookName());
			statement.setString(2, book.getBookAuthor());
			statement.setInt(3, book.getNoOfBooks());
			statement.setString(4, book.getTypeOfBooks());
			statement.setString(5, book.getBookStatus());
			statement.setInt(6, book.getBookLimitDays());

			statement.setInt(7, book.getBookId());

			booktable.bookId.set(Integer.toString(book.getBookId()));
			booktable.bookName.set(book.getBookName());
			booktable.bookAuthor.set(book.getBookAuthor());
			booktable.noOfBooks.set(Integer.toString(book.getNoOfBooks()));
			booktable.typeOfBooks.set(book.getTypeOfBooks());
			booktable.bookStatus.set(book.getBookStatus());
			booktable.bookLimitDays.set(Integer.toString(book.getBookLimitDays()));

			// Execute the insert
			statement.executeUpdate();
			// To get the primary key (id) of the newly inserted record

		} catch (SQLException e) {

			System.out.println("Error Creating Book: " + e);
		}
		// Close the connection to the database - Very important!!!

		String query1 = "update pthimmojinovel set genre=?, language=?, published=?, pages=? where bookId=? ;";

		try (PreparedStatement statement = connection.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS)) {
			// Set the parameters to the query
			statement.setString(1, novel.getGenre());
			statement.setString(2, novel.getLanguage());
			statement.setDate(3, (Date) novel.getPublished());
			statement.setInt(4, novel.getPages());

			statement.setInt(5, book.getBookId());

			// Execute the insert
			statement.executeUpdate();

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
			Date date;

			// date.
			String convertedDate = novel.getPublished().toString();
			System.out.println("converted is:" + convertedDate);
			booktable.genre.set(novel.getGenre());
			booktable.pages.set(Integer.toString(novel.getPages()));
			System.out.println("pre strdate is " + novel.getPublished());
			// booktable.published.set(date.toString(novel.getPublished()));

			booktable.published.set(convertedDate);
			booktable.language.set(novel.getLanguage());

			// To get the primary key (id) of the newly inserted record
			ResultSet resultSet = statement.getGeneratedKeys();

		} catch (SQLException e) {
			novel = null;
			System.out.println("Error Creating Novel: " + e);
		}
		try {
			connection.close();
			connection = null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
		}
		// Return the bank object that was inserted with the id field set.

		return booktable;

	}

	public tblBook AddReference(Book book, Periodical periodical) {
		// Get a connection
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
		tblBook booktable = new tblBook();

		// Query to insert a record to the bank table
		String query = "INSERT INTO pthimmojibook (bookId,bookName, bookAuthor, noOfBooks, typeOfBooks, bookStatus, bookLimitDays) VALUES (?, ?, ?, ?, ?, ?, ?) ;";
		// Use prepared statements to avoid SQL injection attacks
		try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			// Set the parameters to the query
			statement.setInt(1, random);
			statement.setString(2, book.getBookName());
			statement.setString(3, book.getBookAuthor());
			statement.setInt(4, book.getNoOfBooks());
			statement.setString(5, book.getTypeOfBooks());
			statement.setString(6, book.getBookStatus());
			statement.setInt(7, book.getBookLimitDays());

			booktable.bookId.set(Integer.toString(random));
			booktable.bookName.set(book.getBookName());
			booktable.bookAuthor.set(book.getBookAuthor());
			booktable.noOfBooks.set(Integer.toString(book.getNoOfBooks()));
			booktable.typeOfBooks.set(book.getTypeOfBooks());
			booktable.bookStatus.set(book.getBookStatus());
			booktable.bookLimitDays.set(Integer.toString(book.getBookLimitDays()));

			// Execute the insert
			statement.executeUpdate();
			// To get the primary key (id) of the newly inserted record
			ResultSet resultSet = statement.getGeneratedKeys();

		} catch (SQLException e) {
			book = null;
			System.out.println("Error Creating Book: " + e);
		}

		String query1 = "INSERT INTO pthimmojiperiodical (bookId,format, schedule, edition) VALUES (?, ?, ?, ?) ;";

		/*
		 * Date DPCurrentDate1 =
		 * java.sql.Date.valueOf(dateText.getText()(novel.getPublished());
		 */ // Use prepared statements to avoid SQL injection attacks
		try (PreparedStatement statement = connection.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS)) {
			// Set the parameters to the query
			statement.setInt(1, random);
			statement.setString(2, periodical.getFormat());
			statement.setString(3, periodical.getSchedule());
			statement.setInt(4, periodical.getEdition());

			booktable.format.set(periodical.getFormat());
			booktable.schedule.set(periodical.getSchedule());

			booktable.edition.set(Integer.toString(periodical.getEdition()));

			// Execute the insert
			statement.executeUpdate();
			// To get the primary key (id) of the newly inserted record
			ResultSet resultSet = statement.getGeneratedKeys();

		} catch (SQLException e) {
			periodical = null;
			System.out.println("Error Creating Novel: " + e);
		}
finally{
		// Close the connection to the database - Very important!!!
		try {

			connection.close();
			connection = null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
		}
		}
		// Return the bank object that was inserted with the id field set.
		return booktable;

	}

	public ObservableList<tblBook> getAllReferenceProducts() {

		allProductsList = FXCollections.observableArrayList();
		try {
			try {
				connection = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				System.out.println("Error creating connection to database: " + e);
				System.exit(-1);
			}

			String query = "Select * from pthimmojibook b,pthimmojiperiodical n where b.bookId=n.bookId;";
			ResultSet rs = connection.createStatement().executeQuery(query);

			while (rs.next()) {

				tblBook booktable = new tblBook();
				booktable.bookId.set(rs.getString("bookId"));
				booktable.bookName.set(rs.getString("bookName"));
				booktable.bookAuthor.set(rs.getString("bookAuthor"));
				booktable.noOfBooks.set(rs.getString("noOfBooks"));
				booktable.typeOfBooks.set(rs.getString("typeOfBooks"));
				booktable.bookStatus.set(rs.getString("bookStatus"));
				booktable.bookLimitDays.set(rs.getString("bookLimitDays"));
				booktable.format.set(rs.getString("format"));
				booktable.schedule.set(rs.getString("schedule"));
				booktable.edition.set(rs.getString("edition"));

				allProductsList.add(booktable);

			}
			rs.close();

			/*
			 * table.setItems(data);
			 */ } catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		return allProductsList;
	}

	public tblBook updateReference(Book book, Periodical periodical) {
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}

		tblBook booktable = new tblBook();

		// Query to insert a record to the bank table
		String query = "update pthimmojibook b set b.bookName=?,b.bookAuthor=?,b.noOfBooks=?,b.typeOfBooks=?,b.bookStatus=?,b.bookLimitDays=? where bookId=? ;";
		// Use prepared statements to avoid SQL injection attacks
		try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			// Set the parameters to the query
			statement.setString(1, book.getBookName());
			statement.setString(2, book.getBookAuthor());
			statement.setInt(3, book.getNoOfBooks());
			statement.setString(4, book.getTypeOfBooks());
			statement.setString(5, book.getBookStatus());
			statement.setInt(6, book.getBookLimitDays());

			statement.setInt(7, book.getBookId());

			booktable.bookId.set(Integer.toString(book.getBookId()));
			booktable.bookName.set(book.getBookName());
			booktable.bookAuthor.set(book.getBookAuthor());
			booktable.noOfBooks.set(Integer.toString(book.getNoOfBooks()));
			booktable.typeOfBooks.set(book.getTypeOfBooks());
			booktable.bookStatus.set(book.getBookStatus());
			booktable.bookLimitDays.set(Integer.toString(book.getBookLimitDays()));

			// Execute the insert
			statement.executeUpdate();
			// To get the primary key (id) of the newly inserted record

		} catch (SQLException e) {

			System.out.println("Error Creating Book: " + e);
		}
		// Close the connection to the database - Very important!!!

		String query1 = "update pthimmojiperiodical set format=?, schedule=?, edition=? where bookId=? ;";

		// Date DPCurrentDate1 =
		// java.sql.Date.valueOf(dateText.getText()(novel.getPublished());
		// Use prepared statements to avoid SQL injection attacks
		try (PreparedStatement statement = connection.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS)) {
			// Set the parameters to the query
			statement.setString(1, periodical.getFormat());
			statement.setString(2, periodical.getSchedule());
			statement.setInt(3, periodical.getEdition());

			statement.setInt(4, book.getBookId());

			// Execute the insert
			statement.executeUpdate();

			booktable.format.set(periodical.getFormat());
			booktable.schedule.set(periodical.getSchedule());

			booktable.edition.set(Integer.toString(periodical.getEdition()));

			// To get the primary key (id) of the newly inserted record
			ResultSet resultSet = statement.getGeneratedKeys();

		} catch (SQLException e) {
			periodical = null;
			System.out.println("Error Creating Novel: " + e);
		}
		finally{
		try {
			connection.close();
			connection = null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
		}
		}
		// Return the bank object that was inserted with the id field set.

		return booktable;

	}

	private ObservableList<tblMember> allMembersList;

	public ObservableList<tblMember> getAllMemberDetails() {
		allMembersList = FXCollections.observableArrayList();
		try {
			try {
				connection = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				System.out.println("Error creating connection to database: " + e);
				System.exit(-1);
			}

			String query = "Select * from pthimmojimemberrecords;";
			ResultSet rs = connection.createStatement().executeQuery(query);

			while (rs.next()) {

				tblMember membertable = new tblMember();

				membertable.memberId.set(rs.getString("memberId"));
				membertable.memberName.set(rs.getString("memberName"));
				membertable.memberAddress.set(rs.getString("memberAddress"));
				membertable.memberPhone.set(rs.getString("memberPhone"));
				membertable.noOfBooksIssued.set(rs.getString("noOfBooksIssued"));
				membertable.memberPassword.set(rs.getString("memberPassword"));
				membertable.typeOfMember.set(rs.getString("typeOfMember"));

				allMembersList.add(membertable);

			}
			rs.close();

			/*
			 * table.setItems(data);
			 */ } catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		return allMembersList;
	}

	public tblMember AddMember(memberRecords member) {
		// TODO Auto-generated method stub

		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}

		tblMember membertable = new tblMember();
		String memberPassword="password";
		// Query to insert a record to the bank table
		String query = "INSERT INTO pthimmojimemberrecords (memberId,memberName, memberAddress, memberPhone, typeOfMember, NoofBooksIssued, memberPassword) VALUES (?, ?, ?, ?, ?, ?, ?) ;";
		// Use prepared statements to avoid SQL injection attacks
		try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			// Set the parameters to the query
			statement.setInt(1, random);
			statement.setString(2, member.getMemberName());
			statement.setString(3, member.getMemberAddress());
			statement.setLong(4, member.getMemberPhone());
			statement.setString(5, member.getTypeOfMember());
			statement.setInt(6, member.getNoOfBooksIssued());
			statement.setString(7, "pass");

			membertable.memberId.set(Integer.toString(random));
			membertable.memberName.set(member.getMemberName());
			membertable.memberAddress.set(member.getMemberAddress());
			membertable.memberPhone.set(Long.toString(member.getMemberPhone()));
			membertable.noOfBooksIssued.set(Integer.toString((member.getNoOfBooksIssued())));
			membertable.memberPassword.set(memberPassword);
			//membertable.memberPassword.set(member.getMemberPassword());
			membertable.typeOfMember.set(member.getTypeOfMember());

			// Execute the insert
			statement.executeUpdate();
			// To get the primary key (id) of the newly inserted record
			ResultSet resultSet = statement.getGeneratedKeys();

		} catch (SQLException e) {
			member = null;
			System.out.println("Error Creating Member: " + e);

		}

		return membertable;

	}

	public tblMember updateMember(memberRecords member) {
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}

		tblMember membertable = new tblMember();

		// Date today = (Date) Calendar.getInstance().getTime();

		// Query to insert a record to the bank table
		String query = "update pthimmojimemberrecords b set b.memberName=?,b.memberAddress=?,b.memberPhone=?,b.noOfBooksIssued=?,b.typeOfMember=? where memberId=? ;";
		// Use prepared statements to avoid SQL injection attacks
		try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			// Set the parameters to the query
			statement.setString(1, member.getMemberName());
			statement.setString(2, member.getMemberAddress());
			statement.setLong(3, member.getMemberPhone());
			statement.setInt(4, member.getNoOfBooksIssued());
			statement.setString(5, member.getTypeOfMember());

			statement.setInt(6, member.getMemberId());

			membertable.memberId.set(Integer.toString(member.getMemberId()));
			membertable.memberName.set(member.getMemberName());
			membertable.memberAddress.set(member.getMemberAddress());
			membertable.memberPhone.set(Long.toString(member.getMemberPhone()));
			membertable.noOfBooksIssued.set(Integer.toString((member.getNoOfBooksIssued())));
			membertable.typeOfMember.set(member.getTypeOfMember());

			// Execute the insert
			statement.executeUpdate();
			// To get the primary key (id) of the newly inserted record

		} catch (SQLException e) {

			System.out.println("Error Creating Book: " + e);
		}
		finally{
		// Close the connection to the database - Very important!!!

		try {
			connection.close();
			connection = null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
		}}
		// Return the bank object that was inserted with the id field set.

		return membertable;
	}

	public boolean issueBook(Bill bill) {
		// TODO Auto-generated method stub
		boolean result = true;
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}

		tblBill billtable = new tblBill();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

		// Date today = (java.sql.Date) Calendar.getInstance().getTime();

		// System.out.println("todays date is"+today);

		// String mydate=dateFormat.format(today);

		// create a java calendar instance
		Calendar calendar = Calendar.getInstance();
		String unpaid = "U";

		// get a java date (java.util.Date) from the Calendar instance.
		// this java date will represent the current date, or "now".
		java.util.Date currentDate = calendar.getTime();

		// now, create a java.sql.Date from the java.util.Date
		java.sql.Date date = new java.sql.Date(currentDate.getTime());

		// Query to insert a record to the bank table
		String query = "INSERT INTO pthimmojibill (billNo,bookId, memberId, libId, dateIssued) VALUES (?, ?, ?, ?, ?) ;";
		// Use prepared statements to avoid SQL injection attacks
		try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			// Set the parameters to the query
			statement.setInt(1, random);
			statement.setInt(2, bill.getBookId());
			statement.setInt(3, bill.getMemberId());
			statement.setInt(4, bill.getLibId());
			statement.setDate(5, date);

			// Execute the insert
			statement.executeUpdate();
			// To get the primary key (id) of the newly inserted record
			ResultSet resultSet = statement.getGeneratedKeys();

		} catch (SQLException e) {
			bill = null;
			result = false;
			System.out.println("Error Creating Member: " + e);

		}

		return result;

	}

	private ObservableList<tblBill> allBillList;

	public ObservableList<tblBill> getBilldetails() {
		allBillList = FXCollections.observableArrayList();
		try {
			try {
				connection = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				System.out.println("Error creating connection to database: " + e);
				System.exit(-1);
			}

			String query = "Select * from pthimmojibill;";
			ResultSet rs = connection.createStatement().executeQuery(query);

			while (rs.next()) {

				tblBill billtable = new tblBill();

				billtable.billNo.set(rs.getString("billNo"));
				billtable.billDate.set(rs.getString("billDate"));
				billtable.billAmount.set(rs.getString("billAmount"));
				billtable.memberId.set(rs.getString("memberId"));

				billtable.bookId.set(rs.getString("bookId"));
				billtable.librarianId.set(rs.getString("libId"));
				billtable.dateIssued.set(rs.getString("dateIssued"));
				billtable.dateReturned.set(rs.getString("dateReturned"));

				allBillList.add(billtable);

			}
			rs.close();

			/*
			 * table.setItems(data);
			 */ } catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		return allBillList;
	}

	public tblBill updateBill(Bill bill) {
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}

		tblBill billtable = new tblBill();

		// Query to insert a record to the bank table
		String query = "update pthimmojigeneratedbill b set b.billDate=?,b.billAmount=?,b.bookId=?,b.memberId=?,b.libId=?,b.dateIssued=?,b.dateReturned=?,b.billStatus=? where billNo=? ;";
		// Use prepared statements to avoid SQL injection attacks
		try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			// Set the parameters to the query

			System.out.println("bill status in DAo" + bill.getBillStatus());

			statement.setDate(1, (Date) bill.getBillDate());
			statement.setFloat(2, bill.getBillAmount());
			statement.setInt(3, bill.getBookId());
			statement.setInt(4, bill.getMemberId());
			statement.setInt(5, bill.getLibId());
			statement.setDate(6, (Date) bill.getDateIssued());
			statement.setDate(7, (Date) bill.getDateReturned());
			statement.setString(8, bill.getBillStatus());

			statement.setInt(9, bill.getBillNo());

			billtable.billNo.set(Integer.toString(bill.getBillNo()));

			billtable.bookId.set(Integer.toString(bill.getBookId()));
			billtable.billAmount.set(Float.toString(bill.getBillAmount()));
			billtable.memberId.set(Integer.toString(bill.getMemberId()));
			billtable.librarianId.set(Integer.toString(bill.getLibId()));
			String convertedBillDate = bill.getBillDate().toString();
			String convertedIssDate = bill.getDateIssued().toString();
			String convertedRetDate = bill.getDateReturned().toString();
			billtable.billDate.set(convertedBillDate);
			billtable.dateIssued.set(convertedIssDate);
			billtable.dateReturned.set(convertedRetDate);
			billtable.billStatus.set(bill.getBillStatus());

			// Execute the insert
			statement.executeUpdate();
			// To get the primary key (id) of the newly inserted record

		} catch (SQLException e) {

			System.out.println("Error Creating Book: " + e);
		}
finally{
		// Close the connection to the database - Very important!!!

		try {
			connection.close();
			connection = null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
		}
		// Return the bank object that was inserted with the id field set.
}
		return billtable;
	}

	public boolean createBill(Bill bill) {
		// TODO Auto-generated method stub
		boolean result = true;
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}

		tblBill billtable = new tblBill();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

		// Date today = (java.sql.Date) Calendar.getInstance().getTime();

		// System.out.println("todays date is"+today);

		// String mydate=dateFormat.format(today);

		// create a java calendar instance
		Calendar calendar = Calendar.getInstance();

		// get a java date (java.util.Date) from the Calendar instance.
		// this java date will represent the current date, or "now".
		java.util.Date currentDate = calendar.getTime();
		String unpaid = "U";
		// now, create a java.sql.Date from the java.util.Date
		java.sql.Date date = new java.sql.Date(currentDate.getTime());

		// Query to insert a record to the bank table
		String query = "INSERT INTO pthimmojigeneratedbill (billNo,billDate,billAmount,bookId, memberId, libId, dateIssued,dateReturned,billStatus) VALUES (?, ?, ?, ?,?,?,?,?,?) ;";
		// Use prepared statements to avoid SQL injection attacks
		try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			// Set the parameters to the query
			statement.setInt(1, bill.getBillNo());
			statement.setDate(2, (Date) bill.getBillDate());
			statement.setFloat(3, bill.getBillAmount());
			statement.setInt(4, bill.getBookId());
			statement.setInt(5, bill.getMemberId());
			statement.setInt(6, bill.getLibId());

			statement.setDate(7, (Date) bill.getDateIssued());
			statement.setDate(8, (Date) bill.getDateReturned());
			//statement.setString(9, bill.getBillStatus());
			statement.setString(9, unpaid);

			// Execute the insert
			statement.executeUpdate();
			// To get the primary key (id) of the newly inserted record
			ResultSet resultSet = statement.getGeneratedKeys();

		} catch (SQLException e) {
			bill = null;
			result = false;
			System.out.println("Error Creating Member: " + e);

		}

		String query1 = "delete from pthimmojibill where billNo=? ;";
		// Use prepared statements to avoid SQL injection attacks
		try (PreparedStatement statement = connection.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS)) {
			// Set the parameters to the query
			statement.setInt(1, bill.getBillNo());

			// Execute the insert
			statement.executeUpdate();
			// To get the primary key (id) of the newly inserted record
			ResultSet resultSet = statement.getGeneratedKeys();

		} catch (SQLException e) {
			bill = null;
			result = false;
			System.out.println("Error Creating Member: " + e);

		}

		return result;
	}

	private ObservableList<tblBill> allPostBillList;

	public ObservableList<tblBill> getCreatedBillDetails() {
		allPostBillList = FXCollections.observableArrayList();
		try {
			try {
				connection = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				System.out.println("Error creating connection to database: " + e);
				System.exit(-1);
			}

			String query = "Select * from pthimmojigeneratedbill;";
			ResultSet rs = connection.createStatement().executeQuery(query);

			while (rs.next()) {

				tblBill billtable = new tblBill();

				billtable.billNo.set(rs.getString("billNo"));
				billtable.billDate.set(rs.getString("billDate"));
				billtable.billAmount.set(rs.getString("billAmount"));
				billtable.memberId.set(rs.getString("memberId"));

				billtable.bookId.set(rs.getString("bookId"));
				billtable.librarianId.set(rs.getString("libId"));
				billtable.dateIssued.set(rs.getString("dateIssued"));
				billtable.dateReturned.set(rs.getString("dateReturned"));
				billtable.billStatus.set(rs.getString("billStatus"));

				allPostBillList.add(billtable);

			}
			rs.close();

			/*
			 * table.setItems(data);
			 */ } catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		return allPostBillList;
	}

	public Integer getSumBooks(Bill bill) {
		// allBillList = FXCollections.observableArrayList();
		int number = 0;
		PreparedStatement ps = null;
		try {
			try {
				connection = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				System.out.println("Error creating connection to database: " + e);
				System.exit(-1);
			}

			ps = connection.prepareStatement("Select * from pthimmojibook where bookId=?;");
			ps.setInt(1, bill.getBookId());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				number = Integer.parseInt(rs.getString("noOfBooks"));

			}
			rs.close();

			/*
			 * table.setItems(data);
			 */ } catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		return number;
	}

	public tblBook setNumberOfBooks(Integer num, Book book, Novel novel) {
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}

		System.out.println("number of books is " + num);
		tblBill billtable = new tblBill();
		tblBook booktable = new tblBook();
		// Query to insert a record to the bank table
		String query = "update pthimmojibook b set b.noOfBooks=? where b.bookId=? ;";
		// Use prepared statements to avoid SQL injection attacks
		try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			// Set the parameters to the query

			statement.setInt(1, num);

			statement.setInt(2, book.getBookId());

			booktable.bookId.set(Integer.toString(book.getBookId()));
			booktable.bookName.set(book.getBookName());
			booktable.bookAuthor.set(book.getBookAuthor());
			booktable.noOfBooks.set(Integer.toString(num));
			booktable.typeOfBooks.set(book.getTypeOfBooks());
			booktable.bookStatus.set(book.getBookStatus());
			booktable.bookLimitDays.set(Integer.toString(book.getBookLimitDays()));

			String convertedDate = novel.getPublished().toString();
			System.out.println("converted is:" + convertedDate);
			booktable.genre.set(novel.getGenre());
			booktable.pages.set(Integer.toString(novel.getPages()));
			System.out.println("pre strdate is " + novel.getPublished());
			// booktable.published.set(date.toString(novel.getPublished()));

			booktable.published.set(convertedDate);
			booktable.language.set(novel.getLanguage());

			// Execute the insert
			statement.executeUpdate();
			// To get the primary key (id) of the newly inserted record

		} catch (SQLException e) {

			System.out.println("Error Creating Book: " + e);
		}
		finally{
		// Close the connection to the database - Very important!!!

		try {
			connection.close();
			connection = null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
		}
		}
		// Return the bank object that was inserted with the id field set.

		return booktable;

	}

	public tblBook setNumberOfReferenceBooks(Integer num, Book book, Periodical periodical) {
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}

		System.out.println("number of books is " + num);
		tblBill billtable = new tblBill();
		tblBook booktable = new tblBook();
		// Query to insert a record to the bank table
		String query = "update pthimmojibook b set b.noOfBooks=? where b.bookId=? ;";
		// Use prepared statements to avoid SQL injection attacks
		try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			// Set the parameters to the query

			statement.setInt(1, num);

			statement.setInt(2, book.getBookId());

			booktable.bookId.set(Integer.toString(book.getBookId()));
			booktable.bookName.set(book.getBookName());
			booktable.bookAuthor.set(book.getBookAuthor());
			booktable.noOfBooks.set(Integer.toString(num));
			booktable.typeOfBooks.set(book.getTypeOfBooks());
			booktable.bookStatus.set(book.getBookStatus());
			booktable.bookLimitDays.set(Integer.toString(book.getBookLimitDays()));
			booktable.format.set(periodical.getFormat());
			booktable.schedule.set(periodical.getSchedule());

			booktable.edition.set(Integer.toString(periodical.getEdition()));

			// Execute the insert
			statement.executeUpdate();
			// To get the primary key (id) of the newly inserted record

		} catch (SQLException e) {

			System.out.println("Error Creating Book: " + e);
		}
		finally{
		// Close the connection to the database - Very important!!!

		try {
			connection.close();
			connection = null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
		}}
		// Return the bank object that was inserted with the id field set.

		return booktable;

	}

	private ObservableList<tblUser> allIssuedList;

	public ObservableList<tblUser> getUserIssuedBooks() {
		allIssuedList = FXCollections.observableArrayList();
		try {
			try {
				connection = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				System.out.println("Error creating connection to database: " + e);
				System.exit(-1);
			}
			PreparedStatement ps = null;

			ps = connection.prepareStatement(
					"select bk.bookid,bk.bookname,bk.bookauthor,b.dateissued from pthimmojibill b,pthimmojibook bk where b.bookid=bk.bookid and b.memberid=?;");
			ps.setInt(1, 4);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				tblUser usertable = new tblUser();

				usertable.bookId.set(rs.getString("bookId"));
				usertable.bookName.set(rs.getString("bookName"));
				usertable.bookAuthor.set(rs.getString("bookAuthor"));
				usertable.dateIssued.set(rs.getString("dateIssued"));

				allIssuedList.add(usertable);

			}
			rs.close();

			/*
			 * table.setItems(data);
			 */ } catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		return allIssuedList;
	}

	public ObservableList<tblUser> getUserIssuedBooks(Integer id) {
		allIssuedList = FXCollections.observableArrayList();
		try {
			try {
				connection = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				System.out.println("Error creating connection to database: " + e);
				System.exit(-1);
			}
			PreparedStatement ps = null;

			ps = connection.prepareStatement(
					"select bk.bookid,bk.bookname,bk.bookauthor,b.dateissued from pthimmojibill b,pthimmojibook bk where b.bookid=bk.bookid and b.memberid=?;");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				tblUser usertable = new tblUser();

				usertable.bookId.set(rs.getString("bookId"));
				usertable.bookName.set(rs.getString("bookName"));
				usertable.bookAuthor.set(rs.getString("bookAuthor"));
				usertable.dateIssued.set(rs.getString("dateIssued"));

				allIssuedList.add(usertable);

			}
			rs.close();

			/*
			 * table.setItems(data);
			 */ } catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		return allIssuedList;
	}

	public ObservableList<tblBill> GetTransactionBill() {
		allPostBillList = FXCollections.observableArrayList();
		PreparedStatement ps = null;

		try {
			try {
				connection = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				System.out.println("Error creating connection to database: " + e);
				System.exit(-1);
			}

			ps = connection.prepareStatement("Select * from pthimmojigeneratedbill g where g.memberId=? ");
			ps.setInt(1, 4);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				tblBill billtable = new tblBill();

				billtable.billNo.set(rs.getString("billNo"));
				billtable.billDate.set(rs.getString("billDate"));
				billtable.billAmount.set(rs.getString("billAmount"));
				billtable.memberId.set(rs.getString("memberId"));

				billtable.bookId.set(rs.getString("bookId"));
				billtable.librarianId.set(rs.getString("libId"));
				billtable.dateIssued.set(rs.getString("dateIssued"));
				billtable.dateReturned.set(rs.getString("dateReturned"));
				billtable.billStatus.set(rs.getString("billStatus"));

				allPostBillList.add(billtable);

			}
			rs.close();

			/*
			 * table.setItems(data);
			 */ } catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		return allPostBillList;
	}

	public ObservableList<tblBill> GetTransactionBill(Integer id) {
		allPostBillList = FXCollections.observableArrayList();
		PreparedStatement ps = null;

		try {
			try {
				connection = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				System.out.println("Error creating connection to database: " + e);
				System.exit(-1);
			}

			ps = connection.prepareStatement("Select * from pthimmojigeneratedbill g where g.memberId=? ");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				tblBill billtable = new tblBill();

				billtable.billNo.set(rs.getString("billNo"));
				billtable.billDate.set(rs.getString("billDate"));
				billtable.billAmount.set(rs.getString("billAmount"));
				billtable.memberId.set(rs.getString("memberId"));

				billtable.bookId.set(rs.getString("bookId"));
				billtable.librarianId.set(rs.getString("libId"));
				billtable.dateIssued.set(rs.getString("dateIssued"));
				billtable.dateReturned.set(rs.getString("dateReturned"));
				billtable.billStatus.set(rs.getString("billStatus"));

				allPostBillList.add(billtable);

			}
			rs.close();

			/*
			 * table.setItems(data);
			 */ } catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		return allPostBillList;
	}

	public tblBill payBill(Bill bill) {
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}

		tblBill billtable = new tblBill();

		String billpaid = "P";

		// Query to insert a record to the bank table
		String query = "update pthimmojigeneratedbill b set b.billStatus=? where billNo=? ;";
		// Use prepared statements to avoid SQL injection attacks
		try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			// Set the parameters to the query

			statement.setString(1, billpaid);

			statement.setInt(2, bill.getBillNo());

			billtable.billNo.set(Integer.toString(bill.getBillNo()));

			billtable.bookId.set(Integer.toString(bill.getBookId()));
			billtable.billAmount.set(Float.toString(bill.getBillAmount()));
			billtable.memberId.set(Integer.toString(bill.getMemberId()));
			billtable.librarianId.set(Integer.toString(bill.getLibId()));
			String convertedBillDate = bill.getBillDate().toString();
			String convertedIssDate = bill.getDateIssued().toString();
			String convertedRetDate = bill.getDateReturned().toString();
			billtable.billDate.set(convertedBillDate);
			billtable.dateIssued.set(convertedIssDate);
			billtable.dateReturned.set(convertedRetDate);
			billtable.billStatus.set(billpaid);

			// Execute the insert
			statement.executeUpdate();
			// To get the primary key (id) of the newly inserted record

		} catch (SQLException e) {

			System.out.println("Error Creating Book: " + e);
		}
		finally{
		// Close the connection to the database - Very important!!!

		try {
			connection.close();
			connection = null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
		}}
		// Return the bank object that was inserted with the id field set.

		return billtable;
	}

	public void PsetBooks(Bill bill, Integer num) {
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}

		System.out.println("number of books is " + num);
		tblBill billtable = new tblBill();
		tblBook booktable = new tblBook();
		// Query to insert a record to the bank table
		String query = "update pthimmojibook b set b.noOfBooks=? where b.bookId=? ;";
		// Use prepared statements to avoid SQL injection attacks
		try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			// Set the parameters to the query

			statement.setInt(1, num);

			statement.setInt(2, bill.getBookId());

			// Execute the insert
			statement.executeUpdate();
			// To get the primary key (id) of the newly inserted record

		} catch (SQLException e) {

			System.out.println("Error Creating Book: " + e);
		}
		// Close the connection to the database - Very important!!!

		try {
			connection.close();
			connection = null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
		}
		// Return the bank object that was inserted with the id field set.

	}

	public void setUserIssuedBooks(Bill bill) {
		Integer nobooks = 0;
		try {

			try {
				connection = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				System.out.println("Error creating connection to database: " + e);
				System.exit(-1);
			}
			PreparedStatement ps = null;

			// System.out.println("number of books is "+num);
			tblBill billtable = new tblBill();
			// BookTable booktable=new BookTable();
			// Query to insert a record to the bank table
			// String query = "select * from memberrecords b where
			// b.noOfBooksIssued=? ;";
			// Use prepared statements to avoid SQL injection attacks

			ps = connection.prepareStatement("select * from pthimmojimemberrecords b  where b.memberId=? ;");
			ps.setInt(1, bill.getMemberId());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				nobooks = Integer.parseInt(rs.getString("noOfBooksIssued"));

			}
			rs.close();

		} catch (SQLException e) {

			System.out.println("Error Creating Book: " + e);
		}
		// Close the connection to the database - Very important!!!
		Integer increased = nobooks + 1;
		System.out.println("Book to be increased" + increased);

		String query1 = "update pthimmojimemberRecords b set b.noOfBooksIssued=? where b.memberId=? ;";
		// Use prepared statements to avoid SQL injection attacks
		try (PreparedStatement statement = connection.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS)) {
			// Set the parameters to the query

			statement.setInt(1, increased);

			statement.setInt(2, bill.getMemberId());

			// Execute the insert
			statement.executeUpdate();
			// To get the primary key (id) of the newly inserted record

		} catch (SQLException e) {

			System.out.println("Error Creating Book: " + e);
		}
finally{
		try {
			connection.close();
			connection = null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
		}}
		// Return the bank object that was inserted with the id field set.

	}

	public void setUserReturnedBooks(Bill bill) {
		Integer nobooks = 0;
		try {

			try {
				connection = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				System.out.println("Error creating connection to database: " + e);
				System.exit(-1);
			}
			PreparedStatement ps = null;

			// System.out.println("number of books is "+num);
			tblBill billtable = new tblBill();
			// BookTable booktable=new BookTable();
			// Query to insert a record to the bank table
			// String query = "select * from memberrecords b where
			// b.noOfBooksIssued=? ;";
			// Use prepared statements to avoid SQL injection attacks

			ps = connection.prepareStatement("select * from pthimmojimemberrecords b  where b.memberId=? ;");
			ps.setInt(1, bill.getMemberId());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				nobooks = Integer.parseInt(rs.getString("noOfBooksIssued"));

			}
			rs.close();

		} catch (SQLException e) {

			System.out.println("Error Creating Book: " + e);
		}
		// Close the connection to the database - Very important!!!

		Integer decreased = nobooks - 1;
		System.out.println("Book to be decreased" + decreased);

		String query1 = "update pthimmojimemberrecords b set b.noOfBooksIssued=? where b.memberId=? ;";
		// Use prepared statements to avoid SQL injection attacks
		try (PreparedStatement statement = connection.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS)) {
			// Set the parameters to the query

			statement.setInt(1, decreased);

			statement.setInt(2, bill.getMemberId());

			// Execute the insert
			statement.executeUpdate();
			// To get the primary key (id) of the newly inserted record

		} catch (SQLException e) {

			System.out.println("Error Creating Book: " + e);
		}
finally{
		try {
			connection.close();
			connection = null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
		}}
		// Return the bank object that was inserted with the id field set.

	}

	public Integer getLibraryBooksBill(Bill bill) {
		// allBillList = FXCollections.observableArrayList();
		int number = 0;
		PreparedStatement ps = null;
		try {
			try {
				connection = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				System.out.println("Error creating connection to database: " + e);
				System.exit(-1);
			}

			ps = connection.prepareStatement("Select * from pthimmojibook where bookId=?;");
			ps.setInt(1, bill.getBookId());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				number = Integer.parseInt(rs.getString("noOfBooks"));

			}
			rs.close();

			/*
			 * table.setItems(data);
			 */ } catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		return number;
	}

	public ArrayList<Integer> getUser() {
		List list = null;

		ArrayList<Integer> members = null;
		try {
			try {

				members = new ArrayList<Integer>();
				connection = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				System.out.println("Error creating connection to database: " + e);
				System.exit(-1);
			}
			PreparedStatement ps = null;

			ps = connection.prepareStatement("select m.memberId from pthimmojimemberrecords m;");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Integer member = rs.getInt(1);
				members.add(member);

			}
			rs.close();

			/*
			 * table.setItems(data);
			 */ } catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		return members;
	}

	private ObservableList<tblMember> allcreList;

	public boolean getLoginCredentials(Integer userid, String Pass) {

		allcreList = FXCollections.observableArrayList();
		Integer daoID = null;
		String daoPass = null;
		try {
			PreparedStatement ps = null;

			try {
				connection = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				System.out.println("Error creating connection to database: " + e);
				System.exit(-1);
			}

			tblMember member = new tblMember();

			// Query to insert a record to the bank table
			// String query = "select m.memberId,m.memberPassword from
			// memberrecords where memberId=? ;";

			ps = connection.prepareStatement("select * from pthimmojimemberrecords where memberId=? ;");
			ps.setInt(1, userid);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				// member.memberId.set(rs.getString("memberId"));
				// member.memberPassword.set(rs.getString("memberPassword"));

				daoID = Integer.parseInt(rs.getString("memberId"));
				daoPass = rs.getString("memberPassword");
				// allcreList.add(member);

			}
			rs.close();
		} catch (SQLException e) {

			System.out.println("Error Creating credentials: " + e);
		}
		finally{
		// Close the connection to the database - Very important!!!

		try {
			connection.close();
			connection = null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
		}}
		// Return the bank object that was inserted with the id field set.

		System.out.println("Dao userID: " + daoID);	
		System.out.println("Entered user Id: " + userid);
		System.out.println("Dao password: " + daoPass);
		System.out.println("entereed pass: " + Pass);

		if (daoID == null || daoPass.equals("")) {
	return false;
	}
		else
		 if (daoID.equals(userid) && daoPass.equals(Pass)) {
			return true;
		} else {

			return false;
		}
	}

}