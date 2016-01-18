package donation.remote;

import java.rmi.RemoteException;

import donation.observer.remote.IRemoteObservable;

/**
 * 
 * The donation server that inherits from IRemoteObservable so that it can add and delete observers
 * It also implements the getDonation method explained in the CollectorWindow class
 *
 */
public interface ICollector extends IRemoteObservable {
	void getDonation(int donation) throws RemoteException;
}