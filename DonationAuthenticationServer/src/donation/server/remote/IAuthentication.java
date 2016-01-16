package donation.server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAuthentication extends Remote {

	public boolean loginAuthentication(String mail, String password)
			throws RemoteException;

}
