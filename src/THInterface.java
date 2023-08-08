//Interface with methods used for the Server - Client communication

public interface THInterface extends java.rmi.Remote {
    String listAvailableSeats() throws java.rmi.RemoteException;

    String bookSeats(String type, int number, String name, boolean flag) throws java.rmi.RemoteException;

    String getGuests() throws java.rmi.RemoteException;

    String cancelSeats(String type, int number, String name) throws java.rmi.RemoteException;

}