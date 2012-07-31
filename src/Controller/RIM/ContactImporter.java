package Controller.RIM;
/*******************************************************************************************
 * Project: ReUnite
 *
 * Class: ContactImporter
 *
 * Author: Adeel M. Ateeque (112013Z)
 * 
 * Lecturer: Ms Lim Ai Hua
 * 
 * Purpose:  A controller class to import user contacts from various online services 
 * such as Hotmail, Google and Facebook.
 * 
 * Honor Code: I pledge that this program represents my own program code. 
 * I received help from no one in designing, coding and debugging my program.
 *******************************************************************************************/
import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.FacebookApi;
import org.scribe.builder.api.GoogleApi;
import org.scribe.builder.api.LiveApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import Controller.RIM.Utils.ValidationHelper;
import Model.Membership.Guest;
import View.RIM.ServiceLoginWindow;

public class ContactImporter
{
	private static String SERVICE_NAME = null;
	private static String PROTECTED_RESOURCE_URL = null;
	private static Token REQUEST_TOKEN = null;
	private static String API_KEY = null;
	private static String API_SECRET = null;
	public static final String CALL_BACK_URL = "http://www.saharpharma.com/";
	private static Token ACCESS_TOKEN = null;
	private static String SCOPE = null;

	private static OAuthService service;

	public ContactImporter(String serviceName)
	{
		SERVICE_NAME = serviceName;

		if (serviceName.equals("Hotmail"))
		{
			PROTECTED_RESOURCE_URL = "https://apis.live.net/v5.0/";
			API_KEY = "00000000440CAA45";
			API_SECRET = "5Lw8GFNWSy4CZba-SrWZIk4TPjXEDYiO";
			SCOPE = "wl.basic wl.emails wl.birthday wl.postal_addresses wl.phone_numbers";

			service = new ServiceBuilder().provider(LiveApi.class).apiKey(API_KEY).apiSecret(API_SECRET).scope(SCOPE).callback(CALL_BACK_URL).build();
		}
		else if (serviceName.equals("Google"))
		{
			PROTECTED_RESOURCE_URL = "https://docs.google.com/feeds/default/private/full/";
			API_KEY = "anonymous";
			API_SECRET = "anonymous";
			SCOPE = "https://docs.google.com/feeds/";

			service = new ServiceBuilder().provider(GoogleApi.class).apiKey(API_KEY).apiSecret(API_SECRET).scope(SCOPE).callback(CALL_BACK_URL)
					.build();
		}
		else if (serviceName.equals("Facebook"))
		{
			PROTECTED_RESOURCE_URL = "https://graph.facebook.com/me";
			API_KEY = "335189139899151";
			API_SECRET = "5b84db1c6e20ac51f24c9932eb02f1fc";

			service = new ServiceBuilder().provider(FacebookApi.class).apiKey(API_KEY).apiSecret(API_SECRET).callback(CALL_BACK_URL).build();
		}

		ServiceLoginWindow loginWindow = new ServiceLoginWindow(getAuthorizationURL(), CALL_BACK_URL, SERVICE_NAME);

		getAccessToken(loginWindow.getAccessCode());

		System.out.println("Access code is: " + loginWindow.getAccessCode());
	}

	private static String getAuthorizationURL()
	{
		String authorizationUrl = null;

		System.out.println("Fetching the Authorization URL...");

		if (SERVICE_NAME.equals("Google"))
		{
			REQUEST_TOKEN = service.getRequestToken();
			authorizationUrl = "https://www.google.com/accounts/OAuthAuthorizeToken?oauth_token=" + REQUEST_TOKEN.getToken();
		}
		else
		{
			authorizationUrl = service.getAuthorizationUrl(REQUEST_TOKEN);
		}

		System.out.println("Got the Authorization URL!");
		System.out.println(authorizationUrl);

		return authorizationUrl;
	}

	private static void getAccessToken(String authorizationCode)
	{
		if(authorizationCode!=null && !authorizationCode.equals("")){
			
			Verifier verifier = new Verifier(authorizationCode);
	
			if (verifier != null)
			{
				// Trade the Request Token and Verifier for the Access Token
				System.out.println("Trading the Request Token and Verifier for an Access Token...");
				Token accessToken = service.getAccessToken(REQUEST_TOKEN, verifier);
				if (accessToken != null)
				{
					System.out.println("Got the Access Token!");
					System.out.println("(Access Token: " + accessToken + " )");
	
					ACCESS_TOKEN = accessToken;
				}
			}
		}
	}

	private static String getResource(String query)
	{
		// Ask for the protected resource
		System.out.println("Accessing protected resource...");
		OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL + query);
		service.signRequest(ACCESS_TOKEN, request);

		// add header if service is google
		if (SERVICE_NAME.equals("Google"))
		{
			request.addHeader("GData-Version", "3.0");
		}

		Response response = request.send();
		
		

		return response.getBody();
	}

	public ArrayList<Guest> getContacts()
	{
		ArrayList<Guest> response = new ArrayList<Guest>();

		if (SERVICE_NAME.equals("Hotmail"))
		{
			String data = getResource("me/contacts");
			
			System.out.println(data);
			
			JSONObject result = (JSONObject) JSONSerializer.toJSON(data);

             try 
             {
                 JSONArray contacts = result.optJSONArray("data");
                 for (int i = 0; i < contacts.size(); i++) {
                    JSONObject contact = contacts.getJSONObject(i);
                    
                    if (ValidationHelper.validateEmail(contact.getString("name"))||
                    		((!contact.getString("first_name").equals("null") && 
                    				(!contact.getString("last_name").equals("null")))))
    				{
                    	Guest guest = new Guest();
                    	
                    	if(!contact.getString("first_name").equals("null"))
                    	{
                    		guest.setFirstName(contact.getString("first_name"));
    					}
                    	
    					if(!contact.getString("last_name").equals(contact.getString("first_name")))
    					{
    						if(!contact.getString("first_name").equals("null"))
                        	{
    							guest.setLastName(contact.getString("last_name"));
                        	}
    					}
    					if(ValidationHelper.validateEmail(contact.getString("name")))
    					{
    						guest.setEmail(contact.getString("name"));
    					}
    					
    					guest.setProfilePicture("hotmailIcon.png");
                   
    					response.add(guest);
    				}	
                }
             }
             catch(JSONException ex) 
             {
                System.out.println("Error getting contacts " + ex.getMessage()+ ex.getLocalizedMessage());
             }
		}
//		else if (SERVICE_NAME.equals("Google"))
//		{
//			System.out.println(parseXMLResposne(getResource("")));
//		}
//		else if (SERVICE_NAME.equals("Facebook"))
//		{
//			System.out.println(getResource(""));
//		}

		return response;
	}

//	private String parseXMLResposne(String resource)
//	{
//		return resource;
//	}
//
	public static void main(String args[])
	{
		ContactImporter importer = new ContactImporter("Hotmail");
		for(Guest guest:importer.getContacts())
		{
			System.out.println(guest.getFirstName() + " " + guest.getLastName() + " " + guest.getEmail());
		}
	}
	
}