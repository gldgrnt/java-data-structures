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
		List<Book> filteredBooks = new ArrayList<Book>();
		if (name.isBlank() || name.isEmpty() || name == null) return null;
		name.toLowerCase();
		// Filter and sort the retrieved books
		List<Book> allBooks = getAllBooksFromData();
		for (Book book : allBooks) {
			boolean containsName = book.getAuthor().contains(name);
			// Add to results array
			if (containsName) {
				filteredBooks.add(book);
			}
		}
		Collections.sort(filteredBooks);
		return getTitlesFromBooks(filteredBooks);
	}
	
	public int findNumberOfBooksInYear(int year) {
		List<Book> filteredBooks = new ArrayList<Book>();
		String yearString = String.valueOf(year);
		if (yearString.length() != 4) return 0;
		// Get and filter books that aren't in the target year
		List<Book> allBooks = getAllBooksFromData();
		for (Book book : allBooks) {
			boolean isSameYear = book.getPublicationYear() == year;
			if (isSameYear) {
				filteredBooks.add(book);
			}
		}
		return filteredBooks.size();
	}
	
	public List<String> getAllBookTitles() {
		List<String> titles = new ArrayList<String>();
		List<Book> books = dataTier.getAllBooks();
		for ( Book book : books) {
			titles.add(book.getTitle());
		}
		return titles;
	}
	
	private List<Book> getAllBooksFromData() {
		List<Book> books = new ArrayList<Book>();
		books = dataTier.getAllBooks();
		return books;
	}
	
	private List<String> getTitlesFromBooks(List<Book> books) {
		List<String> result = new ArrayList<String>();
		for (Book book : books) {
			result.add(book.getTitle());
		}
		return result;
	}
}
