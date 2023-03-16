// --== CS400 Project One File Header ==--
// Name: Nisitha trevin de silva
// CSL Username: nisitha
// Email: ntdesilva@wisc.edu
// Lecture #: <001 @11:00am, 002 @1:00pm, 003 @2:25pm>
// Notes to Grader:

import java.util.List;
import java.util.Scanner;

public class BookMapperFrontend implements IBookMapperFrontend {
    Scanner input = new Scanner(System.in);
    IBookMapperBackend backend;
    IISBNValidator isbnValidator;

    /**
     * The constructor that the implementation this interface will provide. It takes
     * the Scanner that will read user input as a parameter as well as the backend
     * and the ISBNvalidator.
     */
    public BookMapperFrontend(Scanner userInputScanner, IBookMapperBackend backend, IISBNValidator isbnValidator) {
        this.backend = backend;
        this.isbnValidator = isbnValidator;
        this.input = userInputScanner;
    }

    /**
     * This method starts the command loop for the frontend, and will terminate when
     * the user exists the app.
     */
    @Override
    public void runCommandLoop() {
        int response = 0;
        System.out.println("Welcome to the Book Mapper Application!\n" + "x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x\n");

        while (response < 4) {
            displayMainMenu();
            response = input.nextInt();

            if (response == 1) {
                isbnLookup();

                // isbnLookup();
            }

            else if (response == 2) {
                titleSearch();

            }

            else if (response == 3) {
                setAuthor();

            }

        }
        System.out.println("Goodbye!");

    }

    /**
     * prints command options to System.out
     */
    @Override
    public void displayMainMenu() {
        System.out.println(
                "You are in the Main Menu:\n" + "          1) Lookup ISBN\n" + "          2) Search by Title Word\n"
                        + "          3) Set Author Name Filter\n" + "          4) Exit Application\n");
    }

    /**
     * displays a list of books
     */
    @Override
    public void displayBooks(List<IBook> books) {
        int count = books.size();
        int i = 0;
        int number = 0;
        while (i < count) {

            System.out.println((number + 1) + ". \"" + books.get(i).getTitle() + "\" by " + books.get(i).getAuthors()
                    + ", ISBN: " + books.get(i).getISBN13() + "\n");

            i++;
            number++;

        }

    }

    /**
     * reads word from System.in, displays results
     */

    @Override
    public void isbnLookup() {
        System.out.print("You are in the ISBN Menu:\n" + "                 Enter ISBN to look up: ");

        String userIsbn = input.next();

        if ((isbnValidator.validate(userIsbn)) != false) {
            IBook books = backend.getByISBN(userIsbn);
            // System.out.print(books.getTitle());
            List<IBook> library = List.of(books);
            displayBooks(library);

        } else {
            return;
        }

    }

    /**
     * reads author name from System.in, displays results
     */
    @Override
    public void titleSearch() {
        Scanner scans = new Scanner(System.in);
        System.out.print("You are in the Search for Title Word Menu:\n"
                + "          Enter a word to search for in book titles (empty for all books): ");
        String title = scans.nextLine();

        List<IBook> library = backend.searchByTitleWord(title);

        if (backend.getAuthorFilter() != null) {

            System.out.println("Matches (author filter: " + (backend.getAuthorFilter()) + ") " + library.size()
                    + " of " + backend.getNumberOfBooks());
            displayBooks(library);
            System.out.println("Matches (author filter: " + (backend.getAuthorFilter()) + ") " + library.size()
                    + " of " + backend.getNumberOfBooks());
        } else {
            System.out.println("Matches (any author) " + library.size() + " of " + backend.getNumberOfBooks());
            displayBooks(library);

        }

    }

    public void setAuthor() {
        // String author = "";
        Scanner scan = new Scanner(System.in);
        String author;
        if (backend.getAuthorFilter() == null) {
            author = "none";
        } else {
            author = backend.getAuthorFilter();
        }
        System.out.println("You are in the Set Author Filter Menu:\n"
                + "                   Author name must currently contain: " + author);
        System.out.println("Enter a new string for author names to contain (empty for any): ");
        author = scan.nextLine();
        backend.setAuthorFilter(author);

    }
}