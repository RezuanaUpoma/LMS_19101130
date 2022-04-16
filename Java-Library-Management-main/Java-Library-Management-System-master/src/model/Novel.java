package model;
/**
 * Novel Books class
 */

import java.util.Date;

public class Novel extends Book {

	// TODO Auto-generated constructor stub

	private Integer bookId;

	private String genre; // genre of the book
	private String language; // language written
	private Date published; // Date published
	int pages; // Number of Pages

	public final Integer getBookId() {
		return bookId;
	}

	public final void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public final String getGenre() {
		return genre;
	}

	public final void setGenre(String genre) {
		this.genre = genre;
	}

	public final String getLanguage() {
		return language;
	}

	public final void setLanguage(String language) {
		this.language = language;
	}

	public final Date getPublished() {
		return published;
	}

	public final void setPublished(Date published) {
		this.published = published;
	}

	public final int getPages() {
		return pages;
	}

	public final void setPages(int pages) {
		this.pages = pages;
	}

}
