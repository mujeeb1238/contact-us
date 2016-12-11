package org.cs.contactus.domain.form;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class Contact extends RecaptchaForm{
	
	@NotEmpty(message = "Business Name is mandatory")
    @Size(max = 64, message = "Business Name size must be between 0 and 64")
	public String fullName;
	
	@NotEmpty(message = "Mobile number is mandatory")
    @Size(min = 10, max = 12, message = "Invalid Mobile number")
	public String contactNumber;
	
	
    @Size(max = 250)
	public String business;
	
	public Contact() {}
	
	public Contact(String fullName, String contactNumber, String business) {
		this.fullName = fullName;
		this.contactNumber = contactNumber;
		this.business = business;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	@Override
	public String toString() {
		return "Contact [fullName=" + fullName + ", contactNumber=" + contactNumber + ", business=" + business + "]";
	}	
	
}
