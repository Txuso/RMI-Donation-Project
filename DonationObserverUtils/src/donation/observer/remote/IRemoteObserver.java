package donation.observer.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 
 * This class will be used by the observers (clients) so that they can update the total amount
 * of donated money 
 *
 */
public interface IRemoteObserver extends Remote {
	public void update(Object arg) throws RemoteException;
}