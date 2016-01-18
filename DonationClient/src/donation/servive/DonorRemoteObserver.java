package donation.servive;

import java.rmi.RemoteException;

import donation.observer.remote.RemoteObserver;
import donation.remote.ICollector;
import donation.service.Donor;


/**
 * 
 * This class adds observers (clients) who will be observing the observable object (server)
 * It inherits from the RemoteObserver in order to implement the update method 
 */
public class DonorRemoteObserver extends RemoteObserver {
	
	private static final long serialVersionUID = 1L;
	private ICollector collector;
	private Donor donor;
	
	/**
	 * 
	 * @param collector the observable object
	 * @param donor the client that will be added to the subscription list from the server in 
	 * order to be notified about the changes
	 */
	public DonorRemoteObserver(ICollector collector, Donor donor) throws RemoteException {
		super();
		this.collector = collector;
		this.donor = donor;
		this.collector.addRemoteObserver(this);
	}

	/**
	 * 
	 * If the client ends the process it will be unsubscripted from the list
	 * of observers to be notified 
	 */
	public void end() throws RemoteException {
		this.collector.deleteRemoteObserver(this);
	}
	
	/**
	 * We call the method that will update th total amount of donated money
	 */
	public void update(Object arg) {
		if (arg instanceof Integer) {
			this.donor.notifyTotalAmount((Integer) arg);
		}
	}
}