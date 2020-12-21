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
		ArrayList<Book> result = new ArrayList<Book>();
		// Try reading the file
		try {
			File textFile = new File(this.fileName);
			Scanner scanner = new Scanner(textFile);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] splitLine = line.split("\t");
				if (splitLine.length != 3) continue;
				// Create each part of the book object
				// It is assumed that the file is formatted properly
				String title = splitLine[0];
				String author = splitLine[1];
				int year = Integer.parseInt(splitLine[2]);
				Book book = new Book(title, author, year);
				result.add(book);
			}
			scanner.close();
		} catch(FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}
		return result;
	}

}
