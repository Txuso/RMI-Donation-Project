package donation.authentication.server;

import java.util.HashMap;

/**
 * 
 * This class checks if the login is correct or wrong
 *
 */
public class ApplicationService {
	
	/**
	 * This hasmap contains all the users within the system
	 */
	private HashMap<String, String> UsernamePasswordHashMap = new HashMap<String, String>();
	
	/**
	 * We put some data inside the list
	 */
	public ApplicationService() {
		
		UsernamePasswordHashMap.put("josurubio@gmail.es", "123");
		UsernamePasswordHashMap.put("mario@gmail.es", "123");
		UsernamePasswordHashMap.put("elisa@gmail.es", "123");

	} 
	
	/**
	 * 
	 * @param mail from the donor
	 * @param password from the donor
	 * @return true if the email and password are in the hasmap and false if not.
	 */
	public synchronized boolean loginAuthentification(String mail, String password) {
		return UsernamePasswordHashMap.containsKey(mail) && UsernamePasswordHashMap.get(mail).equals(password);
	}
	
}
