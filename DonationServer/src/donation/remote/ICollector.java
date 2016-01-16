package donation.remote;

import java.rmi.RemoteException;

import donation.observer.remote.IRemoteObservable;

public interface ICollector extends IRemoteObservable {
	void getDonation(int donation) throws RemoteException;
}