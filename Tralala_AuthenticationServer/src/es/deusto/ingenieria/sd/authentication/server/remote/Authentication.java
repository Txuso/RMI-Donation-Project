package es.deusto.ingenieria.sd.authentication.server.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import es.deusto.ingenieria.sd.authentication.server.ApplicationService;

public class Authentication extends UnicastRemoteObject implements IAuthentication{
	
	private static final long serialVersionUID = 1L;
	/**
	 * The application service that gives access to the stored users
	 */
	private ApplicationService appService;
	
	public Authentication(ApplicationService server) throws RemoteException {
		super();
		this.appService = server;
	} 
	
	/**
	 * The login method that will be called within the Application Service so as to login users
	 */
	@Override
	public boolean loginAuthentication(String mail, String password) {
		// TODO Auto-generated method stub
		return this.appService.loginAuthentification(mail, password);
	}

}
