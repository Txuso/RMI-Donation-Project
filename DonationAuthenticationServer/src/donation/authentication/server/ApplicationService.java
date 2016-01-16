package donation.authentication.server;

import java.util.HashMap;


public class ApplicationService {
	
	private HashMap<String, String> UsernamePasswordHashMap = new HashMap<String, String>();
	
	public ApplicationService() {
		
		UsernamePasswordHashMap.put("josurubio@gmail.es", "123");
		UsernamePasswordHashMap.put("mario@gmail.es", "123");

	} 
	

	public synchronized boolean loginAuthentification(String mail, String password){

		return UsernamePasswordHashMap.containsKey(mail) && UsernamePasswordHashMap.get(mail).equals(password);
	}
	
}
