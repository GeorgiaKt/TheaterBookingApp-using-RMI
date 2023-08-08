//Implementation of Interface's methods

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.HashMap;


public class THImpl extends java.rmi.server.UnicastRemoteObject implements THInterface {
    //declaring arrays for each type of seat, length of each array states the number of seats for this type
    //when a seat/element of the array has value "", means that it is not booked
    //when a seat/element of the array has any other value, means that it is booked by the value of the seat/element of the array, which is the name of the one that booked it

    public String zoneA[] = new String[100];    // zone A - 45€
    public String zoneB[] = new String[200];    // zone B - 35€
    public String zoneC[] = new String[400];    // zone C - 25€
    public String center[] = new String[225];    // Center - 30€
    public String sides[] = new String[75];     // Sides - 20€


    public THImpl() throws RemoteException { //constructor
        super();
        //initializing the arrays with empty strings
        Arrays.fill(zoneA, "");
        Arrays.fill(zoneB, "");
        Arrays.fill(zoneC, "");
        Arrays.fill(center, "");
        Arrays.fill(sides, "");
    }

    @Override
    public String listAvailableSeats() throws RemoteException {
        String availableSeatsRes;
        int availableSeatsA, availableSeatsB, availableSeatsC, availableSeatsCenter, availableSeatsSides; //counters for each zone
        availableSeatsA = availableSeatsB = availableSeatsC = availableSeatsCenter = availableSeatsSides = 0;

        //calculating available seats for each zone
        for (int i = 0; i < zoneA.length; i++)
            if (zoneA[i].equals(""))
                availableSeatsA++;
        for (int i = 0; i < zoneB.length; i++)
            if (zoneB[i].equals(""))
                availableSeatsB++;
        for (int i = 0; i < zoneC.length; i++)
            if (zoneC[i].equals(""))
                availableSeatsC++;
        for (int i = 0; i < center.length; i++)
            if (center[i].equals(""))
                availableSeatsCenter++;
        for (int i = 0; i < sides.length; i++)
            if (sides[i].equals(""))
                availableSeatsSides++;

        availableSeatsRes = "AVAILABLE SEATS:\n" +
                availableSeatsA + " seats ZoneA (code A) - Price: 45 euro\n" +
                availableSeatsB + " seats ZoneB (code B) - Price: 35 euro\n" +
                availableSeatsC + " seats ZoneC (code C) - Price: 25 euro\n" +
                availableSeatsCenter + " seats Center (code CE) - Price: 30 euro\n" +
                availableSeatsSides + " seats Sides (code S) - Price: 20 euro\n";

        return availableSeatsRes;
    }

    @Override
    public String bookSeats(String type, int number, String name, boolean flag) throws RemoteException {
        int cost = 0;
        int bookedSeats;
        int tmp = number;

        if (number == 0)
            return "BOOKING FAILED due to " + number + " seats requested";

        //Zone A
        if (type.equals("A")) {
            //calculating the available seats
            for (int i = 0; i < zoneA.length; i++)
                if (zoneA[i].equals("") && tmp > 0)
                    tmp--;

            //tmp == 0 when all seats requested are available
            bookedSeats = number - tmp; //seats that can be booked
            if (bookedSeats < number && bookedSeats != 0) { //if less than the seats requested are available
                if (!flag) //when bookSeats() is called the first time and less available seats were found (than requested), returns the number of available seats in order to inform the client and ask him if he'd like to proceed to booking
                    return bookedSeats + ""; //available seats
                //when bookSeats() is called the second time, proceeds to booking the available seats
                //booking, calculating the total cost & the amount of available seats booked - since client has agreed to proceed
                tmp = number;
                for (int i = 0; i < zoneA.length; i++)
                    if (zoneA[i].equals("") && tmp > 0) {
                        zoneA[i] = name;
                        cost = cost + 45;
                        tmp--;
                    }

                bookedSeats = number - tmp; //in case someone else books some of the seats available, calculate again the seats that are booked
                return "Booked " + bookedSeats + " seats in ZoneA (code A), Cost: " + cost;
            } else if (bookedSeats == number) { //if all the seats requested are available
                //booking, calculating the total cost & the amount of available seats booked
                tmp = number;
                for (int i = 0; i < zoneA.length; i++)
                    if (zoneA[i].equals("") && tmp > 0) {
                        zoneA[i] = name;
                        cost = cost + 45;
                        tmp--;
                    }

                bookedSeats = number - tmp;
                return "Booked " + bookedSeats + " seats in ZoneA (code A), Cost: " + cost;
            } else //if no seats are available
                return "-1"; //-1 for not enough available seats to book

        } //Zone B
        else if (type.equals("B")) {
            //calculating the available seats
            for (int i = 0; i < zoneB.length; i++)
                if (zoneB[i].equals("") && tmp > 0)
                    tmp--;

            //tmp == 0 when all seats requested are available
            bookedSeats = number - tmp;
            if (bookedSeats < number && bookedSeats != 0) { //if less than the seats requested are available
                if (!flag) //when bookSeats() is called the first time and less available seats were found (than requested), returns the number of available seats in order to inform the client and ask him if he'd like to proceed to booking
                    return bookedSeats + ""; //available seats
                //when bookSeats() is called the second time, proceeds to booking the available seats
                //booking, calculating the total cost & the amount of available seats booked - since client has agreed to proceed
                tmp = number;
                for (int i = 0; i < zoneB.length; i++)
                    if (zoneB[i].equals("") && tmp > 0) {
                        zoneB[i] = name;
                        cost = cost + 35;
                        tmp--;
                    }

                bookedSeats = number - tmp; //in case someone else books some of the seats available, calculate again the seats that are booked
                return "Booked " + bookedSeats + " seats in ZoneB (code B), Cost: " + cost;
            } else if (bookedSeats == number) { //if all the seats requested are available
                //booking, calculating the total cost & the amount of available seats booked
                tmp = number;
                for (int i = 0; i < zoneB.length; i++)
                    if (zoneB[i].equals("") && tmp > 0) {
                        zoneB[i] = name;
                        cost = cost + 35;
                        tmp--;
                    }

                bookedSeats = number - tmp;
                return "Booked " + bookedSeats + " seats in ZoneB (code B), Cost: " + cost;
            } else //if no seats are available
                return "-1"; //-1 for not enough available seats to book

        } //Zone C
        else if (type.equals("C")) {
            //calculating the available seats
            for (int i = 0; i < zoneC.length; i++)
                if (zoneC[i].equals("") && tmp > 0)
                    tmp--;

            //tmp == 0 when all seats requested are available
            bookedSeats = number - tmp;
            if (bookedSeats < number && bookedSeats != 0) { //if less than the seats requested are available
                if (!flag) //when bookSeats() is called the first time and less available seats were found (than requested), returns the number of available seats in order to inform the client and ask him if he'd like to proceed to booking
                    return bookedSeats + ""; //available seats
                //when bookSeats() is called the second time, proceeds to booking the available seats
                //booking, calculating the total cost & the amount of available seats booked
                tmp = number;
                for (int i = 0; i < zoneC.length; i++)
                    if (zoneC[i].equals("") && tmp > 0) {
                        zoneC[i] = name;
                        cost = cost + 25;
                        tmp--;
                    }

                bookedSeats = number - tmp; //in case someone else books some of the seats available, calculate again the seats that are booked
                return "Booked " + bookedSeats + " seats in ZoneC (code C), Cost: " + cost;
            } else if (bookedSeats == number) { //if all the seats requested are available
                //booking, calculating the total cost & the amount of available seats booked
                tmp = number;
                for (int i = 0; i < zoneC.length; i++)
                    if (zoneC[i].equals("") && tmp > 0) {
                        zoneC[i] = name;
                        cost = cost + 25;
                        tmp--;
                    }

                bookedSeats = number - tmp;
                return "Booked " + bookedSeats + " seats in ZoneC (code C), Cost: " + cost;
            } else //if no seats are available
                return "-1"; //-1 for not enough available seats to book

        } //Center
        else if (type.equals("CE")) {
            //calculating the available seats
            for (int i = 0; i < center.length; i++)
                if (center[i].equals("") && tmp > 0)
                    tmp--;

            //tmp == 0 when all seats requested are available
            bookedSeats = number - tmp;
            if (bookedSeats < number && bookedSeats != 0) { //if less than the seats requested are available
                if (!flag) //when bookSeats() is called the first time and less available seats were found (than requested), returns the number of available seats in order to inform the client and ask him if he'd like to proceed to booking
                    return bookedSeats + ""; //available seats
                //when bookSeats() is called the second time, proceeds to booking the available seats
                //booking, calculating the total cost & the amount of available seats booked
                tmp = number;
                for (int i = 0; i < center.length; i++)
                    if (center[i].equals("") && tmp > 0) {
                        center[i] = name;
                        cost = cost + 30;
                        tmp--;
                    }

                bookedSeats = number - tmp; //in case someone else books some of the seats available, calculate again the seats that are booked
                return "Booked " + bookedSeats + " seats in Center (code CE), Cost: " + cost;
            } else if (bookedSeats == number) { //if all the seats requested are available
                //booking, calculating the total cost & the amount of available seats booked
                tmp = number;
                for (int i = 0; i < center.length; i++)
                    if (center[i].equals("") && tmp > 0) {
                        center[i] = name;
                        cost = cost + 30;
                        tmp--;
                    }

                bookedSeats = number - tmp;
                return "Booked " + bookedSeats + " seats in Center (code CE), Cost: " + cost;
            } else //if no seats are available
                return "-1"; //-1 for not enough available seats to book

        } //Sides
        else if (type.equals("S")) {
            //calculating the available seats
            for (int i = 0; i < sides.length; i++)
                if (sides[i].equals("") && tmp > 0)
                    tmp--;

            //tmp == 0 when all seats requested are available
            bookedSeats = number - tmp;
            if (bookedSeats < number && bookedSeats != 0) { //if less than the seats requested are available
                if (!flag) //when bookSeats() is called the first time and less available seats were found (than requested), returns the number of available seats in order to inform the client and ask him if he'd like to proceed to booking
                    return bookedSeats + ""; //available seats
                //when bookSeats() is called the second time, proceeds to booking the available seats
                //booking, calculating the total cost & the amount of available seats booked
                tmp = number;
                for (int i = 0; i < sides.length; i++)
                    if (sides[i].equals("") && tmp > 0) {
                        sides[i] = name;
                        cost = cost + 20;
                        tmp--;
                    }

                bookedSeats = number - tmp; //in case someone else books some of the seats available, calculate again the seats that are booked
                return "Booked " + bookedSeats + " seats in Sides (code S), Cost: " + cost;
            } else if (bookedSeats == number) { //if all the seats requested are available
                //booking, calculating the total cost & the amount of available seats booked
                tmp = number;
                for (int i = 0; i < sides.length; i++)
                    if (sides[i].equals("") && tmp > 0) {
                        sides[i] = name;
                        cost = cost + 20;
                        tmp--;
                    }

                bookedSeats = number - tmp;
                return "Booked " + bookedSeats + " seats in Sides (code S), Cost: " + cost;
            } else //if no seats are available
                return "-1"; //-1 for not enough available seats to book
        } else
            return "BOOKED FAILED due to wrong type of seats given. Must be one of the types: A, B, C, CE, S";
    }

    @Override
    public String getGuests() throws RemoteException {

        String guests = "GUESTS LIST:\n";

        //creating a hashmap for each zone and keeping number of seats booked for each name
        //Zone A
        HashMap<String, Integer> guestsMapA = new HashMap<>(); //declaring hashmap where the names of the guests and their number of seats booked are going to be stored
        for (String value : zoneA) { //for every value in zoneA array
            if (!value.equals("")) { //if value has a name
                if (guestsMapA.containsKey(value)) { //if the value (name) already exists in the hashmap
                    //increases the number of seats booked by 1 (+1)
                    int seats = guestsMapA.get(value); //gets the seats this name already has booked
                    guestsMapA.put(value, seats + 1); //increase seats
                } else //if the value (name) does not exist in the hashmap
                    guestsMapA.put(value, 1); //add name and set booked seats to 1
            }
        }
        for (String value : guestsMapA.keySet())
            if (!value.equals("")) { //if value has a name
                int totalSeats = guestsMapA.get(value); //gets the seats that this name already has
                guests = guests + "Zone A, Name: " + value + ", Seats: " + totalSeats + ", Cost: " + totalSeats * 45 + "\n"; //concatenating the results in a string
            }

        //Zone B
        HashMap<String, Integer> guestsMapB = new HashMap<>(); //declaring hashmap where the names of the guests and their number of seats booked are going to be stored
        for (String value : zoneB) { //for every value in zoneB array
            if (!value.equals("")) { //if value has a name
                if (guestsMapB.containsKey(value)) { //if the value (name) already exists in the hashmap
                    //increases the number of seats booked by 1 (+1)
                    int seats = guestsMapB.get(value); //gets the seats this name already has booked
                    guestsMapB.put(value, seats + 1); //increase seats
                } else //if the value (name) does not exist in the hashmap
                    guestsMapB.put(value, 1); //add name and set booked seats to 1
            }
        }
        for (String value : guestsMapB.keySet())
            if (!value.equals("")) { //if value has a name
                int totalSeats = guestsMapB.get(value); //gets the seats that this name already has
                guests = guests + "Zone B, Name: " + value + ", Seats: " + totalSeats + ", Cost: " + totalSeats * 35 + "\n"; //concatenating the results in a string
            }

        //Zone C
        HashMap<String, Integer> guestsMapC = new HashMap<>(); //declaring hashmap where the names of the guests and their number of seats booked are going to be stored
        for (String value : zoneC) { //for every value in zoneB array
            if (!value.equals("")) { //if value has a name
                if (guestsMapC.containsKey(value)) { //if the value (name) already exists in the hashmap
                    //increases the number of seats booked by 1 (+1)
                    int seats = guestsMapC.get(value); //gets the seats this name already has booked
                    guestsMapC.put(value, seats + 1); //increase seats
                } else //if the value (name) does not exist in the hashmap
                    guestsMapC.put(value, 1); //add name and set booked seats to 1
            }
        }
        for (String value : guestsMapC.keySet())
            if (!value.equals("")) { //if value has a name
                int totalSeats = guestsMapC.get(value); //gets the seats that this name already has
                guests = guests + "Zone C, Name: " + value + ", Seats: " + totalSeats + ", Cost: " + totalSeats * 25 + "\n"; //concatenating the results in a string
            }

        //Center
        HashMap<String, Integer> guestsMapCE = new HashMap<>(); //declaring hashmap where the names of the guests and their number of seats booked are going to be stored
        for (String value : center) { //for every value in zoneB array
            if (!value.equals("")) { //if value has a name
                if (guestsMapCE.containsKey(value)) { //if the value (name) already exists in the hashmap
                    //increases the number of seats booked by 1 (+1)
                    int seats = guestsMapCE.get(value); //gets the seats this name already has booked
                    guestsMapCE.put(value, seats + 1); //increase seats
                } else //if the value (name) does not exist in the hashmap
                    guestsMapCE.put(value, 1); //add name and set booked seats to 1
            }
        }
        for (String value : guestsMapCE.keySet())
            if (!value.equals("")) { //if value has a name
                int totalSeats = guestsMapCE.get(value); //gets the seats that this name already has
                guests = guests + "Center, Name: " + value + ", Seats: " + totalSeats + ", Cost: " + totalSeats * 30 + "\n"; //concatenating the results in a string
            }

        //Sides
        HashMap<String, Integer> guestsMapS = new HashMap<>(); //declaring hashmap where the names of the guests and their number of seats booked are going to be stored
        for (String value : sides) { //for every value in zoneB array
            if (!value.equals("")) { //if value has a name
                if (guestsMapS.containsKey(value)) { //if the value (name) already exists in the hashmap
                    //increases the number of seats booked by 1 (+1)
                    int seats = guestsMapS.get(value); //gets the seats that this name already has booked
                    guestsMapS.put(value, seats + 1); //increase seats
                } else //if the value (name) does not exist in the hashmap
                    guestsMapS.put(value, 1); //add name and set booked seats to 1
            }
        }
        for (String value : guestsMapS.keySet())
            if (!value.equals("")) { //if value has a name
                int totalSeats = guestsMapS.get(value); //gets the seats that this name already has
                guests = guests + "Sides, Name: " + value + ", Seats: " + totalSeats + ", Cost: " + totalSeats * 20 + "\n"; //concatenating the results in a string
            }

        if (guests.equals("GUESTS LIST:\n"))
            return "No guests were found!\n";

        return guests;
    }

    @Override
    public String cancelSeats(String type, int number, String name) throws RemoteException {
        int canceledSeats;
        int bookedSeats = 0; //the seats that remain booked
        int tmp = number;

        if (number == 0)
            return "CANCELLATION FAILED due to " + number + " seats requested";

        //Zone A
        if (type.equals("A")) {
            //cancelling number seats if found & calculating remaining booked seats if found
            for (int i = 0; i < zoneA.length; i++)
                if (zoneA[i].equals(name)) { //for the seats booked on the name
                    if (tmp > 0) { //cancelling the number of seats requested
                        zoneA[i] = "";
                        tmp--;
                    } else //calculating the rest of the seats that remain booked in this zone for name
                        bookedSeats++;
                }

            canceledSeats = number - tmp; //seats that are cancelled
            if (canceledSeats == number) //if all the seats requested to be cancelled are found
                return "Cancelled " + canceledSeats + " seats in Zone A for Name: " + name + ". Booked seats remaining: " + bookedSeats;
            else if (canceledSeats < number && canceledSeats != 0) //if less than the requested seats to be cancelled are found
                return "Only " + canceledSeats + " found for this name in Zone A.\n" +
                        "Cancelled " + canceledSeats + " seats in Zone A for Name: " + name + ". Booked seats remaining: " + bookedSeats;
            else
                return "CANCELLATION FAILED due to 0 seats booked for this name in Zone A";

        } //Zone B
        else if (type.equals("B")) {
            //cancelling number seats if found & calculating remaining booked seats if found
            for (int i = 0; i < zoneB.length; i++)
                if (zoneB[i].equals(name)) { //for the seats booked on the name
                    if (tmp > 0) { //cancelling the number of seats requested
                        zoneB[i] = "";
                        tmp--;
                    } else //calculating the rest of the seats that remain booked in this zone for name
                        bookedSeats++;
                }

            canceledSeats = number - tmp; //seats that are cancelled
            if (canceledSeats == number) //if all the seats requested to be cancelled are found
                return "Cancelled " + canceledSeats + " seats in Zone B for Name: " + name + ". Booked seats remaining: " + bookedSeats;
            else if (canceledSeats < number && canceledSeats != 0) //if less than the requested seats to be cancelled are found
                return "Only " + canceledSeats + " found for this name in Zone B.\n" +
                        "Cancelled " + canceledSeats + " seats in Zone B for Name: " + name + ". Booked seats remaining: " + bookedSeats;
            else
                return "CANCELLATION FAILED due to 0 seats booked for this name in Zone B";

        } //Zone C
        else if (type.equals("C")) {
            //cancelling number seats if found & calculating remaining booked seats if found
            for (int i = 0; i < zoneC.length; i++)
                if (zoneC[i].equals(name)) { //for the seats booked on the name
                    if (tmp > 0) { //cancelling the number of seats requested
                        zoneC[i] = "";
                        tmp--;
                    } else //calculating the rest of the seats that remain booked in this zone for name
                        bookedSeats++;
                }

            canceledSeats = number - tmp; //seats that are cancelled
            if (canceledSeats == number) //if all the seats requested to be cancelled are found
                return "Cancelled " + canceledSeats + " seats in Zone C for Name: " + name + ". Booked seats remaining: " + bookedSeats;
            else if (canceledSeats < number && canceledSeats != 0) //if less than the requested seats to be cancelled are found
                return "Only " + canceledSeats + " found for this name in Zone C.\n" +
                        "Cancelled " + canceledSeats + " seats in Zone C for Name: " + name + ". Booked seats remaining: " + bookedSeats;
            else
                return "CANCELLATION FAILED due to 0 seats booked for this name in Zone C";

        } //Center
        else if (type.equals("CE")) {
            //cancelling number seats if found & calculating remaining booked seats if found
            for (int i = 0; i < center.length; i++)
                if (center[i].equals(name)) { //for the seats booked on the name
                    if (tmp > 0) { //cancelling the number of seats requested
                        center[i] = "";
                        tmp--;
                    } else //calculating the rest of the seats that remain booked in this zone for name
                        bookedSeats++;
                }

            canceledSeats = number - tmp; //seats that are cancelled
            if (canceledSeats == number) //if all the seats requested to be cancelled are found
                return "Cancelled " + canceledSeats + " seats in Center for Name: " + name + ". Booked seats remaining: " + bookedSeats;
            else if (canceledSeats < number && canceledSeats != 0) //if less than the requested seats to be cancelled are found
                return "Only " + canceledSeats + " found for this name in Center.\n" +
                        "Cancelled " + canceledSeats + " seats in Center for Name: " + name + ". Booked seats remaining: " + bookedSeats;
            else
                return "CANCELLATION FAILED due to 0 seats booked for this name in Center";

        } //Sides
        else if (type.equals("S")) {
            //cancelling number seats if found & calculating remaining booked seats if found
            for (int i = 0; i < sides.length; i++)
                if (sides[i].equals(name)) { //for the seats booked on the name
                    if (tmp > 0) { //cancelling the number of seats requested
                        sides[i] = "";
                        tmp--;
                    } else //calculating the rest of the seats that remain booked in this zone for name
                        bookedSeats++;
                }

            canceledSeats = number - tmp; //seats that are cancelled
            if (canceledSeats == number) //if all the seats requested to be cancelled are found
                return "Cancelled " + canceledSeats + " seats in Sides for Name: " + name + ". Booked seats remaining: " + bookedSeats;
            else if (canceledSeats < number && canceledSeats != 0) //if less than the requested seats to be cancelled are found
                return "Only " + canceledSeats + " found for this name in Sides.\n" +
                        "Cancelled " + canceledSeats + " seats in Sides for Name: " + name + ". Booked seats remaining: " + bookedSeats;
            else
                return "CANCELLATION FAILED due to 0 seats booked for this name in Sides";

        } else
            return "CANCELLATION FAILED due to wrong type of seats given. Must be one of the types: A, B, C, CE, S";
    }
}