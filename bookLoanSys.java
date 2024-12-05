import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class bookLoanSys {
	public void loanBook(String bookTitle, String bookAuthor, String bookISBN, String bookCategory, String bookFictionorNon, String dateLoaned, String personLoaning)
	{
		String[] bookDetails = {bookTitle, bookAuthor, bookISBN, bookCategory, bookFictionorNon, dateLoaned, personLoaning, "no"}; // creating an array to store book information, which will eventually be stored in a text file
        // last value of array "no" is manually set as book cannot be returned instantly.
		String bookInfo = String.join(", ", bookDetails);
		
		
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("loanedbooks.txt", true))) {
            writer.write(bookInfo);
            writer.newLine();  // this just makes sure we add a new line as to not conflict with other loaned books
            System.out.println("Book sucesfully loaned!");
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
		
		// returndata returnDataSystem = new returndata();
		
		
		// List<String> lines = returnDataSystem.readAllLines("loanedbooks.txt");
		
		
		// System.out.println("Currrently loaned books: ");
		
		
        /* for (String line : lines) { // makes a for loop and will iterate until end of lines
            System.out.println(line);
        }
		
		
         */
	}
}
