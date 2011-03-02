package ca.ubc.magic.icd.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth.consumer.OAuthConsumerContextFilter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public class ErrorController {

	@RequestMapping("/oauth_error")
	public ModelAndView errorPage(@RequestParam(OAuthConsumerContextFilter.OAUTH_FAILURE_KEY) HttpSession error) {
        	((AuthenticationException) error.getAttribute(OAuthConsumerContextFilter.OAUTH_FAILURE_KEY)).getMessage();
            ((AuthenticationException) error.getAttribute(OAuthConsumerContextFilter.OAUTH_FAILURE_KEY)).printStackTrace(System.out);
		return new ModelAndView("error", "error_message", error);
	}
	
}
