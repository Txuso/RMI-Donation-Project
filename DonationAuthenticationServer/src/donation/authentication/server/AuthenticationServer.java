package donation.authentication.server;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import donation.server.remote.Authentication;
import donation.server.remote.IAuthentication;

/**
 * 
 * The Authentication Server will provide the login service
 *
 */
public class AuthenticationServer {
	
	public static void main(String[] args) {
		/**
		 * We check the number of arguments given when the server is run
		 */
		if (args.length != 3) {
			System.exit(0);
		}
		
		/**
		 * We create the security manager if it is null
		 */
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
		
		/**
		 * Hostname is set so that the client and server can work in different machines
		 */
		System.setProperty("java.rmi.server.hostname","192.168.43.251");
		
		/**
		 * arg[0] it is the IP address of the server
		 * arg[1] it is the port of the server
		 * arg[2] it is the name of the server
		 * This data is obtained when the server is run. Look at the build.xml
		 */
		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

		try {
			/**
			 * We create the application service where the login will be checked
			 */
			ApplicationService appService = new ApplicationService();
			/**
			 * We create the Main Authentication server and we give the application service as a parameter
			 */
			IAuthentication MainServer = new Authentication(appService);
			/**
			 * We run the server giving the args data and the Authentication reference
			 */
			Naming.rebind(name, MainServer);
			System.out.println("- ServiceFacade '" + name + "' active and waiting...");
		} catch (Exception e) {
			System.err.println("$ ServiceFacade exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}