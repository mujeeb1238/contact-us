package org.cs.contactus.service;

import org.cs.contactus.domain.form.Contact;

public interface SendEmail {

	void sendEmail(Contact contactInfo);

}
