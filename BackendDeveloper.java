// --== CS400 Project One File Header ==--
// Name: Huong Thien Do
// CSL Username: tdo
// Email: tdo@wisc.edu
// Lecture #: <001 @11:00am, 002 @1:00pm, 003 @2:25pm>
// Notes to Grader: Using My HashtableMap class which implements Iterable<ValueType>.

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the search and filter functionality of the Book Mapper app using hash maps.
 *
 * @author Gary Dahl
 * @author Thien Huong Do
 */
public class BackendDeveloper implements IBookMapperBackend {

    protected HashtableMap<String, IBook> Map; //the HashtableMap object which maps ISBN to its book.
    private String authorFilter; //the author filter for a search by author.
    private int size; //the number of book in this hash map.

    /*
     * Default constructor constructs an empty insertion-ordered HashtableMap instance.
     */
    public BackendDeveloper() {
        this.Map= new HashtableMap<>();
    }

    /*
     * Adds a new book to the backend's database and is stored in
     * a hash table internally.
     * @param book the book to add
     */
    @Override
    public void addBook(IBook book) {
        if (book == null) return;
        String key = book.getISBN13();
        if (Map.put(key, book)) size++;
    }
    /**
     * Returns the number of books stored in the backend's database.
     * @return the number of books
     */
    @Override
    public int getNumberOfBooks() {
        return this.size;
    }

    /**
     * This method can be used to set a filter for the author names
     * contained in the search results. A book is only returned as
     * a result for a search by title, it is also contains the string
     * filterBy in the names of its authors.
     * @param filterBy the string that the book's author names must contain
     */
    @Override
    public void setAuthorFilter(String filterBy) {
        this.authorFilter = filterBy;
    }
    /**
     * Returns the string used as the author filter, null if no author
     * filter is currently set.
     * @return the string used as the author filter, or null if none is set
     */
    @Override
    public String getAuthorFilter() {
        return this.authorFilter;
    }

    /**
     * Resets the author filter to null (no filter).
     */
    @Override
    public void resetAuthorFilter() {
        this.authorFilter = null;
    }

    /**
     * Search through all the books in the title base and return books whose
     * title contains the string word (and that satisfies the author filter,
     * if an author filter is set).
     * @param word word that must be contained in a book's title in result set
     * @return list of books found
     */
    @Override
    public List<IBook> searchByTitleWord(String word) {
        List<IBook> result = new ArrayList<>(); //The list contains all found books.

        //Traverse through this hashtableMap
        for (int i = 0; i < Map.hashtable.length; i++) {
            if (Map.hashtable[i] == null) continue; //If the Linkedlist is null, pass it.
            for (int j = 0; j < Map.hashtable[i].size(); j++) {
                IBook book = Map.hashtable[i].get(j).getValue(); //Get the value IBook
                    //Case 1: Contains the books searched by the author filter only.
                if (word == null){
                    if (this.authorFilter == null) return null;
                    if (book.getAuthors().toLowerCase().contains(this.authorFilter.toLowerCase())) {
                        result.add(book);
                    }

                    //Case 2: Contains the books searched by this word in the title
                    // (and the author filter that was set)
                } else {
                    if (book.getTitle().toLowerCase().contains(word.toLowerCase())) {
                        if (this.authorFilter == null
                                || book.getAuthors().toLowerCase().contains(this.authorFilter.toLowerCase()))
                            // If there is no author filter set, contains the books search by this word.
                            result.add(book);
                    }
                }
            }
        }
        return result;
    }

    /**
     * Return the book uniquely identified by the ISBN, or null if ISBN is not
     * present in the dataset.
     * @param ISBN the book's ISBN number
     * @return the book identified by the ISBN, or null if ISBN not in database
     */
    @Override
    public IBook getByISBN(String ISBN) {
        if (ISBN == null) {return null;}
        if (Map.containsKey(ISBN)) {
            IBook book = Map.get(ISBN);
            return book;
        }
        return null;
    }

}