package org.cs.contactus.helper;

import org.springframework.util.StringUtils;

public class ContactUsHelper {
	
	public static boolean allMandatory(String ... inputs){
		for(String input: inputs){
			if(StringUtils.isEmpty(input)){
				return false;
			}
		}
		return true;
	}
}
