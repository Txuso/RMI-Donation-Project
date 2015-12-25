package es.deusto.ingenieria.sd.util.observer.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public abstract class RemoteObserver extends UnicastRemoteObject implements IRemoteObserver {
	private static final long serialVersionUID = 1L;

	public RemoteObserver() throws RemoteException {
		super();
	}
}
