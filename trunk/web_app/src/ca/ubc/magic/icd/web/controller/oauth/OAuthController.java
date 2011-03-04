package ca.ubc.magic.icd.web.controller.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ca.ubc.magic.icd.web.services.CoffeeShopService;
import ca.ubc.magic.icd.web.services.MagicService;

@Controller
public class OAuthController {
	@Autowired
	private MagicService magicService;
	
	/*@RequestMapping("/basic/callback")
	public ModelAndView recieveCallback(@RequestParam("oauth_verifier") String verifier,
			@RequestParam("oauth_token") String token) {
		
	}*/
	
}
