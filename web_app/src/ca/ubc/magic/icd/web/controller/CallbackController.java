package ca.ubc.magic.icd.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth.consumer.InMemoryProtectedResourceDetailsService;
import org.springframework.security.oauth.consumer.ProtectedResourceDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ca.ubc.magic.icd.web.controller.oauth.CallbackBuffer;
import ca.ubc.magic.icd.web.oauth.OAuth;
 
@Controller
public class CallbackController {
	@Autowired
	private InMemoryProtectedResourceDetailsService resourceDetails;
 
    @RequestMapping("/basic/callback")
    public ModelAndView callback(@RequestParam(OAuth.OAUTH_TOKEN) String token) {
    	System.out.println("Caught token: " + token);
    	Map<String, ? extends ProtectedResourceDetails> store = resourceDetails.getResourceDetailsStore();
    	return new ModelAndView("home");
    }
    
    @RequestMapping("/fetch_callback")
    public ModelAndView fetch() {
    	return new ModelAndView("fetch_callback", "callback", CallbackBuffer.getInstance().next());
    }
    
}
