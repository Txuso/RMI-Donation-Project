package es.deusto.ingenieria.sd.authentication.server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAuthentication extends Remote {
	/**
	 * This is the interface with the methods that are going to be used in order to login
	 */
	public boolean loginAuthentication(String mail, String password)
			throws RemoteException;

}
