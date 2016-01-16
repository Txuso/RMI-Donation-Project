package donation.server;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import donation.observer.remote.IRemoteObserver;
import donation.observer.remote.RemoteObservable;
import donation.remote.ICollector;


public class CollectorWindow extends UnicastRemoteObject implements ICollector, ActionListener {

	private JFrame frame;
	private static final long serialVersionUID = 1L;
	private RemoteObservable remoteObservable;
	private int donations = 0;
	private JButton buttonEnd;
	private JTextField donation;
	private JTextField total;
	private JLabel message;
	private JLabel lblTotal;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}

		System.setProperty("java.rmi.server.hostname","192.168.43.30");
		final String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
		System.out.println(" * Server name: " + name);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ICollector doncollector = new CollectorWindow();
					Naming.rebind(name, doncollector);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws RemoteException 
	 */
	public CollectorWindow() throws RemoteException {
		initialize();
		this.remoteObservable = new RemoteObservable();
		
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.buttonEnd = new JButton("End Donation Process");
		this.buttonEnd.addActionListener(this);
		this.donation = new JTextField(10);
		this.donation.setText("0");
		this.donation.setEnabled(false);
		this.message = new JLabel("RMI Donation Collector Server running...");
		this.message.setOpaque(true);
		this.message.setForeground(Color.yellow);
		this.message.setBackground(Color.gray);

		JPanel panelSuperior = new JPanel();
		panelSuperior.add(new JLabel("Last Donation: "));
		panelSuperior.add(this.donation);
		
		lblTotal = new JLabel("Total: ");
		panelSuperior.add(lblTotal);
		this.total = new JTextField(10);
		this.total.setText("0");
		this.total.setBackground(Color.WHITE);
		this.total.setEnabled(false);
		panelSuperior.add(this.total);
		panelSuperior.add(new JLabel("Total Donation: "));

		JPanel panelBoton = new JPanel();
		panelBoton.add(this.buttonEnd);

		this.frame = new JFrame("Donation Collector: RMI Server");
		this.frame.setSize(475, 140);
		this.frame.getContentPane().add(panelSuperior, "North");
		this.frame.getContentPane().add(panelBoton);
		this.frame.getContentPane().add(this.message, "South");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setVisible(true);
	}

	public void addRemoteObserver(IRemoteObserver observer) {
		this.remoteObservable.addRemoteObserver(observer);
		
		try {
			observer.update(new Integer(this.donations));
		} catch (RemoteException e) {
			System.err.println(" # Error subscribing to remote server: " + e.getMessage());
		}
	}

	public void deleteRemoteObserver(IRemoteObserver observer) {
		this.remoteObservable.deleteRemoteObserver(observer);
	}

	public synchronized void getDonation(int donation) throws RemoteException {
		this.donations += donation;
		this.donation.setText(Integer.toString(donation));
		this.total.setText(Integer.toString(this.donations));
		this.notifyTotal(this.donations);
	}

	private void notifyTotal(int total) {
		this.remoteObservable.notifyRemoteObservers(new Integer(total));
	}

	public void actionPerformed(ActionEvent e) {
		JButton target = (JButton)e.getSource();
		if (target == this.buttonEnd) {
			System.exit(0);
		}
	}
	
}
