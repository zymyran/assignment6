import java.util.*;

// Interface for the facade
interface LibraryFacade {
	void borrowBook(String bookTitle, String username);
	void returnBook(String bookTitle, String username);
	List<Book> searchBooksByTitle(String title);
	List<Book> searchBooksByAuthor(String author);
	boolean checkBookAvailability(String bookTitle);

}

// Book class representing a book entity
class Book {
	private String title;
	private String author;
	private boolean available;

	public Book(String title, String author) {
		this.title = title;
		this.author = author;
		this.available = true;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

// Getters and setters
	// Not shown for brevity
}

// Subsystem for managing book inventory
class BookInventorySystem {
	public static boolean checkAvailability(String bookTitle) {
		// Implementation to check book availability in the inventory
		return true; // Dummy implementation
	}
}

// Subsystem for managing user accounts

class UserManagementSystem {
	private static Map<String, Set<String>> borrowedBooks = new HashMap<>();

	public static void borrowBook(String bookTitle, String username) {
		if (borrowedBooks.containsKey(username) && borrowedBooks.get(username).size() >= 3) {
			System.out.println(username + " has already borrowed the maximum number of books.");
			return;
		}

		borrowedBooks.putIfAbsent(username, new HashSet<>());
		borrowedBooks.get(username).add(bookTitle);

		System.out.println(username + " borrowed " + bookTitle);
	}

	public static void returnBook(String bookTitle, String username) {
		if (!borrowedBooks.containsKey(username) || !borrowedBooks.get(username).contains(bookTitle)) {
			System.out.println(username + " has not borrowed " + bookTitle + ".");
			return;
		}

		borrowedBooks.get(username).remove(bookTitle);

		System.out.println(username + " returned " + bookTitle);
	}

}


// Facade implementation
class LibraryFacadeImpl implements LibraryFacade {
	@Override
	public void borrowBook(String bookTitle, String username) {
		if (BookInventorySystem.checkAvailability(bookTitle)) {
			UserManagementSystem.borrowBook(bookTitle, username);
			System.out.println(username + " borrowed " + bookTitle);
		} else {
			System.out.println("Sorry, " + bookTitle + " is not available for borrowing.");
		}
	}

	@Override
	public void returnBook(String bookTitle, String username) {
		UserManagementSystem.returnBook(bookTitle, username);
		System.out.println(username + " returned " + bookTitle);
	}

	@Override
	public List<Book> searchBooksByTitle(String title) {
		List<Book> books = new ArrayList<>();
		books.add(new Book("Book 1", "Author 1"));
		books.add(new Book("Book 2", "Author 2"));
		return books;
	}

	@Override
	public List<Book> searchBooksByAuthor(String author) {
		List<Book> books = new ArrayList<>();
		books.add(new Book("Book 3", "Author 1"));
		books.add(new Book("Book 4", "Author 1"));
		return books;
	}

	@Override
	public boolean checkBookAvailability(String bookTitle) {
		return BookInventorySystem.checkAvailability(bookTitle);
	}
}

// Test class
public class LibraryManagementTest {
	public static void main(String[] args) {
		LibraryFacade facade = new LibraryFacadeImpl();

		facade.borrowBook("The Great Gatsby", "user123");

		facade.returnBook("The Great Gatsby", "user123");

		boolean isAvailable = facade.checkBookAvailability("The Great Gatsby");
		System.out.println("Is 'The Great Gatsby' available? " + isAvailable);

		List<Book> booksByTitle = facade.searchBooksByTitle("Book 1");
		System.out.println("Books with title 'Book 1':");
		for (Book book : booksByTitle) {
			System.out.println(book.getTitle() + " by " + book.getAuthor());
		}

		List<Book> booksByAuthor = facade.searchBooksByAuthor("Author 1");
		System.out.println("Books by author 'Author 1':");
		for (Book book : booksByAuthor) {
			System.out.println(book.getTitle() + " by " + book.getAuthor());
		}
	}
}
