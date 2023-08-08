//Server

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.*;

public class THServer {

    public THServer() {
        try {
            THInterface thS = new THImpl(); //Server's remote object
            Naming.rebind("//localhost/Theater", thS); //bind Server's remote object (thS) to the RMI registry - making it known
            System.out.println("PeerServer bound in registry");
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
    }


    public static void main(String args[]) {
        System.out.println("RMI server started");
        //object registry
        try { //special exception handler for registry creation
            LocateRegistry.createRegistry(1099); //create Registry
            System.out.println("java RMI registry created.");
        } catch (RemoteException e) {
            //do nothing, error means registry already exists
            System.out.println("java RMI registry already exists.");
        }
        //Instantiate RmiServer
        new THServer();
    }
}