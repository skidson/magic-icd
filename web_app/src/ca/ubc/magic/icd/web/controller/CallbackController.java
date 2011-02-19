package ca.ubc.magic.icd.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ca.ubc.magic.icd.web.controller.oauth.CallbackBuffer;
import ca.ubc.magic.icd.web.controller.oauth.CallbackItem;
import ca.ubc.magic.icd.web.oauth.OAuth;
 
@Controller
public class CallbackController {
 
    @RequestMapping("/callback")
    public ModelAndView callback(@RequestParam(OAuth.OAUTH_TOKEN) String token, 
    		@RequestParam(OAuth.OAUTH_TOKEN_SECRET) String tokenSecret, 
    		@RequestParam(OAuth.OAUTH_CALLBACK_CONFIRMED) String callbackConfirmed) {
    	CallbackBuffer.getInstance().add(new CallbackItem(token, tokenSecret, callbackConfirmed));
    	return new ModelAndView("home");
    }
    
    @RequestMapping("/fetch_callback")
    public ModelAndView fetch() {
    	return new ModelAndView("fetch_callback", "callback", CallbackBuffer.getInstance().next());
    }
    
}
