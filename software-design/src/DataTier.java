import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;


/*
 * SD2x Homework #8
 * This class represents the Data Tier in the three-tier architecture.
 * Implement the appropriate methods for this tier below.
 */

public class DataTier {
	
	private String fileName; // the name of the file to read
	
	public DataTier(String inputSource) {
		fileName = inputSource;
	}
	
	/*
	 * Read the data file containing information about the books, create 
	 * Book objects for each, and then return the Book objects. 
	 * Format of each line in file: [title][tab][author][tab][year] 
	 */
	public List<Book> getAllBooks() {
		List<Book> bookData = new ArrayList<Book>(); 
		
		try {
			Scanner file = new Scanner(new File(fileName));
			
			while (file.hasNextLine()) {
				String[] bookInfo =  file.nextLine().split("\t");
				// Check that data is formatted properly
				if (bookInfo.length != 3)
					continue;
				String title = bookInfo[0];
				String author = bookInfo[1];
				int year = Integer.parseInt(bookInfo[2]);
				bookData.add(new Book(title, author, year));
			}
				
		} catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return bookData;
	}

}
