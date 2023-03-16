
// --== CS400 Project One File Header ==--
// Name: Nattarach Larptaweepornsup
// CSL Username: nattarach
// Email: larptaweepor@wisc.edu
// Lecture #: <001 @11:00am>
// Notes to Grader: <any optional extra notes to your grader>

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains methods to load and read all the books from the csv files and store them as
 * an object of IBook with its attribute: title, authors, ISBN13
 *
 * @author Nattarach Larptaweepornsup
 *
 */
public class IBookLoaderImplement implements IBookLoader {
    private final int titleCol = 1;
    private final int authorsCol = 2;
    private final int isbn13Col = 5;

    /**
     * This method loads the list of books from a CSV file.
     * @param filepathToCSV path to the CSV file relative to the executable
     * @return a list of book objects
     * @throws FileNotFoundException
     */
    @Override
    public List<IBook> loadBooks(String filepathToCSV) throws FileNotFoundException {
        // handle null
        if (filepathToCSV == null) {
            throw new FileNotFoundException("error reading file: please specify file path");
        }
        // handle file not found
        File file = new File(filepathToCSV);
        if (!file.exists()) {
            throw new FileNotFoundException("error reading file: file not found");
        }

        List<IBook> allBooks = new ArrayList<>();

        try (BufferedReader br =
                     new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));) {
            String line;
            // skip first line
            br.readLine();
            // continue reading each line
            while ((line = br.readLine()) != null) {
                // list of string to store book's attribute
                List<String> book = new ArrayList<String>();
                int startPos = 0;
                boolean isInQuotes = false;
                // handle case when there is comma in string
                for (int currentPos = 0; currentPos < line.length(); currentPos++) {
                    if (line.charAt(currentPos) == '\"') {
                        isInQuotes = !isInQuotes;
                        // only add when not in the quotes
                    } else if (line.charAt(currentPos) == ',' && !isInQuotes) {
                        book.add(line.substring(startPos, currentPos));
                        startPos = currentPos + 1;
                    }
                }
                // add last token
                String lastToken = line.substring(startPos);
                if (lastToken.equals(",")) {
                    book.add("");
                } else {
                    book.add(lastToken);
                }

                // only store the following columns: title, authors, and ISBN13 to obj
                String title = book.get(titleCol);
                String authors = book.get(authorsCol);
                String isbn13 = book.get(isbn13Col);

                // create new obj and add
                IBookImplement bookObj = new IBookImplement(title, authors, isbn13);
                allBooks.add(bookObj);
            }

        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return allBooks;
    }
}
