package org.cs.contactus.domain.form;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public abstract class RecaptchaForm {

    @NotEmpty(message = "Invalid Captcha!")
    @Size(min = 0, max = 2000, message = "Invalid Captcha!")
    private String recaptchaResponse;

    public void setRecaptchaResponse(String response) {
        this.recaptchaResponse = response;
    }

    public String getRecaptchaResponse() {
        return recaptchaResponse;
    }

    @Override
    public String toString() {
        return "RecaptchaForm{" +
                "recaptchaResponse='" + recaptchaResponse + '\'' +
                '}';
    }
}

