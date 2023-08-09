# Theater Booking Application using RMI
A Server-Client project made in Java with the use of RMI for managing the booking process in a theater.

## Description
In this Theater Booking Application a client can:
- get the available seats, per zone [list]
- book seats [book]
- get the total number of booked seats made by a specific name [guests]
- cancel seats [cancel]

The server is responsible for handling the above functionalities.

In the specific theater there are 5 zones in total. For each one of them there are:
- 100 seats in zone A (type A), each seat costs 45 €
- 200 seats in zone B (type B), each seat costs 35 €
- 400 seats in zone C (type C), each seat costs 25 €
- 225 seats in Center (type CE), each seat costs 30 €
- 75 seats on Sides (type S), each seat costs 20 €

## Prerequisites
- JDK installed

## How to compile
```
  javac *.java
```

## How to run
Run Server:
```
  java THServer
```
Run Client (**one** of the following!):
```
  java THClient list <hostname>
  java THClient book <hostname> <type> <number> <name>
  java THClient guests <hostname>
  java THClient cancel <hostname> <type> <number> <name>
```
*Server and Client should be running in different terminals.*

## Setup Details
- JDK 20.0.1
