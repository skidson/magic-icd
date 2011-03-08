package ca.ubc.magic.icd.web.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ca.ubc.magic.icd.web.json.JsonItem;
import ca.ubc.magic.icd.web.model.Bit;
import ca.ubc.magic.icd.web.model.LinkManager;
import ca.ubc.magic.icd.web.model.User;
import ca.ubc.magic.icd.web.services.MagicService;
import ca.ubc.magic.icd.web.services.UserService;
 
@Controller
public class BitController {
	private static final int BITS_PER_PAGE = 20;
	
	@Autowired
	private MagicService magicService;
	
	@Autowired 
	private LinkManager linkManager;
	
    @RequestMapping("/basic/bits")
    public ModelAndView basicBits() {
    	Map<String, Object> model = UserService.initUserContext(linkManager);
    	return new ModelAndView("bits", model);
    }
    
    @RequestMapping("/magic/bits")
    public ModelAndView magicBits() {
    	Map<String, Object> model = UserService.initUserContext(linkManager);
    	
    	List<Bit> bitsList = new ArrayList<Bit>();
    	for(int i = 1; i < BITS_PER_PAGE; i++) {
    		try {
	    		JsonItem bitInfo = magicService.showBit(i);
	    		Bit bit = new Bit(bitInfo);
	    		bitsList.add(bit);
    		} catch (Exception e) {
    			continue;
    		}
    	}
    	
    	model.put("bitsList", bitsList);
    	return new ModelAndView("bits", model);
    }
    
    @RequestMapping("/magic/bit")
    public ModelAndView showBit(@RequestParam("id") int bitID) {
    	Map<String, Object> model = UserService.initUserContext(linkManager);
    	Bit bit = new Bit(magicService.showBit(bitID));
    	model.put("bit", bit);
    	
    	List<User> userLinks = magicService.showUserLinks(bitID);
    	List<User> bitLinks = magicService.showUserLinks(bitID);
    	
    	model.put("userLinks", userLinks);
    	model.put("bitLinks", bitLinks);
    	
    	return new ModelAndView("bit", model);
    }
    
    @RequestMapping("/magic/checkinBit")
    public ModelAndView checkIn(@RequestParam("id") int bitID){
    	Map<String, Object> model = UserService.initUserContext(linkManager);
    	
    	JsonItem checkinInfo = magicService.checkin(bitID);
    	System.out.println(checkinInfo.toString());
    	return new ModelAndView("home", model);
    }
    
    @RequestMapping(value = "/magic/createBit", method = RequestMethod.POST)
    public ModelAndView createBit(  @RequestParam("in_name") String name, 
    								@RequestParam("in_type") int type, 
    								@RequestParam("in_description") String description, 
    								@RequestParam("in_place") String place){
    	Map<String, Object> model = UserService.initUserContext(linkManager);
    	
    	JsonItem bitInfo = null;
    	if (place.equals("none"))
    		bitInfo = magicService.createBit(type, name, description);
    	else
    		bitInfo = magicService.createBit(type, name, description, place);
    	
    	Bit bit = new Bit(bitInfo);
    	model.put("bit", bit);
    	return new ModelAndView("bit", model);
    }
    
    @RequestMapping(value = "/magic/createBit", method = RequestMethod.GET)
    public ModelAndView createBit() {
    	Map<String, Object> model = UserService.initUserContext(linkManager);
    	return new ModelAndView("bit_create", model);
    }
}
