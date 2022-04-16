package model;
/**
 * Bill Class
 */

import java.util.Date;

public class Bill {
	private Integer billNo; 
	private Float billAmount;
	private Integer bookId;
	private Integer memberId;
	private Integer libId;
	private Date dateIssued; 
	private Date dateReturned; 
	private Date billDate; 
	private String billStatus;
	public final String getBillStatus() {
		return billStatus;
	}
	public final void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}
	public final Integer getBillNo() {
		return billNo;
	}
	public final void setBillNo(Integer billNo) {
		this.billNo = billNo;
	}
	public final Float getBillAmount() {
		return billAmount;
	}
	public final void setBillAmount(Float billAmount) {
		this.billAmount = billAmount;
	}
	public final Integer getBookId() {
		return bookId;
	}
	public final void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public final Integer getMemberId() {
		return memberId;
	}
	public final void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public final Integer getLibId() {
		return libId;
	}
	public final void setLibId(Integer libId) {
		this.libId = libId;
	}
	public final Date getDateIssued() {
		return dateIssued;
	}
	public final void setDateIssued(Date dateIssued) {
		this.dateIssued = dateIssued;
	}
	public final Date getDateReturned() {
		return dateReturned;
	}
	public final void setDateReturned(Date dateReturned) {
		this.dateReturned = dateReturned;
	}
	public final Date getBillDate() {
		return billDate;
	}
	public final void setBillDate(Date billDate) {
		this.billDate = billDate;
	}


	

}
