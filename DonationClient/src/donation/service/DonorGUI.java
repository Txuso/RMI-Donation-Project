package donation.service;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import donation.server.remote.IAuthentication;

/**
 * 
 * Donor Authentication GUI
 *
 */
public class DonorGUI extends JFrame{
	static DonorGUI donorG;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private static final long serialVersionUID = 1L;
	/**
	 * Authentication server instance
	 */
	private IAuthentication service;
	
	/**
	 * We create the connection to the server that the donor will be using
	 */
	public DonorGUI() {
		 try {
			service = (IAuthentication) java.rmi.Naming.lookup("//192.168.43.251:1099/authenticationServer");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ai = new Authentication();
		
		System.out.println("GUI: Login");
		this.setResizable(false);
		this.setName("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 298, 177);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBackground(Color.BLACK);
		contentPane.setToolTipText("Donation Client");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblName = new JLabel("Email");
		lblName.setForeground(Color.WHITE);
		lblName.setBounds(28, 36, 188, 14);
		contentPane.add(lblName);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setBounds(28, 61, 188, 14);
		contentPane.add(lblPassword);

		textField = new JTextField();
		textField.setBounds(99, 33, 132, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(99, 58, 132, 20);
		contentPane.add(passwordField);
		this.setTitle("Donation Client");

		JButton btnNewButton = new JButton("Login");

		btnNewButton.setBounds(99, 87, 132, 23);
		contentPane.add(btnNewButton);
		/**
		 * We check if the user exits using the method from the authentication server
		 */
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean userID;
				try {
					userID = service.loginAuthentication(textField.getText(), passwordField.getText());
					if(userID){
						Donor d = new Donor();
						donorG.setVisible(false);
					}
					else
		                  JOptionPane.showMessageDialog(new Frame(), "Incorrect User");
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
						
			}
		});

		
		
	}
	
	/**
	 * 
	 * We run the login client
	 */
	public static void main(String[] args) {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}

		try {
			donorG = new DonorGUI();
			donorG.setVisible(true);
			
			
		} catch (Exception e) {
			System.err.println(" # Error starting Donor: " + e.getMessage());
		}
	}
	

}
