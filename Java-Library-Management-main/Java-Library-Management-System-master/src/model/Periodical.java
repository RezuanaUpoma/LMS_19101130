package model;
/**
 * Periodical Books class
 * @author gururaj
 *
 */


public class Periodical extends Book{
	
	private Integer bookId;

	private String format; // format of the book
	private String schedule; //  written
	private Integer edition; // 
	 // Number of Pages
	public final Integer getBookId() {
		return bookId;
	}
	public final void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public final String getFormat() {
		return format;
	}
	public final void setFormat(String format) {
		this.format = format;
	}
	public final String getSchedule() {
		return schedule;
	}
	public final void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	public final Integer getEdition() {
		return edition;
	}
	public final void setEdition(Integer edition) {
		this.edition = edition;
	}

}
