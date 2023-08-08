//Client

import java.io.Console;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;

public class THClient {

    public static void main(String[] args) {
        try {
            //args[0]= function, args[1]= hostname, args[2]= type, args[3]= number, args[4]= name
            if (args.length == 2) { //cases with 2 arguments: list, guests
                String host = args[1];
                THInterface thC = (THInterface) Naming.lookup("//" + host + "/Theater");
                //list
                if (args[0].equals("list")) //if: java TΗClient list <hostname>
                    System.out.println(thC.listAvailableSeats());
                //guests
                if (args[0].equals("guests")) //if: java TΗClient guests <hostname>
                    System.out.println(thC.getGuests());
            } else if (args.length == 5) { //cases with 5 arguments: book, cancel
                Console cnsl = System.console();
                String host = args[1];
                THInterface thC = (THInterface) Naming.lookup("//" + host + "/Theater");
                //book
                if (args[0].equals("book")) { //if: java TΗClient book <hostname> <type> <number> <name>
                    String res = thC.bookSeats(args[2], Integer.parseInt(args[3]), args[4], false); //flag = false since it's the first time calling the method and no booking problems are found
                    //args are type String, so args[3] has to be converted to integer
                    //in case there are not as many seats available as requested, client is informed and asked if he'd like to continue
                    if (res.charAt(0) != 'B' && res.charAt(0) != '-') { //string starts with 'B' when the booking was successful
                        int availableSeats = 0;
                        for (int i = 0; i <= res.length(); i++) //getting the available seats (number from the string)
                            availableSeats = Integer.parseInt(res);
                        System.out.println(availableSeats + " available seats only found. Would you like to proceed? Y/N");
                        String answer = cnsl.readLine();
                        if (answer.charAt(0) == 'Y' || answer.charAt(0) == 'y')
                            System.out.println(thC.bookSeats(args[2], Integer.parseInt(args[3]), args[4], true)); //flag = true since it's not the first time calling the method (booking problems are found & resolved)
                    } else if (res.charAt(0) == '-' && res.charAt(1) == '1') //code -1 for failed booking
                        System.out.println("BOOKING FAILED: Not enough available seats to book!");
                    else if (res.charAt(0) == 'B') //successful booking for all requested seats
                        System.out.println(res); //printing result from bookSeats()
                } //cancel
                else if (args[0].equals("cancel")) //if: java TΗClient cancel <hostname> <type> <number> <name>
                    System.out.println(thC.cancelSeats(args[2], Integer.parseInt(args[3]), args[4]));

            } else { //if: java TΗClient (without enough arguments)
                //printing valid arguments used
                System.out.println("USAGE:");
                System.out.println("java THClient list <hostname>");
                System.out.println("java THClient book <hostname> <type> <number> <name>");
                System.out.println("java THClient guests <hostname>");
                System.out.println("java THClient cancel <hostname> <type> <number> <name>");

            }

        } catch (MalformedURLException murle) {
            System.out.println();
            System.out.println("MalformedURLException");
            System.out.println(murle);
        } catch (RemoteException re) {
            System.out.println();
            System.out.println("RemoteException");
            System.out.println(re);
        } catch (NotBoundException nbe) {
            System.out.println();
            System.out.println("NotBoundException");
            System.out.println(nbe);
        } catch (java.lang.ArithmeticException ae) {
            System.out.println();
            System.out.println("java.lang.ArithmeticException");
            System.out.println(ae);
        }
    }
}