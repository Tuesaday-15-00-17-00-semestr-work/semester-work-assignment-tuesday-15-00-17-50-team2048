CREATE TABLE IF NOT EXISTS Roles (
    role_id INTEGER PRIMARY KEY,
    role_name TEXT NOT NULL
);
INSERT OR IGNORE INTO Roles (role_id, role_name) VALUES (1, 'Library Administrator');
INSERT OR IGNORE INTO Roles (role_id, role_name) VALUES (2, 'User');

CREATE TABLE IF NOT EXISTS Books (
    book_id INTEGER PRIMARY KEY AUTOINCREMENT,
    title VARCHAR(50) NOT NULL,
    author VARCHAR(50) NOT NULL,
    isbn VARCHAR(50) NOT NULL,
    available_copies INTEGER
);

CREATE TABLE IF NOT EXISTS Users (
    user_id INTEGER PRIMARY KEY AUTOINCREMENT,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(250) NOT NULL,
    role_id INTEGER NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL,
    FOREIGN KEY (role_id) REFERENCES Roles(role_id)
);

CREATE TABLE IF NOT EXISTS Transactions (
    transaction_id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id INTEGER,
    book_id INTEGER,
    actions INT,
    date_of TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (book_id) REFERENCES Books(book_id)
);

