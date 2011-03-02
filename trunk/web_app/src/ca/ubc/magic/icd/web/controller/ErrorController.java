package ca.ubc.magic.icd.web.controller;

import org.springframework.security.oauth.consumer.OAuthConsumerContextFilter;
import org.springframework.security.oauth.consumer.OAuthRequestFailedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public class ErrorController {

	@RequestMapping("/oauth_error")
	public ModelAndView errorPage(@RequestParam(OAuthConsumerContextFilter.OAUTH_FAILURE_KEY) OAuthRequestFailedException error) {
        	String message = error.getMessage();
		return new ModelAndView("error", "error_message", message);
	}
	
}
