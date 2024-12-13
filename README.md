# Library Management System

## Project Overview
The Library Management System is a semesterial project designed to facilitate resource sharing among students. It provides an efficient platform to register, manage, borrow, and lend books. There are 2 roles, User and Admin and each can create, borrow, retrun books.

## Features
- **User Registration and Login**: Secure user management with distinct roles (e.g., student, admin).
- **Book Management**: Add, update, view, and remove books in the system.
- **Borrowing and Lending**: Track which books are borrowed or available.

## Technology Stack
- **Frontend**: JavaFX for an intuitive user interface.
- **Backend**: Spring Boot for robust and scalable application logic.
- **Database**: SQLite for efficient data storage and retrieval.
- **API**: RESTful API for seamless client-server communication.
![image](https://github.com/user-attachments/assets/d066696b-1413-4fa4-9d05-20dfefda975a)


## Application Architecture
- **Client Layer**: JavaFX interface for user interactions.
- **Service Layer**: Spring Boot handles business logic.
- **Data Layer**: SQLite stores user, book, role, and transaction data.
- **API Layer**: RESTful services for CRUD operations and interactions.

## Database Design
The application uses a relational database with the following tables:
![image](https://github.com/user-attachments/assets/a12aae04-5d9d-44bc-a2c2-64b5aeb8372d)

- **Users Table**: Manages user details and roles.
  
![image](https://github.com/user-attachments/assets/660a675b-5b74-455a-a632-5a8595e8b2e2)

- **Books Table**: Stores book details and availability.

![image](https://github.com/user-attachments/assets/0abbd2fa-5a95-41ae-b602-2dbdb94b1cde)

- **Roles Table**: Defines access levels for users.
  
![image](https://github.com/user-attachments/assets/95c0acb8-1a5f-434f-8dc8-200992d7e728)

- **Transaction Table**: Tracks borrow and return activities.
  
![image](https://github.com/user-attachments/assets/ca4a4ff4-8d93-4f44-a055-bc7ec1292f03)

- **Borrowed Table**: Users library is stored in here.
  
![image](https://github.com/user-attachments/assets/a5c12e38-a734-4ce5-bab5-a9ea7d4548ca)


## Key Classes and Functions
- **Records**: Contains expample of how the instance should look like
  
  ![image](https://github.com/user-attachments/assets/e7e11ce2-1a5b-48e3-968c-974c891eb04d)

  ![image](https://github.com/user-attachments/assets/3e391017-ab12-469a-a596-d857cb70d8a6)

- **Repositories**: Handles interacting with database. This is where CRUD methods are made.

  ![image](https://github.com/user-attachments/assets/d7b692bf-13b0-4136-ad05-651907aa6274)

  ![image](https://github.com/user-attachments/assets/9eca9c96-5850-46bf-8b80-8b6202a102cf)

- **Controllers**: Just class that calls CRUD methods. This is where we are "controlling" operations.

  ![image](https://github.com/user-attachments/assets/9eb32e97-864e-409a-b3ed-d30b48eba7c6)

  ![image](https://github.com/user-attachments/assets/551d8eaf-e581-45b1-8c42-36616173105b)


- **GET**: Method where we "get" some information.

  ![image](https://github.com/user-attachments/assets/a0691f03-afe6-443c-a929-fd5a4887b69f)

  ![image](https://github.com/user-attachments/assets/9028e05b-843b-4588-ac35-96ed2c23f0ce)


- **CREATE**: Method where we create instance in the database.

  ![image](https://github.com/user-attachments/assets/e3db299b-9b28-43e9-be8d-64d7dbfe943d)

  ![image](https://github.com/user-attachments/assets/0c332b21-d62c-48f1-a7c9-db07d95bfd13)


- **DELETE**: Method where we delete instance from database.

  ![image](https://github.com/user-attachments/assets/5b3eea90-c4d0-4113-82b3-2886d3fed2f9)

  ![image](https://github.com/user-attachments/assets/507f2c13-3620-438a-b815-63b4b9d659da)



## User Interface
The JavaFX UI provides:
- **Main window**: Opens widnow for either login or register.

  ![image](https://github.com/user-attachments/assets/32480366-1eae-49a4-8d48-f7e094174911)

- **Home window**:

  ![image](https://github.com/user-attachments/assets/b6efe679-f381-4800-95bb-90349f7c6d4a)

- **Users**: Window where u can see users and delete them if necessary.

  ![image](https://github.com/user-attachments/assets/79c6615b-e17c-4e29-be91-63a9e35c32ca)

- **My Books**: Window where you see which books u borrowed.

  ![image](https://github.com/user-attachments/assets/2de9586f-6f19-4ff4-b43e-99cb83d51168)

- **View Books**: Window where u can see all books and manage them.

  ![image](https://github.com/user-attachments/assets/064b9f57-b71a-4121-9907-fe88f9e764c9)

- *Transaction History**: Show who borrow what book.

  


## Challenges and Solutions
### Challenges
- Setting up client, server and database
- Connecting them
- Displaying right things on client side
- Some security

### Solutions
- For server i hod to setup Springboot framework
- Database was easily done with SQLite
- Clientside was tricky but done with JavaFX
- Connection from Frontend to backend was done through HTTP request
- Security is missing

## How to Run the Project
1. First you need to open backend and start
2. Secondly you need to open frontend separatly and start
3. Then you just work in the GUI
   ```
4. Install dependencies and configure the database.
5. Run the application using your IDE or the command line.

## Credits
Team2048 - Collaborative work by [Team Member Names].
