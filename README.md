# MULTITHREADED-CHAT-APPLICATION

*COMPANY*: CODTECH IT SOLUTIONS

*NAME*: NAVEEN SAHU

*INTERN ID*: CTIS7160

*DOMAIN*: JAVA  PROGRAMING

*DURATION*: 4 WEEKS

*MENTOR*: NEELA SANTOSH

## This project is a Multithreaded Client-Server Chat Application developed in Java as part of my internship task at CodTech Solutions. It enables multiple users to connect to a central server and communicate with each other in real time through a console-based interface.

Features
Supports multiple clients simultaneously using multithreading
Real-time message broadcasting to all connected users
Unique username identification for each client
Handles client connection and disconnection gracefully
Simple and interactive console-based chat system
Continuous listening for incoming messages using separate threads

WORKING
The server starts and listens for incoming client connections on a specified port.

Each new client connection is handled by a separate thread (ClientHandler).

The client enters a username and connects to the server.

Messages sent by a client are broadcasted to all other connected clients.

A dedicated thread on the client side continuously listens for incoming messages.

Users can exit the chat by typing exit or disconnect.


## OUTPUT

SERVER 

Server started on port 12345

New client connected: /127.0.0.1

User user1 has connected.

New client connected: /127.0.0.1

User user2 has connected.

[user2] : hi

[user1] : hello

[user2] : exit client disconnected

Client disconnected: /127.0.0.1

[user1] : exit client disconnected

Client disconnected: /127.0.0.1


CLIENT 1


Enter your username: user1

Welcome to the chat, user1!

[user2] : hi

hello

[user2] : exit client disconnected

disconnect


CLIENT 2
Enter your username: user2

Welcome to the chat, user2!

hi

[user1] : hello

exit
