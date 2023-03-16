import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * Class with main method to run the book mapper app.
 */
public class BookMapper {

    public static void main(String[] args) throws FileNotFoundException {
        // load the books from the data file
        List<IBook> books = (new IBookLoaderImplement()).loadBooks("src/books.csv");
        // instantiate the backend
        IBookMapperBackend backend = new BackendDeveloper();
        // add all the books to the backend
        for (IBook book : books) {
            backend.addBook(book);
        }

        // instantiate the isbn validator (to be used by the front end)
        IISBNValidator isbnChecker = new IISBNChecker();
        // instantiate the scanner for user input (to be used by the front end)
        Scanner userInputScanner = new Scanner(System.in);
        // instantiate the front end and pass references to the scanner, backend, and isbn validator to it
        BookMapperFrontend frontend = new BookMapperFrontend(userInputScanner, backend, isbnChecker);
        // start the input loop of the front end
        frontend.runCommandLoop();
    }

}