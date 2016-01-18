package donation.server.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import donation.authentication.server.ApplicationService;

public class Authentication extends UnicastRemoteObject implements IAuthentication{
	
	private static final long serialVersionUID = 1L;
	private ApplicationService appService;
	/**
	 * 
	 * @param server the application service that will check the login
	 * 
	 */
	public Authentication(ApplicationService server) throws RemoteException {
		super();
		this.appService = server;
	} 
	
	/**
	 * The implementation of the interface that will call the application service
	 * in order to check the login
	 * @return true if the email and password are in the hasmap and false if not.
	 */
	@Override
	public boolean loginAuthentication(String mail, String password) {
		// TODO Auto-generated method stub
		return this.appService.loginAuthentification(mail, password);
	}

}
