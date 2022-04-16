package model;
/**
 * 
 * @author gururaj
 *Book Class
 */

public class Book {
	
	private Integer bookId; //Primary column of the table
	private String bookName; 
	private String bookAuthor; 
	private Integer noOfBooks; 
	private String typeOfBooks; 
	private String bookStatus; 

	private Integer bookLimitDays;

	public Book() {
		
		// TODO Auto-generated constructor stub
	}

	

	public  Integer getBookId() {
		return bookId;
	}

	public  void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public  String getBookName() {
		return bookName;
	}

	public  void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public  String getBookAuthor() {
		return bookAuthor;
	}

	public  void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public  Integer getNoOfBooks() {
		return noOfBooks;
	}

	public  void setNoOfBooks(Integer noOfBooks) {
		this.noOfBooks = noOfBooks;
	}

	public  String getTypeOfBooks() {
		return typeOfBooks;
	}

	public  void setTypeOfBooks(String typeOfBooks) {
		this.typeOfBooks = typeOfBooks;
	}

	public  String getBookStatus() {
		return bookStatus;
	}

	public  void setBookStatus(String bookStatus) {
		this.bookStatus = bookStatus;
	}

	public Book(Integer bookId, String bookName, String bookAuthor, Integer noOfBooks, String typeOfBooks,
			String bookStatus, Integer bookLimitDays) {
		
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.noOfBooks = noOfBooks;
		this.typeOfBooks = typeOfBooks;
		this.bookStatus = bookStatus;
		this.bookLimitDays = bookLimitDays;
	}

	public  Integer getBookLimitDays() {
		return bookLimitDays;
	}

	public  void setBookLimitDays(Integer bookLimitDays) {
		this.bookLimitDays = bookLimitDays;
	}


	
}
