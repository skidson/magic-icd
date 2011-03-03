package ca.ubc.magic.icd.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ca.ubc.magic.icd.web.MagicService;
import ca.ubc.magic.icd.web.controller.oauth.CoffeeShopService;
import ca.ubc.magic.icd.web.json.JsonItem;
import ca.ubc.magic.icd.web.model.Bit;
 
@Controller
public class BitController {
	@Autowired
	private MagicService magicService;
 
    @RequestMapping("/bits")
    public ModelAndView showList() {
    	List<Bit> bitsList = new ArrayList<Bit>();
    	bitsList.add(new Bit("Coffee", "A delicious drink. Brewed from Columbian beans at a perfect temperature " +
    			"topped off with a swirl of whipped cream. MMmmmmm.", CoffeeShopService.DRINK, 1, 1337));
    	bitsList.add(new Bit("Ham & Cheese Panini", "A delicious meal", CoffeeShopService.FOOD, 1, 1338));
    	return new ModelAndView("bits", "bitsList", bitsList);
    }
    
    @RequestMapping("/bit")
    public ModelAndView homePage(@RequestParam("bit") int bitID) {
    	JsonItem bitInfo = magicService.showBit(bitID); // TODO sanitize this input
    	Bit bit = new Bit(bitInfo.getAsString("name"),
    			bitInfo.getAsString("description"),
    			bitInfo.getAsInteger("bit_types_id"),
    			bitInfo.getAsInteger("places_id"),
    			bitID);
    	return new ModelAndView("bit", "bit", bit);
    }
}