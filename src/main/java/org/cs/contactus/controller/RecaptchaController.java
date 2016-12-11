package org.cs.contactus.controller;

import org.cs.contactus.domain.form.Contact;
import org.cs.contactus.exception.InvalidRequestException;
import org.cs.contactus.service.SendEmail;
import org.cs.contactus.validator.RecaptchaFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class RecaptchaController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(RecaptchaController.class);
	
	private final RecaptchaFormValidator recaptchaFormValidator;
	private final SendEmail sendEmail;
	
	@Autowired
	public RecaptchaController(final RecaptchaFormValidator recaptchaFormValidator,
			final SendEmail sendEmail) {
		this.recaptchaFormValidator = recaptchaFormValidator;
		this.sendEmail = sendEmail;
	}
	
	@InitBinder("contactUsForm")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(recaptchaFormValidator);
    }
		
	@CrossOrigin
	@RequestMapping(value = "/contact", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody ResponseEntity<Contact> greeting(@ModelAttribute("contactUsForm") @Valid Contact contactUsForm, BindingResult result) {

		if(result.hasErrors()){
			LOGGER.info(contactUsForm.toString());
			LOGGER.info("Validation error exist Not Sending email");
			throw new InvalidRequestException("Invalid Captcha", result);
		}else{
			LOGGER.info(contactUsForm.toString());
			LOGGER.info("Validation is done! Initiating send mail");
			sendEmail.sendEmail(contactUsForm);
			return ResponseEntity.ok(contactUsForm);
		}
		
	}
}
