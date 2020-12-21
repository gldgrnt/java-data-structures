import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/*
 * SD2x Homework #8
 * This class represents the Logic Tier in the three-tier architecture.
 * Implement the appropriate methods for this tier below.
 */

public class LogicTier {
	
	private DataTier dataTier; // link to the Data Tier
	
	public LogicTier(DataTier dataTier) {
		this.dataTier = dataTier;
	}
	
	public List<String> findBookTitlesByAuthor(String name) {		
		List<String> result = new ArrayList<String>();
		if (name.isBlank() || name.isEmpty() || name == null) return result;
		// Filter and sort the retrieved books
		List<Book> allBooks = _getAllBooks();
		for (Book book : allBooks) {
			boolean containsName = book.getAuthor().contains(name.toLowerCase());
			// Add to results array
			if (!containsName) {
				allBooks.remove(book);
			}
		}
		Collections.sort(allBooks);
		result = _getBookTitles(allBooks);
		return result;
	}
	
	public int findNumberOfBooksInYear(int year) {
		int result = 0;
		String yearString = String.valueOf(year);
		if (yearString.length() != 4) return result;
		// Get and filter books that aren't in the target year
		List<Book> allBooks = this._getAllBooks();
		for (Book book : allBooks) {
			boolean isSameYear = book.getPublicationYear() == year;
			if (!isSameYear) {
				allBooks.remove(book);
			}
		}
		result = allBooks.size();
		return result;
	}
	
	private List<Book> _getAllBooks() {
		List<Book> books = new ArrayList<Book>();
		books = dataTier.getAllBooks();
		return books;
	}
	
	private List<String> _getBookTitles(List<Book> books) {
		List<String> result = new ArrayList<String>();
		for (Book book : books) {
			result.add(book.getTitle());
		}
		return result;
	}
}
