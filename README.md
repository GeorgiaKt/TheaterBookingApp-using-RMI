# Theater Booking Application using RMI
A Server-Client project made in Java with the use of RMI for managing the booking process in a theater.

## Description


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
