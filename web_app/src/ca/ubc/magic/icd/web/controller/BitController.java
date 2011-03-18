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
 

/**
 * The Spring Controller that intercepts all the URL patterns related to Bits.
 * Each function passes the specified jsp page name through the View Resolver to render it for the user
 * @author Jeffrey Payan
 * @author Stephen Kidson
 */
@Controller
public class BitController {
	private static final int BITS_PER_PAGE = 15;
	
	@Autowired
	private MagicService magicService;
	
	@Autowired 
	private LinkManager linkManager;
	
	/**
	 * 
	 * 
	 */
    @RequestMapping("/basic/bits")
    public ModelAndView basicBits() {
    	Map<String, Object> model = UserService.initUserContext(linkManager);
    	return new ModelAndView("bits", model);
    }
    
    /**
     * 
     *
     */
    @RequestMapping("/magic/bits")
    public ModelAndView magicBits() {
    	Map<String, Object> model = UserService.initUserContext(linkManager);
    	
    	List<Bit> bitsList = new ArrayList<Bit>();
    	bitsList = magicService.showBitLinksOfUser();
    	
    	model.put("bitsList", bitsList);
    	return new ModelAndView("bits", model);
    }
    
    /**
     * 
     * @param id - Request Parameter : bitID
     *
     */
    @RequestMapping("/magic/bit")
    public ModelAndView showBit(@RequestParam("bitID") int id) {
    	Map<String, Object> model = UserService.initUserContext(linkManager);
    	Bit bit = new Bit(magicService.showBit(id));
    	
		User magicUser = new User(magicService.showUser());
		magicUser.setBits(magicService.showBitLinksOfUser(magicUser.getId()));
		List<User> userLinks = magicService.showUserLinkedToBit(id);
    	List<Bit> bitTies = magicService.showTies(id);
    	
    	model.put("bit", bit);
    	model.put("magicUser", magicUser);
    	model.put("userLinks", userLinks);
    	model.put("bitLinks", bitTies);
    	
    	return new ModelAndView("bit", model);
    }
    
    /**
     * 
     * @param id - Request Parameter : bitID
     * 
     */
    @RequestMapping("/magic/checkinBit")
    public ModelAndView checkIn(@RequestParam("bitID") int id){
    	Map<String, Object> model = UserService.initUserContext(linkManager);
    	
    	JsonItem checkinInfo = magicService.checkin(id);
    	System.out.println(checkinInfo.toString());
    	return new ModelAndView("home", model);
    }
    
    /**
     * 
     * @param name - Request Parameter : in_name
     * @param type - Request Parameter : in_type
     * @param description - Request Parameter : in_description
     * @param place - Request Parameter : in_place
     * 
     */
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
    
    /**
     * 
     *
     */
    @RequestMapping(value = "/magic/createBit", method = RequestMethod.GET)
    public ModelAndView createBit() {
    	Map<String, Object> model = UserService.initUserContext(linkManager);
    	return new ModelAndView("bit_create", model);
    }
    
    /**
     * 
     * @param id - Request Parameter : bitID
     *
     */
    @RequestMapping("magic/linkBit")
    public ModelAndView linkToBit(@RequestParam("bitID") int id){
    	Map<String, Object> model = UserService.initUserContext(linkManager);
    	
    	magicService.createLink(id);
    	return new ModelAndView("redirect:/magic/bits", model);
    }
    
    /**
     * 
     * @param id - Request Parameter : bitID
     * 
     */
    @RequestMapping("magic/destroyLink")
    public ModelAndView destoyLink(@RequestParam("bitID") int id){
    	Map<String, Object> model = UserService.initUserContext(linkManager);
    	
    	magicService.destroyLink(id);
    	return new ModelAndView("redirect:/magic/bits", model);
    }
    
    /**
     * 
     * @param id - Request Parameter : bitID
     *
     */
    @RequestMapping("magic/updateBit")
    public ModelAndView updateBit(@RequestParam("bitID") int id){
    	Map<String, Object> model = UserService.initUserContext(linkManager);
    	
    	Bit bit = new Bit(magicService.showBit(id));
    	model.put("bit", bit);
    	return new ModelAndView("updateBit", model);
    }
    
    /**
     * 
     * @param id - Request Parameter : bitID
     * @param description - Request Parameter :  in_description
     * 
     */
    @RequestMapping("magic/updateBitDescription")
    public ModelAndView updateBitDescription(	@RequestParam("bitID") int id,
    											@RequestParam("in_description") String description){
    	Map<String, Object> model = UserService.initUserContext(linkManager);
    	
    	magicService.updateBitDescription(id, description);
    	return new ModelAndView("redirect:/magic/updateBit?bitID=" + id, model);
    }
    
    /**
     * 
     * @param id - Request Parameter : bitID
     * @param type - Request Parameter : in_type
     * @return
     */
    @RequestMapping("magic/updateBitType")
    public ModelAndView updateBitType(	@RequestParam("bitID") int id,
    									@RequestParam("in_type") int type){
    	Map<String, Object> model = UserService.initUserContext(linkManager);
    	
    	magicService.updateBitType(id, type);
    	return new ModelAndView("redirect:/magic/updateBit?bitID=" + id, model);
    }
    
    /**
     * 
     * @param id - Request Param : bitID
     * @param name - Request Param : in_name
     * 
     */
    @RequestMapping("magic/updateBitName")
    public ModelAndView updateBitName(	@RequestParam("bitID") int id,
    									@RequestParam("in_name") String name){
    	Map<String, Object> model = UserService.initUserContext(linkManager);
    	
    	magicService.updateBitName(id, name);
    	return new ModelAndView("redirect:/magic/updateBit?bitID=" + id, model);
    }
    
    /**
     * 
     * @param id - Request Parameter : bitID
     * @param page - Request Parameter : page
     * @param query - Request Parameter : searchQuery
     * 
     */
    @RequestMapping("magic/connectBits")
    public ModelAndView getConnectPage(	@RequestParam("bitID") int id, 
    									@RequestParam(value = "page", required = false) Integer page, 
    									@RequestParam(value="searchQuery", required = false) String query){
    	
    	Map<String, Object> model = UserService.initUserContext(linkManager);
    	List<Bit> search = magicService.searchBits(query);
    	List<Bit> toReturn = new ArrayList<Bit>();
    	Bit origBit = new Bit(magicService.showBit(id));
    	
    	if(query == null) query = "";
    	if(page == null) page = 1;
  
    	for (int i = (page-1) * BITS_PER_PAGE; i < search.size() && i < (page-1)*BITS_PER_PAGE+BITS_PER_PAGE; i++)
    		toReturn.add(search.get(i));
    	
    	
    	model.put("bits", toReturn);
    	model.put("page", page);
    	model.put("numPages", search.size()/BITS_PER_PAGE + 1);
    	model.put("origBit", origBit);
    	return new ModelAndView("connectBits", model);
    }
    /**
     * 
     * @param id - Request Parameter : bitID
     * @param tieID - Request Parameter : tieID
     * 
     */
    @RequestMapping("magic/connectTo")
    public ModelAndView connectBits(@RequestParam("bitID") int id, @RequestParam("tieID") int tieID){
    	Map<String, Object> model = UserService.initUserContext(linkManager);
    	magicService.createTie(id, tieID);
    	return new ModelAndView("redirect:/magic/bit?bitID=" + id, model);
    }
    
    
}
