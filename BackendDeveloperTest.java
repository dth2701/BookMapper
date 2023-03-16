// --== CS400 Project One File Header ==--
// Name: Huong Thien Do
// CSL Username: tdo
// Email: tdo@wisc.edu
// Lecture #: <001 @11:00am, 002 @1:00pm, 003 @2:25pm>
// Notes to Grader: <any optional extra notes to your grader>

/**
 * This class contains 5 test methods to test 5 methods in the class BackendDeveloper.
 *
 * @author Gary Dahl
 * @author Thien Huong Do
 */
import java.util.Iterator;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class BackendDeveloperTest {

    /**
     * Checks whether HashtableMap’s behaviors towards the author filter work as expected.
     *
     * @return true if method functionality is verified, false otherwise.
     */
    public static boolean test1() {
        BackendDeveloper test = new BackendDeveloper();

        //Check author filter is null in the hash table map.
        if (test.getAuthorFilter() != null) return false;

        //Check author filter returns as expected in the hash table map.
        test.setAuthorFilter("Bill");
        if (!test.getAuthorFilter().equals("Bill")) return false;

        //Check reset author filter
        test.resetAuthorFilter();
        if (test.getAuthorFilter() != null) return false;
        return true;
    }

    /**
     * Checks whether addBook() and getNumberOfBooks() work as expected.
     *
     * @return true if method functionality is verified, false otherwise.
     */
    public static boolean test2() {
        BackendDeveloper test2 = new BackendDeveloper();
        IBookImplement book1 = new IBookImplement("Notes from a Small Island", "Bill Bryson", "9780380727506");
        test2.addBook(book1);
        //Add the first book in this hash map.
        if (test2 == null) return false;
        if (test2.getNumberOfBooks() != 1) return false;

        //Add many books in this hash map.
        IBookImplement book2 = new IBookImplement("Harry Potter and the Chamber of Secrets (Harry Potter  #2)",
                "J.K. Rowling", "9780439554893");
        IBookImplement book3 = new IBookImplement("The Changeling", "Zilpha Keatley Snyder", "9780595321803");
        test2.addBook(book2);
        test2.addBook(book3);
        if (test2 == null) return false;
        if (test2.getNumberOfBooks() != 3) return false;

        //Add the duplicate book in this hashmap.
        IBookImplement book4 = new IBookImplement("The Changeling", "Zilpha Keatley Snyder", "9780595321803");
        test2.addBook(book4);
        if (test2 == null) return false;
        if (test2.getNumberOfBooks() != 3) return false;
        return true;
    }

    /**
     * Checks whether searchByTitleWord() work as expected.
     *
     * @return true if method functionality is verified, false otherwise.
     */
    public static boolean test3() {
        BackendDeveloper test3 = new BackendDeveloper();
        IBookImplement book1 = new IBookImplement("Harry Potter and the Half-Blood Prince (Harry Potter  #6)",
                "J.K. Rowling/Mary GrandPré", "9780439785969");
        IBookImplement book2 = new IBookImplement("Harry Potter and the Order of the Phoenix (Harry Potter  #5)",
                "J.K. Rowling/Mary GrandPré", "9780439358071");
        IBookImplement book3 = new IBookImplement("Notes from a Small Island", "Bill Bryson", "9780380727506");
        IBookImplement book4 = new IBookImplement("Harry Potter and the Chamber of Secrets (Harry Potter  #2)",
                "J.K. Rowling", "9780439554893");
        IBookImplement book5 = new IBookImplement("Into the new World", "Amy", "It is a number");

        test3.addBook(book1);
        test3.addBook(book2);
        test3.addBook(book3);
        test3.addBook(book4);
        test3.addBook(book5);

        //Case 1: Check all the books the valid author filter and valid contained word.
        test3.setAuthorFilter("ROW");
        List<IBook> result1 = test3.searchByTitleWord("Potter");

        if (result1.size() != 3) return false;
        //Check the title of the last book in this list.
        if (!result1.get(2).getAuthors().equals("J.K. Rowling/Mary GrandPré"))
            return false;

        //Case 2: Check all the books the invalid author filter and valid contained word.
        test3.resetAuthorFilter();
        test3.setAuthorFilter("Rowhihi?");
        List<IBook> result2 = test3.searchByTitleWord("Harry");

        //Check the title of the last book in this list.
        if (result2.size() != 0) return false;

        //Case 3: Check all the books the valid author filter and invalid contained word.
        test3.resetAuthorFilter();
        test3.setAuthorFilter("MAry");
        List<IBook> result3 = test3.searchByTitleWord("notHarry");
        if (result2.size() != 0) return false;
        return true;
    }

    /**
     * Checks whether getByISBN() work as expected.
     *
     * @return true if method functionality is verified, false otherwise.
     */
    public static boolean test4() {
        BackendDeveloper test4 = new BackendDeveloper();
        IBookImplement book1 = new IBookImplement("Harry Potter and the Half-Blood Prince (Harry Potter  #6)",
                "J.K. Rowling/Mary GrandPré", "9780439785969");
        IBookImplement book2 = new IBookImplement("Harry Potter and the Order of the Phoenix (Harry Potter  #5)",
                "J.K. Rowling/Mary GrandPré", "9780439358071");
        IBookImplement book3 = new IBookImplement("Notes from a Small Island", "Bill Bryson", "9780380727506");
        IBookImplement book4 = new IBookImplement("Harry Potter and the Chamber of Secrets (Harry Potter  #2)",
                "J.K. Rowling", "9780439554893");
        IBookImplement book5 = new IBookImplement("Into the new World", "Amy", "It is a number");

        test4.addBook(book1);
        test4.addBook(book2);
        test4.addBook(book3);
        test4.addBook(book4);
        test4.addBook(book5);

        //Case 1: Check the valid ISBN
        if (!test4.getByISBN("9780439358071")
                .getTitle().equals("Harry Potter and the Order of the Phoenix (Harry Potter  #5)"))
            return false;

        //Case 2: Check the invalid ISBN
        if (test4.getByISBN("123999123hi") != null) return false;

        return true;
    }

    /**
     * Checks whether getByISBN() with the book not added work as expected.
     *
     * @return true if method functionality is verified, false otherwise.
     */
    public static boolean test5() {
        BackendDeveloper test5 = new BackendDeveloper();
        IBookImplement book1 = new IBookImplement("Harry Potter and the Half-Blood Prince (Harry Potter  #6)",
                "J.K. Rowling/Mary GrandPré", "9780439785969");
        IBookImplement book2 = new IBookImplement("Harry Potter and the Order of the Phoenix (Harry Potter  #5)",
                "J.K. Rowling/Mary GrandPré", "9780439358071");
        IBookImplement book3 = new IBookImplement("Notes from a Small Island", "Bill Bryson", "9780380727506");
        IBookImplement book4 = new IBookImplement("Harry Potter and the Chamber of Secrets (Harry Potter  #2)",
                "J.K. Rowling", "9780439554893");
        IBookImplement book5 = new IBookImplement("Into the new World", "Amy", "It is a number");
        test5.addBook(book1);
        test5.addBook(book2);
        test5.addBook(book4);
        test5.addBook(book5);

        try {
            //Check the ISBN but the book is not in the list
            if (!test5.getByISBN("9780439358071").getAuthors().equals("J.K. Rowling/Mary GrandPré"))
                return false;
            //Check if this hashmap contains the book.
            if (test5.Map.containsKey(book3.getISBN13())) return false;
        } catch (Exception NullPointerException) {
            return true;
        }
        return true;
    }

    /**
     * Checks the Backend Developer code running with Algorithm Engineer code work as expected.
     *
     * @return true if method functionality is verified, false otherwise.
     */
    public static boolean testIntegration1() {
        BackendDeveloper backendDeveloperTest = new BackendDeveloper();
        IISBNChecker isbnChecker = new IISBNChecker();
        Iterator<IBook> it = backendDeveloperTest.Map.iterator();

        IBookImplement book1 = new IBookImplement("Harry Potter and the Half-Blood Prince (Harry Potter  #6)",
                "J.K. Rowling/Mary GrandPré", "9780439785969");
        IBookImplement book2 = new IBookImplement("Harry Potter and the Order of the Phoenix (Harry Potter  #5)",
                "J.K. Rowling/Mary GrandPré", "9780439358071");
        IBookImplement book3 = new IBookImplement("Notes from a Small Island", "Bill Bryson", "9780380727506");
        IBookImplement book4 = new IBookImplement("Harry Potter and the Chamber of Secrets (Harry Potter  #2)",
                "J.K. Rowling", "9780439554893");
        IBookImplement book5 = new IBookImplement("Into the new World", "Amy", "It is a number");

        backendDeveloperTest.Map.put(book1.getISBN13(), book1);
        backendDeveloperTest.Map.put(book2.getISBN13(), book2);
        backendDeveloperTest.Map.put(book3.getISBN13(), book3);
        backendDeveloperTest.Map.put(book4.getISBN13(), book4);
        backendDeveloperTest.Map.put(book5.getISBN13(), book5);
        if (!it.next().equals(book1)) return false;
        if (!it.hasNext() != false) return false;

        if (isbnChecker.validate("9780439785969") == false) return false;

            return true;
    }

    /**
     * Checks the BackendDeveloper, DataWrangler and FrontendDeveloper code work as expected.
     * @return true if method functionality is verified, false otherwise.
     */
    public static boolean testIntegration2() {

        try {
            BackendDeveloper backendDeveloperTest = new BackendDeveloper();
            IISBNChecker isbnChecker = new IISBNChecker();
            TextUITester tester = new TextUITester("4\n");
            Scanner userInputScanner = new Scanner(System.in);
            BookMapperFrontend frontendDeveloperTest = new BookMapperFrontend(userInputScanner,
                    backendDeveloperTest,isbnChecker);

            frontendDeveloperTest.runCommandLoop();
            String output = tester.checkOutput();
            if (!output.contains("Goodbye!")) return false;
        } catch (Exception e) {
            return true;
        }
        return true;
    }

    /**
     * Checks the BackendDeveloper code when running with the DataWrangler code work as expected.
     * @return true if method functionality is verified, false otherwise.
     */
    public static boolean testPartner1() {
        BackendDeveloper backendDeveloperTest1 = new BackendDeveloper();
        IBookLoaderImplement dataWranglerTest1 = new IBookLoaderImplement();

        try {
            List<IBook> allBooks2 = dataWranglerTest1.loadBooks("src/books.csv");
            for(int i = 0; i < allBooks2.size(); i++) {
                backendDeveloperTest1.addBook(allBooks2.get(i));
            }
            //Check getTitle()
            if (!allBooks2.get(26).getTitle().equals("The Lord of the Rings: Complete Visual Companion"))
                return false;
            //Check getAuthors()
            if (!allBooks2.get(26).getAuthors().equals("Jude Fisher")) return false;

            //Check getISBN13()
            if (!allBooks2.get(26).getISBN13().equals("9780618510825")) return false;
        } catch (Exception FileNotFoundException) {
            return true;
        }

        return true;
    }

    /**
     * Checks the BackendDeveloper code when running with the DataWrangler code work as expected.
     * @return true if method functionality is verified, false otherwise.
     */
    public static boolean testPartner2() {
        BackendDeveloper backendDeveloperTest2 = new BackendDeveloper();
        IBookLoaderImplement dataWranglerTest2 = new IBookLoaderImplement();

        try {
            List<IBook> allBooks2 = dataWranglerTest2.loadBooks("src/books.csv");
            for (int i = 0; i < allBooks2.size(); i++) {
                backendDeveloperTest2.addBook(allBooks2.get(i));
            }
            //Check author filter is null in the hash table map.
            if (backendDeveloperTest2.getAuthorFilter() != null) return false;

            //Check author filter returns as expected in the hash table map.
            backendDeveloperTest2.setAuthorFilter("Rowling");
            if (!backendDeveloperTest2.getAuthorFilter().equals("Rowling")) return false;

            //Check the searchByTitleWord() method
            List<IBook> result = backendDeveloperTest2.searchByTitleWord("Harry");
            if (result.size() != 20) return false;
            if(!result.get(5).getISBN13().equals("9780439785969")) return false;

            //Check the getByISBN() method
            if (!backendDeveloperTest2.getByISBN("9780060749910").getAuthors().equals("Edward P. Jones"))
                return false;
        } catch (Exception FileNotFoundException) {
            return true;
        }
        return true;
    }
    /**
     * The main method only runs 5 tests.
     * @param args unused
     */
    public static void main(String args[]) {
        System.out.println("BackendDeveloper Individual Test 1: " + test1());
        System.out.println("BackendDeveloper Individual Test 2: " + test2());
        System.out.println("BackendDeveloper Individual Test 3: " + test3());
        System.out.println("BackendDeveloper Individual Test 4: " + test4());
        System.out.println("BackendDeveloper Individual Test 5: " + test5());
        System.out.println("BackendDeveloper Integration Test 1: " + testIntegration1());
        System.out.println("BackendDeveloper Integration Test 2: "+ testIntegration2());
        System.out.println("BackendDeveloper Partner (DataWrangler) Test 1: " +testPartner1());
        System.out.println("BackendDeveloper Partner (DataWrangler) Test 2: " +testPartner2());

    }

}
