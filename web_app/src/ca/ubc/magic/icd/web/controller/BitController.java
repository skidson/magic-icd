package ca.ubc.magic.icd.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
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
import ca.ubc.magic.icd.web.services.CoffeeShopService;
import ca.ubc.magic.icd.web.services.MagicService;
import ca.ubc.magic.icd.web.services.UserService;
 
@Controller
public class BitController {
	@Autowired
	private MagicService magicService;
	
    @RequestMapping("/basic/bits")
    public ModelAndView basicBits() {
    	Map<String, Object> model = new HashMap<String, Object>();
		UserService.addUserContext(model);
    	
		// Dummy values until we can connect to broker
    	List<Bit> bitsList = new ArrayList<Bit>();
    	bitsList.add(new Bit("Coffee", "A delicious drink. Brewed from Columbian beans at a perfect temperature " +
    			" and topped off with a swirl of whipped cream. MMmmmmm.", "", CoffeeShopService.DRINK, 1, 1));
    	bitsList.add(new Bit("Ham & Cheese Panini", "A delicious meal", "", CoffeeShopService.FOOD, 1, 2));
    	
    	model.put("bitsList", bitsList);
    	return new ModelAndView("bits", model);
    }
    
    @RequestMapping("/magic/bits")
    public ModelAndView magicBits() {
    	Map<String, Object> model = new HashMap<String, Object>();
		UserService.addUserContext(model);
		
    	
		// Dummy values until we can connect to broker
    	List<Bit> bitsList = new ArrayList<Bit>();
    	
    	model.put("bitsList", bitsList);
    	return new ModelAndView("bits", model);
    }
    
    @RequestMapping("/magic/bit")
    public ModelAndView showBit(@RequestParam("id") int bitID) {
    	Map<String, Object> model = new HashMap<String, Object>();
		UserService.addUserContext(model);
    	
    	JsonItem bitInfo = magicService.showBit(bitID); // TODO sanitize this input
    	System.out.println(bitInfo.toString());
    	Bit bit = new Bit(bitInfo.getAsJsonItem("bit").getAsString(MagicService.NAME),
    			bitInfo.getAsJsonItem("bit").getAsString(MagicService.DESCRIPTION),
    			bitInfo.getAsJsonItem("bit").getAsString(MagicService.QR_IMAGE_URL),
    			bitInfo.getAsJsonItem("bit").getAsInteger(MagicService.BITS_TYPE_ID),
    			bitInfo.getAsJsonItem("bit").getAsInteger(MagicService.PLACES_ID),
    			bitID);
    	
    	model.put("bit", bit);
    	return new ModelAndView("bit", model);
    }
    
    @RequestMapping("/magic/checkinBit")
    public ModelAndView checkIn(@RequestParam("id") int bitID){
    	Map<String, Object>	 model = new HashMap<String, Object>();
    	UserService.addUserContext(model);
    	
    	JsonItem checkinInfo = magicService.checkin(bitID);
    	System.out.println(checkinInfo.toString());
    	return new ModelAndView("home", model);
    }
    
    @RequestMapping(value = "/magic/createBit", method = RequestMethod.POST)
    public ModelAndView createBit(  @RequestParam("in_name") String name, 
    								@RequestParam("in_type") int type, 
    								@RequestParam("in_description") String description, 
    								@RequestParam("in_place") String place){
    	Map<String, Object>	 model = new HashMap<String, Object>();
    	UserService.addUserContext(model);
    	
    	JsonItem bitInfo = null;
    	if (place.equals("none"))
    		bitInfo = magicService.createBit(type, name, description);
    	else
    		bitInfo = magicService.createBit(type, name, description);
    	Bit bit = new Bit(bitInfo.getAsString(MagicService.NAME),
    			bitInfo.getAsJsonItem("bit").getAsString(MagicService.DESCRIPTION),
    			bitInfo.getAsJsonItem("bit").getAsString(MagicService.QR_IMAGE_URL),
    			bitInfo.getAsJsonItem("bit").getAsInteger(MagicService.BITS_TYPE_ID),
    			bitInfo.getAsJsonItem("bit").getAsInteger(MagicService.PLACES_ID),
    			bitInfo.getAsJsonItem("bit").getAsInteger(MagicService.ID));
    	model.put("bit", bit);
    	return new ModelAndView("bit", model);
    }
    
    @RequestMapping(value = "/magic/createBit", method = RequestMethod.GET)
    public ModelAndView createBit() {
    	Map<String, Object>	 model = new HashMap<String, Object>();
    	UserService.addUserContext(model);
    	
    	return new ModelAndView("bit_create", model);
    }
}
