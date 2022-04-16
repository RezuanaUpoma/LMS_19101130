package model;

/**
 * Member Details Class
 * @author gururaj
 *
 */

public class memberRecords {
	
	private Integer memberId; //Primary column of the table
	private String memberName;
	private String memberAddress; 
	private Long memberPhone;
	private String typeOfMember;
	private Integer noOfBooksIssued;
	private String memberPassword;
	public final Integer getMemberId() {
		return memberId;
	}
	public final void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public final String getMemberName() {
		return memberName;
	}
	public final void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public final String getMemberAddress() {
		return memberAddress;
	}
	public final void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}
	public final Long getMemberPhone() {
		return memberPhone;
	}
	public final void setMemberPhone(Long memberPhone) {
		this.memberPhone = memberPhone;
	}
	public final String getTypeOfMember() {
		return typeOfMember;
	}
	public final void setTypeOfMember(String typeOfMember) {
		this.typeOfMember = typeOfMember;
	}
	public final Integer getNoOfBooksIssued() {
		return noOfBooksIssued;
	}
	public final void setNoOfBooksIssued(Integer noOfBooksIssued) {
		this.noOfBooksIssued = noOfBooksIssued;
	}
	public final String getMemberPassword() {
		return memberPassword;
	}
	public final void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}
	
	
	
	
	

}


