package es.deusto.ingenieria.sd.donation.server.remote;

import java.rmi.RemoteException;

import es.deusto.ingenieria.sd.util.observer.remote.IRemoteObservable;

public interface ICollector extends IRemoteObservable {
	void getDonation(int donation) throws RemoteException;
}