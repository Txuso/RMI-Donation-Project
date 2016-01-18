package donation.observer.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 
 * This class will be used by the server so that it can add and delete observers (clients)
 *
 */
public interface IRemoteObservable extends Remote {
	public void addRemoteObserver(IRemoteObserver observer) throws RemoteException;
	public void deleteRemoteObserver(IRemoteObserver observer) throws RemoteException;
}