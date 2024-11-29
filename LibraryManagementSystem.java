import java.util.*;

// Book class
class Book {
    private int id;
    private String title;
    private String author;
    private boolean isBorrowed;
    private String borrowedBy; // Student ID
    private Date borrowedDate; // Borrowed date
    private int fine; // Fine amount for overdue books

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
        this.borrowedBy = null;
        this.borrowedDate = null;
        this.fine = 0;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void borrow(String studentId) {
        this.isBorrowed = true;
        this.borrowedBy = studentId;
        this.borrowedDate = new Date(); // Set current date as borrowed date
    }

    public void returnBook() {
        this.isBorrowed = false;
        this.borrowedBy = null;
        this.borrowedDate = null;
        this.fine = 0; // Reset fine on return
    }

    public String getBorrowedBy() {
        return borrowedBy;
    }

    public Date getBorrowedDate() {
        return borrowedDate;
    }

    public int calculateFine() {
        if (isBorrowed && borrowedDate != null) {
            long difference = (new Date().getTime() - borrowedDate.getTime()) / (1000 * 60 * 60 * 24);
            if (difference > 14) { // 14-day borrowing duration
                fine = (int) (difference - 14) * 10; // Fine: 10 units per day
            }
        }
        return fine;
    }

    public int getFine() {
        return fine;
    }

    @Override
    public String toString() {
        String status = isBorrowed ? "Borrowed by: " + borrowedBy : "Available";
        return "ID: " + id + ", Title: " + title + ", Author: " + author + " (" + status + ")";
    }
}

// Library class
class Library {
    private Map<Integer, Book> books = new HashMap<>();
    private int nextBookId = 1;

    public void addBook(String title, String author) {
        Book newBook = new Book(nextBookId++, title, author);
        books.put(newBook.getId(), newBook);
        System.out.println("Book added successfully: " + newBook);
    }

    public void removeBook(int id) {
        if (books.containsKey(id)) {
            Book removed = books.remove(id);
            System.out.println("Book removed: " + removed);
        } else {
            System.out.println("Book with ID " + id + " not found.");
        }
    }

    public void borrowBook(int id, String studentId) {
        Book book = books.get(id);
        if (book != null && !book.isBorrowed()) {
            book.borrow(studentId);
            System.out.println("Book borrowed successfully: " + book);
        } else if (book != null) {
            System.out.println("Book is already borrowed by: " + book.getBorrowedBy());
        } else {
            System.out.println("Book with ID " + id + " not found.");
        }
    }

    public void returnBook(int id) {
        Book book = books.get(id);
        if (book != null && book.isBorrowed()) {
            int fine = book.calculateFine();
            if (fine > 0) {
                System.out.println("This book is overdue! Fine: " + fine + " units.");
            }
            book.returnBook();
            System.out.println("Book returned successfully: " + book);
        } else if (book != null) {
            System.out.println("Book is not borrowed.");
        } else {
            System.out.println("Book with ID " + id + " not found.");
        }
    }

    public void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
        } else {
            System.out.println("Available books in the library:");
            for (Book book : books.values()) {
                System.out.println(book);
            }
        }
    }

    public void viewStudentBooks(String studentId) {
        boolean found = false;
        System.out.println("Books borrowed by Student ID: " + studentId);
        for (Book book : books.values()) {
            if (studentId.equals(book.getBorrowedBy())) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books borrowed by this student.");
        }
    }

    public void viewFines() {
        System.out.println("Books with fines:");
        boolean found = false;
        for (Book book : books.values()) {
            if (book.isBorrowed() && book.calculateFine() > 0) {
                System.out.println(book + " | Fine: " + book.getFine() + " units.");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No fines to display.");
        }
    }

    public void clearFine(int id) {
        Book book = books.get(id);
        if (book != null && book.getFine() > 0) {
            System.out.println("Clearing fine for Book ID: " + id + ". Fine: " + book.getFine() + " units.");
            book.returnBook();
        } else {
            System.out.println("No fine found for this book.");
        }
    }

    // Admin-specific functions
    public void searchBooks(String query) {
        boolean found = false;
        for (Book book : books.values()) {
            if (book.getTitle().toLowerCase().contains(query.toLowerCase()) || book.getAuthor().toLowerCase().contains(query.toLowerCase())) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books found matching the search query.");
        }
    }

    public void adminBorrowBook(int bookId, String studentId) {
        borrowBook(bookId, studentId);
    }

    public void generateReport() {
        System.out.println("Library Borrowed Books Report:");
        for (Book book : books.values()) {
            if (book.isBorrowed()) {
                System.out.println(book + " | Fine: " + book.calculateFine() + " units.");
            }
        }
    }

    // Student-specific functions
    public void studentSearchBooks(String query) {
        boolean found = false;
        for (Book book : books.values()) {
            if (book.getTitle().toLowerCase().contains(query.toLowerCase()) || book.getAuthor().toLowerCase().contains(query.toLowerCase())) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books found matching the search query.");
        }
    }

    public void giveFeedback(String studentId, String feedback) {
        System.out.println("Feedback from Student " + studentId + ": " + feedback);
    }
}

// Main class
public class LibraryManagementSystem {
    private static Map<String, String> admins = new HashMap<>();
    private static Library library = new Library();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        admins.put("admin", "1234"); // Default admin account

        while (true) {
            System.out.println("\nWelcome to the Library Management System");
            System.out.println("1. Admin");
            System.out.println("2. Student");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    adminLogin();
                    break;
                case 2:
                    handleStudent();
                    break;
                case 3:
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Returning to the main menu.");
            }
        }
    }

    private static void adminLogin() {
        System.out.println("1. Create Account");
        System.out.println("2. Login");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            System.out.print("Enter new username: ");
            String newUsername = scanner.nextLine();
            System.out.print("Enter new password: ");
            String newPassword = scanner.nextLine();
            if (admins.containsKey(newUsername)) {
                System.out.println("Account already exists!");
            } else {
                admins.put(newUsername, newPassword);
                System.out.println("Account created successfully!");
            }
        } else if (choice == 2) {
            System.out.print("Enter admin username: ");
            String username = scanner.nextLine();
            System.out.print("Enter admin password: ");
            String password = scanner.nextLine();

            if (!admins.containsKey(username) || !admins.get(username).equals(password)) {
                System.out.println("Invalid credentials. Returning to main menu.");
                return;
            }
            handleAdmin();
        } else {
            System.out.println("Invalid choice. Returning to main menu.");
        }
    }

    private static void handleAdmin() {
        while (true) {
            System.out.println("\nAdmin Menu");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. View All Books");
            System.out.println("6. View Fines");
            System.out.println("7. Clear Fine");
            System.out.println("8. Search Books");
            System.out.println("9. Student Borrow");
            System.out.println("10. Generate Report");
            System.out.println("11. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    library.addBook(title, author);
                    break;
                case 2:
                    System.out.print("Enter book ID to remove: ");
                    int removeId = scanner.nextInt();
                    library.removeBook(removeId);
                    break;
                case 3:
                    System.out.print("Enter book ID to borrow: ");
                    int borrowId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter student ID: ");
                    String studentId = scanner.nextLine();
                    library.borrowBook(borrowId, studentId);
                    break;
                case 4:
                    System.out.print("Enter book ID to return: ");
                    int returnId = scanner.nextInt();
                    library.returnBook(returnId);
                    break;
                case 5:
                    library.viewBooks();
                    break;
                case 6:
                    library.viewFines();
                    break;
                case 7:
                    System.out.print("Enter book ID to clear fine: ");
                    int fineId = scanner.nextInt();
                    library.clearFine(fineId);
                    break;
                case 8:
                    System.out.print("Enter search query (title or author): ");
                    String query = scanner.nextLine();
                    library.searchBooks(query);
                    break;
                case 9:
                    System.out.print("Enter book ID to borrow: ");
                    int bookId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter student ID: ");
                    String student = scanner.nextLine();
                    library.adminBorrowBook(bookId, student);
                    break;
                case 10:
                    library.generateReport();
                    break;
                case 11:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void handleStudent() {
        System.out.print("Enter your Student ID: ");
        String studentId = scanner.nextLine();
        while (true) {
            System.out.println("\nStudent Menu");
            System.out.println("1. View All Books");
            System.out.println("2. View Borrowed Books");
            System.out.println("3. Search Books");
            System.out.println("4. Give Feedback");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    library.viewBooks();
                    break;
                case 2:
                    library.viewStudentBooks(studentId);
                    break;
                case 3:
                    System.out.print("Enter search query (title or author): ");
                    String query = scanner.nextLine();
                    library.studentSearchBooks(query);
                    break;
                case 4:
                    System.out.print("Enter your feedback: ");
                    String feedback = scanner.nextLine();
                    library.giveFeedback(studentId, feedback);
                    break;
                case 5:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
