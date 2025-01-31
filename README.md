# Library Management System

## Overview

This project is a **Library Management System** designed to assist libraries in managing their operations efficiently. The system is tailored for library staff and administrators, allowing them to streamline tasks such as member registration, book tracking, and transaction management. The software provides a user-friendly interface and advanced features like real-time book tracking and overdue notifications, ensuring that library operations run smoothly and effectively.

---

## Features

### Core Functionalities
1. **Sign-Up Function**: Allows new users to register and add themselves to the library's database.
2. **View Members**: Displays a list of all registered members in the library.
3. **View Books**: Displays a list of all books available in the library.
4. **Library Overview**: Provides a dashboard with key statistics, such as the number of books, members, and borrowed books.
5. **User Information**: Allows members to view their personal information and current borrowed books.

### Advanced Features
- **Real-Time Book Tracking**: Ensures that the availability status of books is always up-to-date.
- **Overdue Notifications**: Notifies users and staff about overdue books, improving book return rates.
- **Customized Views**: Different interfaces for librarians and regular users, with administrative functions for librarians and simplified views for users.
- **Add/Remove Books**: Librarians can add or remove books from the database, ensuring accurate inventory management.
- **Borrow Books**: Users can borrow books, and the system records the borrowing date, due date, and renewals.
- **Check Overdue Books**: Both librarians and users can check for overdue books, with notifications sent to users.

---

## Motivation & Research

The project was inspired by the need to improve existing library management systems, which often lack advanced features like real-time tracking and overdue notifications. The system addresses these shortcomings by providing a modern, user-friendly interface and enhanced functionalities to streamline library operations.

### Key Findings from Research:
- **Real-Time Tracking**: Many systems do not update book availability instantly, leading to confusion. This system ensures real-time updates.
- **User-Friendly Interface**: Existing systems often have outdated interfaces. This system features a modern, intuitive design for easy navigation.

---

## Specifications

### Program Functions
- **Sign-Up**: New users can register and be added to the library's database.
- **View Members**: Displays all registered members.
- **View Books**: Lists all available books in the library.
- **Library Overview**: Provides a summary of key statistics, including the number of books, members, and borrowed books.
- **User Information**: Allows users to view their personal details and borrowed books.

### User Interface
- **Login Screen**: Ensures only authorized personnel and registered users can access the system.
- **Sign-Up Form**: Collects essential user information and adds new members to the database.
- **Overview Dashboard**: Displays key statistics and summaries of library operations.
- **Member Overview**: Lists all registered members with detailed information.
- **Book Overview**: Lists all books with details like title, author, ISBN, and availability status.
- **User Overview**: Allows users to view their personal information and borrowing status.

### Help System
- **Help Screen**: Provides text-based tutorials on how to use the system.
- **Tooltips**: Offers guidance on using different features of the program.

---

## Data Storage

### Permanent Data Storage
Data is stored in an **MS Access database** with the following tables:
1. **tblMembers**: Stores member information (memberID, firstName, lastName, username, passcode).
2. **tblBooks**: Stores book details (BookID, Title, ISBN, AuthorID).
3. **tblAuthors**: Stores author information (AuthorID, AuthorName).
4. **tblBorrowedBooks**: Tracks borrowed books (BorrowedID, BookID, MemberID, BorrowedDate, ReturnDate).

### Secondary Storage Design
- **Microsoft Access**: Chosen for its ease of use, compatibility with Java (via JDBC), and ability to handle large amounts of data.
- **Relationships**: Primary and foreign keys ensure data integrity and proper relationships between tables (e.g., linking books to authors and borrowed books to members).

---

## Hardware and Software Requirements

### Programmer Requirements
- **IDE**: IntelliJ IDEA with Java and JDK version 21.
- **Database**: MS Access.
- **Libraries**: JDBC for database connectivity.
- **Hardware**: 
  - Processor: Intel i3 or equivalent.
  - RAM: At least 4GB.
  - Storage: Minimum 500MB hard drive or SSD.
  - Operating System: Windows 10 or later.
  - Display: Monitor with at least 1280x800 resolution.
  - Peripherals: Standard keyboard and mouse.

### User Requirements
- **Hardware**: Similar to programmer requirements.
- **Software**: Java 21 or higher for running the application.

---

## Advanced Techniques

### JavaFX
- **CSS Integration**: Used to apply themes and styles to the UI components, allowing for advanced customization of colors, fonts, borders, and layout.
- **Charts**: JavaFX is used to create pie charts for visualizing data, such as the distribution of book authors.

### Maven
- **Build Automation**: Maven is used for managing dependencies and building the project, ensuring scalability and ease of use.

---

## Testing

### Functional Testing
- **Sign-Up Test**: Ensures that new users can register successfully.
- **Add Book Test**: Validates that librarians can add new books to the database.
- **Borrow Book Test**: Ensures that users can borrow books and that the system records the transaction correctly.

### Test Results
- **Sign-Up Test**: Validates email and password inputs.
- **Add Book Test**: Ensures that book details are correctly added to the database.
- **Borrow Book Test**: Tracks borrowed books and due dates accurately.

---

## Evaluation

### Strengths
- **User-Friendly Interface**: Modern and intuitive design for easy navigation.
- **Real-Time Tracking**: Ensures accurate and up-to-date book availability.
- **Overdue Notifications**: Improves book return rates and library efficiency.
- **Customized Views**: Different interfaces for librarians and users, enhancing usability.

### Weaknesses
- **Performance on Low-End Devices**: Multiple scene openings may impact performance on lower-end devices.
- **Limited Help Menu**: The help section could be more comprehensive with additional guides and tutorials.
- **No Email Validation**: The sign-up process does not validate email addresses.

### Improvements
- **Consolidate FXML Files**: Improve performance by reducing the number of scene openings.
- **Enhance Help Menu**: Add more detailed guides and tutorials.
- **Email Validation**: Implement email validation during the sign-up process.
- **Edit Functionality**: Add the ability to edit book and member details.

---

## Conclusion

This Library Management System provides a robust and user-friendly solution for managing library operations. With features like real-time tracking, overdue notifications, and customized views, the system enhances efficiency and improves the user experience for both librarians and members. While there are areas for improvement, the application offers a comprehensive set of tools to streamline library management tasks.

---

For more details, visit the [GitHub repository](https://github.com/GuyMcKechnie/HospitalApplication).
