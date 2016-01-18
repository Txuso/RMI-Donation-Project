package donation.observer.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public abstract class RemoteObserver extends UnicastRemoteObject implements IRemoteObserver {
	private static final long serialVersionUID = 1L;

	
	/**
	 * Clients can inherit from this class and they simply need to implement update();
	 * 
	 */
	public RemoteObserver() throws RemoteException {
		super();
	}
}
