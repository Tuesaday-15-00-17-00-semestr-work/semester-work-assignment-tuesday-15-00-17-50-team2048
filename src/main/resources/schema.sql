CREATE TABLE IF NOT EXISTS Roles (
    role_id INTEGER PRIMARY KEY,
    role_name TEXT NOT NULL
);

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
    user_id INTEGER NOT NULL,
    book_id INTEGER NOT NULL,
    actions VARCHAR(20) NOT NULL,
    date_of VARCHAR(20),
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (book_id) REFERENCES Books(book_id)
);

CREATE TABLE IF NOT EXISTS Borrowed (
    borrow_id INTEGER PRIMARY KEY AUTOINCREMENT,
    title VARCHAR(50) NOT NULL,
    author VARCHAR(50) NOT NULL,
    date_of VARCHAR(20) NOT NULL,
    transaction_id INTEGER NOT NULL,
    FOREIGN KEY (title) REFERENCES Books(title),
    FOREIGN KEY (author) REFERENCES Books(author),
    FOREIGN KEY (date_of) REFERENCES Transactions(date_of)
);

