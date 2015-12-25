package es.deusto.ingenieria.sd.authentication.server;

import java.util.HashMap;

/**
 * 
 * @author Txuso
 *
 */
public class ApplicationService {
	
	/**
	 * This atribute stores all the different usernames and passwords
	 */
	private HashMap<String,String> UsernamePasswordHashMap = new HashMap<String,String>();
	
	/**
	 * Some data is introduced in order get access to the Donation Client
	 */
	public ApplicationService() {
		
		UsernamePasswordHashMap.put("josurubio@gmail.es", "123");
		UsernamePasswordHashMap.put("mario@gmail.es", "123");
	} 
	
	/**
	 * 
	 * @param mail The client's email
	 * @param password The client's password
	 * @return
	 */
	public synchronized boolean loginAuthentification(String mail, String password){
		return UsernamePasswordHashMap.containsKey(mail) && UsernamePasswordHashMap.get(mail).equals(password);
	}
	
}
