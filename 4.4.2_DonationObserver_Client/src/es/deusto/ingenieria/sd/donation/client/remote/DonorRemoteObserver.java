package es.deusto.ingenieria.sd.donation.client.remote;

import java.rmi.RemoteException;

import es.deusto.ingenieria.sd.donation.client.Donor;
import es.deusto.ingenieria.sd.donation.server.remote.ICollector;
import es.deusto.ingenieria.sd.util.observer.remote.RemoteObserver;

public class DonorRemoteObserver extends RemoteObserver {
	
	private static final long serialVersionUID = 1L;
	private ICollector collector;
	private Donor donor;

	public DonorRemoteObserver(ICollector collector, Donor donor) throws RemoteException {
		super();
		this.collector = collector;
		this.donor = donor;
		this.collector.addRemoteObserver(this);
	}

	public void end() throws RemoteException {
		this.collector.deleteRemoteObserver(this);
	}

	public void update(Object arg) {
		if (arg instanceof Integer) {
			this.donor.notifyTotalAmount((Integer) arg);
		}
	}
}