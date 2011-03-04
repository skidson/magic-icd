package ca.ubc.magic.icd.web.controller;

import org.springframework.security.oauth.consumer.OAuthConsumerContextFilter;
import org.springframework.security.oauth.consumer.OAuthRequestFailedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {

	@RequestMapping("/oauth_error")
	public ModelAndView oauthError(@RequestParam(OAuthConsumerContextFilter.OAUTH_FAILURE_KEY) OAuthRequestFailedException error) {
        	String message = "OAuth Error!\n" + error.getMessage();
		return new ModelAndView("error", "error", message);
	}
	
	@RequestMapping("/basic/error")
	public ModelAndView generalError() {
        	String message = "404 Page Not Found\n";
		return new ModelAndView("error", "error", message);
	}
	
}
