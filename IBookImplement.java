// --== CS400 Project One File Header ==--
// Name: Nattarach Larptaweepornsup
// CSL Username: nattarach
// Email: larptaweepor@wisc.edu
// Lecture #: <001 @11:00am>
// Notes to Grader: <any optional extra notes to your grader>

/**
 * This class provides methods for accessing the object of IBook which includes title, authors, and
 * ISBN13
 *
 * @author Nattarach Larptaweepornsup
 *
 */
public class IBookImplement implements IBook {
    private String title;
    private String authors;
    private String isbn13;

    /**
     * Contructor for an object IBook
     *
     * @param title   title of this book
     * @param authors authors of this book
     * @param isbn13  isbn13 of this book
     */
    public IBookImplement(String title, String authors, String isbn13) {
        this.title = title;
        this.authors = authors;
        this.isbn13 = isbn13;
    }

    /**
     * Returns the title of the book.
     *
     * @return title of the book
     */
    @Override
    public String getTitle() {
        return this.title;
    }

    /**
     * Returns a string that contains the authors of the book as a single string with different
     * authors separated by /.
     *
     * @return author names as single string
     */
    @Override
    public String getAuthors() {
        return this.authors;
    }

    /**
     * Returns the 13 digit ISBN (ISBN13) that uniquely identifies this book.
     *
     * @return ISBN number of book
     */
    @Override
    public String getISBN13() {
        return this.isbn13;
    }
}