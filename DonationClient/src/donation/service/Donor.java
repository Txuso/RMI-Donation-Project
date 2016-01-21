package donation.service;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import donation.remote.ICollector;
import donation.servive.DonorRemoteObserver;

/**
 * 
 *The client's GUI where they will donate money
 *
 */
public class Donor implements ActionListener {
	private JFrame frame;
	private JButton buttonEnd;
	private JButton buttonDonate;
	private JTextField donation;
	private JTextField total;
	private JLabel message;
	/**
	 * the donation server reference
	 */
	private ICollector don_collector;
	/**
	 * The remote donor instance 
	 */
	private DonorRemoteObserver remoteDonor;

	public Donor() {				
		this.buttonDonate = new JButton("Donate");
		this.buttonDonate.addActionListener(this);
		this.buttonEnd = new JButton("End Process");
		this.buttonEnd.addActionListener(this);
		this.donation = new JTextField(10);
		this.message = new JLabel("Welcome to the RMI Donor Client");
		this.message.setOpaque(true);
		this.message.setForeground(Color.yellow);
		this.message.setBackground(Color.gray);

		JPanel panelDonativos = new JPanel();
		panelDonativos.add(new JLabel("Donation: "));
		panelDonativos.add(this.donation);
		this.total = new JTextField(10);
		total.setText("Total Amount\n");
		panelDonativos.add(total);
		this.total.setEnabled(false);
		JLabel label = new JLabel("Total Amount: ");
		panelDonativos.add(label);

		JPanel panelBotones = new JPanel();
		panelBotones.add(this.buttonDonate);
		panelBotones.add(this.buttonEnd);

		this.frame = new JFrame("Donor: RMI Client");
		this.frame.setSize(400, 125);
		this.frame.setResizable(false);		
		this.frame.getContentPane().add(panelDonativos, "North");
		this.frame.getContentPane().add(panelBotones);
		this.frame.getContentPane().add(this.message, "South");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setVisible(true);
		
		/**
		 * We create the needed parameters to start the client
		 */
		String[] Nextargs = new String[3];
		Nextargs [0] = "192.168.43.251"; 
		Nextargs [1] = 	"1099";
		Nextargs [2] = "Collector";
		this.start(Nextargs);
		
	}

	public void start(String[] args) {
		this.connect2Collector(args);
		
		try {
			this.remoteDonor = new DonorRemoteObserver(this.don_collector, this);
		} catch (RemoteException e) {
			System.err.println(" # Error creating Remote Donor: " + e.getMessage());
		}
	}

	private void connect2Collector(String[] args) {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}

		try {
			/**
			 * arg[0] it is the IP address of the server
			 * arg[1] it is the port of the server
			 * arg[2] it is the name of the server
			 * We connect the client to the server
			 */
			String URL = "//" + args[0] + ":" + args[1] + "/" + args[2];
			this.don_collector = (ICollector) Naming.lookup(URL);
		} catch (Exception e) {
			System.err.println(" # Error connecting to Donation Collector: " + e.getMessage());
		}
	}
	
	/**
	 * Buttons actions
	 */
	public void actionPerformed(ActionEvent e) {
		JButton target = (JButton) e.getSource();
		/**
		 * if we end the client process the end method will be called
		 */
		if (target == this.buttonEnd) {
			try {
				this.remoteDonor.end();
				System.exit(0);
			} catch (Exception exc) {
				System.err.println(" # Error ending Remote Donor: " + exc.getMessage());
				System.exit(-1);
			}
		}
		
		/**
		 * When the donate button is pressed the donation is done calling the method from the server
		 */
		if (target == this.buttonDonate) {
			try {
				
				int donation = Integer.parseInt(this.donation.getText());
				if (donation > 0){
					this.message.setText("Sending donation ...");
					this.don_collector.getDonation(donation);
					this.message.setText("Donation of " + donation + " already sent...");
				}
				else
					this.message.setText(" # You cannot steal the money.");

			} catch (Exception exc) {
				this.message.setText(" # Error sending donation.");
			}
		}
	}
	
	/**
	 * 
	 * @param don the amount of donated money
	 * this method is called from the DonorRemoteObserver when a donation is done
	 * It updates the values in the client
	 */
	public void notifyTotalAmount(Integer don) {
		this.message.setText("Receiving total donation amount...");
		this.total.setText(don.toString());
		this.message.setText("Total Amount received...");
	}
	
	/**
	 * 
	 * This method will start the Donor client
	 */
	public static void main(String[] args) {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}

		try {
			Donor donor = new Donor();
			donor.start(args);
		} catch (Exception e) {
			System.err.println(" # Error starting Donor: " + e.getMessage());
		}
	}
}