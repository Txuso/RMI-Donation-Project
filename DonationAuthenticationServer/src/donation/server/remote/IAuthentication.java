package donation.server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 
 * The authentication class with the methods that we client will use
 *
 */
public interface IAuthentication extends Remote {

	public boolean loginAuthentication(String mail, String password)
			throws RemoteException;

}
