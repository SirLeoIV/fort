# Group 16
## Fort!

Welcome to our fort. Can you break in?

## Starting the server
+ Make sure you have Java 17 installed
+ Start the server by running the jar file
```bash
java -jar fort.jar
```

+ The server runs on http://localhost:8080
+ The server will start an embedded H2 database, which will be created in the current directory

## Interacting with the server
+ Use our user interface: https://darkslategray-swan-903516.hostingersite.com/
  + The user interface connects to a server, deployed online in the cloud per default.
  + If you are the first to connect to the online server in a while, it might take a while to start up and process your request. Your request does not get lost, so please be patient.
  + If you want to connect to your local server, you can change the connection mode from ``Online Server`` to ``Custom Server`` and enter the connection details (host: http://localhost; port: 8080)
+ Use the API directly: http://localhost:8080/swagger-ui/index.html


## API Documentation
+ The API documentation can be found at http://localhost:8080/swagger-ui/index.html
+ There are 4 endpoints:
  + ``/health (GET)`` verify that the server is up and running
  + ``/client (POST)`` create a new client
  + ``/counter (POST)`` gets the client and their counter
  + ``/action (POST)`` adds an action to the client's counter
+ All the endpoints are reached via the base url http://localhost:8080

## GitHubs
+ [Frontend](https://github.com/SirLeoIV/fort-gate)
+ [Backend](https://github.com/SirLeoIV/fort)
