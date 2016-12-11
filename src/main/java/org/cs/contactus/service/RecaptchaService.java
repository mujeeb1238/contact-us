package org.cs.contactus.service;

public interface RecaptchaService {

	boolean isResponseValid(String remoteIp, String response);

}
