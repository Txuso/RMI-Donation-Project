package es.deusto.ingenieria.sd.authentication.server.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import es.deusto.ingenieria.sd.authentication.server.ApplicationService;

public class Authentication extends UnicastRemoteObject implements IAuthentication{
	
	private static final long serialVersionUID = 1L;
	private ApplicationService appService;

	public Authentication(ApplicationService server) throws RemoteException {
		super();
		this.appService = server;
	} 
	
	@Override
	public boolean loginAuthentication(String mail, String password) {
		// TODO Auto-generated method stub
		return this.appService.loginAuthentification(mail, password);
	}

}
