package ca.ubc.magic.icd.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
 
    @RequestMapping("/bits")
    public ModelAndView showList() {
    	Map<String, Object> model = new HashMap<String, Object>();
		UserService.addUserContext(model);
    	
		// Dummy values until we can connect to broker
    	List<Bit> bitsList = new ArrayList<Bit>();
    	bitsList.add(new Bit("Coffee", "A delicious drink. Brewed from Columbian beans at a perfect temperature " +
    			" and topped off with a swirl of whipped cream. MMmmmmm.", CoffeeShopService.DRINK, 1, 1));
    	bitsList.add(new Bit("Ham & Cheese Panini", "A delicious meal", CoffeeShopService.FOOD, 1, 2));
    	
    	model.put("bitsList", bitsList);
    	return new ModelAndView("bits", model);
    }
    
    @RequestMapping("/bit")
    public ModelAndView showBit(@RequestParam("id") int bitID) {
    	Map<String, Object> model = new HashMap<String, Object>();
		UserService.addUserContext(model);
    	
    	JsonItem bitInfo = magicService.showBit(bitID); // TODO sanitize this input
    	Bit bit = new Bit(bitInfo.getAsString(MagicService.NAME),
    			bitInfo.getAsString(MagicService.DESCRIPTION),
    			bitInfo.getAsInteger(MagicService.BITS_TYPE_ID),
    			bitInfo.getAsInteger(MagicService.PLACES_ID),
    			bitID);
    	
    	model.put("bit", bit);
    	return new ModelAndView("bit", model);
    }
}
