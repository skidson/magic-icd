package ca.ubc.magic.icd.web.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.security.oauth.consumer.OAuthRestTemplate;
import org.springframework.security.oauth.consumer.ProtectedResourceDetails;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ca.ubc.magic.icd.web.json.JsonItem;
import ca.ubc.magic.icd.web.json.JsonParser;

public class CoffeeShopService implements MagicService {
	private String magicURLPattern;
	private String magicQRCodeURLPattern;
	private OAuthRestTemplate magicRestTemplate;

	private OAuthRestTemplate sparklrRestTemplate;
	private String sparklrPhotoListURL;
	private String sparklrPhotoURLPattern;

	@Override
	public JsonItem showBit(int id) {
		String request = "bit/show?id=" + id;
		return (new JsonParser(compileInputStream(request))).parse().get(0);
	}

	@Override
	public JsonItem createBit(int type, String name, String description) {
		String request = "create?bits_types=" + type + "&name="
			+ name + "&description=" + description;
		return (new JsonParser(compileInputStream(request))).parse().get(0);
	}
	
	@Override
	public JsonItem createBit(int type, String name, String description, int place) {
		String request = "bit/create?bits_types=" + type + "&name="
			+ name + "&description=" + description + "&places_id=" + place;
		return (new JsonParser(compileInputStream(request))).parse().get(0);
	}

	@Override
	public JsonItem updateBitName(int id, String name) {
		String request = "bit/update?id=" + id + "&name=" + name;
		return (new JsonParser(compileInputStream(request))).parse().get(0);
	}

	@Override
	public JsonItem updateBitDescription(int id, String description) {
		String request = "bit/update?id=" + id + "&desription=" + description;
		return (new JsonParser(compileInputStream(request))).parse().get(0);
	}

	@Override
	public JsonItem updateBitType(int id, int type) {
		String request = "bit/update?id=" + id + "&bits_types_id=" + type;
		return (new JsonParser(compileInputStream(request))).parse().get(0);
	}

	@Override
	public JsonItem updateBitPlace(int id, int place) {
		String request = "bit/update?id=" + id + "&places_id=" + place;
		return (new JsonParser(compileInputStream(request))).parse().get(0);
	}
	
	public JsonItem checkin(int id) {
		String request = "chekin/bit?id=" + id;
		return (new JsonParser(compileInputStream(request))).parse().get(0);
	}
	
	@Override
	public JsonItem createFriend(int id) {
		String request = "friend/create?id=" + id;
		return (new JsonParser(compileInputStream(request))).parse().get(0);
	}

	@Override
	public JsonItem destroyFriend(int id) {
		String request = "friend/destroy?id=" + id;
		return (new JsonParser(compileInputStream(request))).parse().get(0);
	}
	
	public List<JsonItem> showFriends() {
		String request = "friends/show";
		return (new JsonParser(compileInputStream(request))).parse();
	}
	
	public List<JsonItem> showFriends(int id) {
		String request = "friends/show?id=" + id;
		return (new JsonParser(compileInputStream(request))).parse();
	}
	
	public JsonItem showUser() {
		String request = "user/show";
		return (new JsonParser(compileInputStream(request))).parse().get(0);
	}
	
	public JsonItem showUser(int id) {
		String request = "user/show?id=" + id;
		return (new JsonParser(compileInputStream(request))).parse().get(0);
	}
	
	public JsonItem searchUser(String query) {
		String request = "friends/search?q=" + query;
		return (new JsonParser(compileInputStream(request))).parse().get(0);
	}

	public void setMagicRestTemplate(OAuthRestTemplate magicRestTemplate) {
		this.magicRestTemplate = magicRestTemplate;
	}

	public OAuthRestTemplate getMagicRestTemplate() {
		return magicRestTemplate;
	}

	public void setMagicQRCodeURLPattern(String magicQRCodeURLPattern) {
		this.magicQRCodeURLPattern = magicQRCodeURLPattern;
	}

	public String getMagicQRCodeURLPattern() {
		return magicQRCodeURLPattern;
	}
	
	public String getMagicURLPattern() {
		return magicURLPattern;
	}

	public void setMagicURLPattern(String magicURLPattern) {
		this.magicURLPattern = magicURLPattern;
	}
	
	private InputStream compileInputStream(String request) {
//		getMagicRestTemplate().getResource().getAdditionalParameters().put("oauth_callback", "http://localhost:8010/web_app/basic/callback?oauth_verifier=lol");
		return new ByteArrayInputStream(getMagicRestTemplate()
				.getForObject(URI.create(getMagicURLPattern() + request), byte[].class));
	}
	
	public List<String> getSparklrPhotoIds() {
	    try {
	      InputStream photosXML = new ByteArrayInputStream(getSparklrRestTemplate().getForObject(URI.create(getSparklrPhotoListURL()), byte[].class));

	      final List<String> photoIds = new ArrayList<String>();
	      SAXParserFactory parserFactory = SAXParserFactory.newInstance();
	      parserFactory.setValidating(false);
	      parserFactory.setXIncludeAware(false);
	      parserFactory.setNamespaceAware(false);
	      SAXParser parser = parserFactory.newSAXParser();
	      parser.parse(photosXML, new DefaultHandler() {
	        @Override
	        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
	          if ("photo".equals(qName)) {
	            photoIds.add(attributes.getValue("id"));
	          }
	        }
	      });
	      return photoIds;
	    } catch (IOException e) {
	        throw new IllegalStateException(e);
	    } catch (SAXException e) {
	      throw new IllegalStateException(e);
	    } catch (ParserConfigurationException e) {
	      throw new IllegalStateException(e);
	    }
	  }
	
	public void setSparklrRestTemplate(OAuthRestTemplate sparklrRestTemplate) {
		this.sparklrRestTemplate = sparklrRestTemplate;
	}

	public OAuthRestTemplate getSparklrRestTemplate() {
		return sparklrRestTemplate;
	}
	
	public String getSparklrPhotoListURL() {
		return sparklrPhotoListURL;
	}

	public String getSparklrPhotoURLPattern() {
		return sparklrPhotoURLPattern;
	}

	public void setSparklrPhotoURLPattern(String sparklrPhotoURLPattern) {
		this.sparklrPhotoURLPattern = sparklrPhotoURLPattern;
	}
	
	public void setSparklrPhotoListURL(String sparklrPhotoListURL) {
		this.sparklrPhotoListURL = sparklrPhotoListURL;
	}
	
	public InputStream loadSparklrPhoto(String id) {
	    return new ByteArrayInputStream(getSparklrRestTemplate().getForObject(URI.create(String.format(getSparklrPhotoURLPattern(), id)), byte[].class));
	}

}
