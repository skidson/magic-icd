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
	private static final int BITS_PER_PAGE = 15;
	
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
    	bitsList = magicService.showBitLinksOfUser();
    	
    	model.put("bitsList", bitsList);
    	return new ModelAndView("bits", model);
    }
    
    @RequestMapping("/magic/bit")
    public ModelAndView showBit(@RequestParam("bitID") int id) {
    	Map<String, Object> model = UserService.initUserContext(linkManager);
    	Bit bit = new Bit(magicService.showBit(id));
    	model.put("bit", bit);
    	
		JsonItem profile = magicService.showUser();
		User magicUser = new User(profile);
		magicUser.setBits(magicService.showBitLinksOfUser(magicUser.getId()));
		List<User> userLinks = magicService.showUserLinkedToBit(id);
    	List<Bit> bitTies = magicService.showTies(id);
    	System.out.println(bitTies.toString());
    	
    	model.put("magicUser", magicUser);
    	model.put("userLinks", userLinks);
    	model.put("bitLinks", bitTies);
    	
    	return new ModelAndView("bit", model);
    }
    
    @RequestMapping("/magic/checkinBit")
    public ModelAndView checkIn(@RequestParam("bitID") int id){
    	Map<String, Object> model = UserService.initUserContext(linkManager);
    	
    	JsonItem checkinInfo = magicService.checkin(id);
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
    
    @RequestMapping("magic/linkBit")
    public ModelAndView linkToBit(@RequestParam("bitID") int id){
    	Map<String, Object> model = UserService.initUserContext(linkManager);
    	
    	magicService.createLink(id);
    	return new ModelAndView("redirect:/magic/bits", model);
    }
    
    @RequestMapping("magic/destroyLink")
    public ModelAndView destoyLink(@RequestParam("bitID") int id){
    	Map<String, Object> model = UserService.initUserContext(linkManager);
    	
    	magicService.destroyLink(id);
    	return new ModelAndView("redirect:/magic/bits", model);
    }
    
    
    @RequestMapping("magic/updateBit")
    public ModelAndView updateBit(@RequestParam("bitID") int id){
    	Map<String, Object> model = UserService.initUserContext(linkManager);
    	
    	Bit bit = new Bit(magicService.showBit(id));
    	model.put("bit", bit);
    	return new ModelAndView("updateBit", model);
    }
    
    @RequestMapping("magic/updateBitDescription")
    public ModelAndView updateBitDescription(	@RequestParam("bitID") int id,
    											@RequestParam("in_description") String description){
    	Map<String, Object> model = UserService.initUserContext(linkManager);
    	
    	magicService.updateBitDescription(id, description);
    	return new ModelAndView("redirect:/magic/updateBit?bitID=" + id, model);
    }
    
    @RequestMapping("magic/updateBitType")
    public ModelAndView updateBitType(	@RequestParam("bitID") int id,
    									@RequestParam("in_type") int type){
    	Map<String, Object> model = UserService.initUserContext(linkManager);
    	
    	magicService.updateBitType(id, type);
    	return new ModelAndView("redirect:/magic/updateBit?bitID=" + id, model);
    }
    
    @RequestMapping("magic/updateBitName")
    public ModelAndView updateBitName(	@RequestParam("bitID") int id,
    									@RequestParam("in_name") String name){
    	Map<String, Object> model = UserService.initUserContext(linkManager);
    	
    	magicService.updateBitName(id, name);
    	return new ModelAndView("redirect:/magic/updateBit?bitID=" + id, model);
    }
    
    @RequestMapping("magic/connectBits")
    public ModelAndView getConnectPage(@RequestParam("bitID") int id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value="searchQuery", required = false) String query){
    	Map<String, Object> model = UserService.initUserContext(linkManager);
    	int index;
    	if(query == null) query = "";
    	List<Bit> search = magicService.searchBits(query);
    	List<Bit> toReturn = new ArrayList<Bit>();
    	Bit origBit = new Bit(magicService.showBit(id));
    	
    	if(page == null) {
    		page = 1;
    		index = BITS_PER_PAGE;
    	}
    	else index = (page-1)*BITS_PER_PAGE+BITS_PER_PAGE;
  
    	for (int i = (page-1) * BITS_PER_PAGE; i < search.size() && i < index; i++)
    		toReturn.add(search.get(i));
    	
    	
    	model.put("bits", toReturn);
    	model.put("page", page);
    	model.put("numPages", search.size()/BITS_PER_PAGE + 1);
    	model.put("origBit", origBit);
    	return new ModelAndView("connectBits", model);
    }
    
    @RequestMapping("magic/connectTo")
    public ModelAndView connectBits(@RequestParam("bitID") int id, @RequestParam("tieID") int tieID){
    	Map<String, Object> model = UserService.initUserContext(linkManager);
    	magicService.createTie(id, tieID);
    	return new ModelAndView("redirect:/magic/bit?bitID=" + id, model);
    }
    
    
}
