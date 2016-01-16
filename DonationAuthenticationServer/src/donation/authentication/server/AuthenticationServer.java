package donation.authentication.server;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;

import donation.server.remote.Authentication;
import donation.server.remote.IAuthentication;

public class AuthenticationServer {

	public static void main(String[] args) {
		if (args.length != 3) {
			System.exit(0);
		}

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}

		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

		try {
			ApplicationService appService = new ApplicationService();
			IAuthentication MainServer = new Authentication(appService);
			Naming.rebind(name, MainServer);
			System.out.println("- ServiceFacade '" + name + "' active and waiting...");
		} catch (Exception e) {
			System.err.println("$ ServiceFacade exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}