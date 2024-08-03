# Library Management System

## Introduction

The Library Management System is a Java-based application designed to manage library operations efficiently. This software allows librarians to manage books, users, and transactions such as borrowing and returning books. It provides a user-friendly interface with features that simplify the administration of library resources.

## Features

- **Book Management:** Add, update, delete, and search for books in the library database.
- **User Management:** Manage user accounts, including registration, updating details, and removing users.
- **Transaction Management:** Record and track book borrowings and returns.
- **Search Functionality:** Quickly search for books or users using various filters.
- **Overdue Management:** Track overdue books and notify users of due dates.
- **Report Generation:** Generate reports on book inventory, user activities, and transactions.

## Technologies Used

- **Java:** Core programming language.
- **JavaFX:** For building the graphical user interface.
- **MS Access (or other RDBMS):** For database management.
- **Maven:** Project build and dependency management.
- **JUnit:** For unit testing.

## Installation

### Prerequisites

- Java Development Kit (JDK) 8 or later
- Maven
- MS Access

### Steps

1. **Clone the repository:**

   ```bash
   git clone https://github.com/GuyMcKechnie/Library-Management-System.git
   cd library-management-system
   ```

2. **Set up the database:**

   - Create a MS Access database.

3. **Build the project:**

   ```bash
   mvn clean install
   ```

4. **Run the application:**

   ```bash
   mvn javafx:run
   ```

5. **Create an executable JAR (optional):**

   ```bash
   mvn package
   ```

   This will generate a JAR file in the `target` directory.

## Usage

- **Log In:** Start the application and log in with your credentials.
- **Dashboard:** Access the main features like book management, user management, and transaction records from the dashboard.
- **Add/Remove Books:** Use the book management section to add new books or remove existing ones.
- **Manage Users:** Register new users or update existing user details.
- **Borrow/Return Books:** Record book borrowings and returns using the transaction management feature.
- **Generate Reports:** Create reports to view summaries of the library's activities.

## Contributing

If you'd like to contribute to this project, please follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Commit your changes (`git commit -m 'Add new feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Open a pull request.

## License

This project is licensed under the MIT License.

## Contact

For any inquiries or support, please contact:

- **Guy McKechnie:** [mckechnieguy.mg@gmail.com](mailto:mckechnieguy.mg@gmail.com)
- **GitHub:** [GuyMcKechnie](https://github.com/GuyMcKechnie)
