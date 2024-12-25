package ioData;

import datamodels.Book;
import reader.ValidationUtils;
import static reader.ValidationConstants.*;

public class BookCreatorUtil {

    private static final String BOOKS_EXTERNAL_FILE = "src/main/resources/External/Books.txt";
    private static final String BOOKS_MANUFACTURES_FILE = "src/main/resources/Manufactures/Book Manufactures.txt";

    public static boolean addRandomsBooks(int count){
        String[][] arr2D = IOManager.readDataFromTXTFileTo2DArray(BOOKS_MANUFACTURES_FILE);
        for (int element = 0; element < count; element++) {
            int rnd = (int) (Math.random() * arr2D.length);
            String title = arr2D[rnd][0];
            String author = arr2D[rnd][1];
            int pageCount = IOManager.parseInt(arr2D[rnd][2]) ;

            addNewBook(title, author, pageCount);
        }
        return false;
    }

    public static boolean addBooksFromTXTFile() {
        return addBooksFromTXTFile(BOOKS_EXTERNAL_FILE);
    }

    public static boolean addBooksFromTXTFile(String fileName) {
        String[][] arr2D = IOManager.readDataFromTXTFileTo2DArray(fileName);
        if (arr2D == null) {
            System.err.println("BookCreatorUtil: Ошибка добавления из внешнего файла по причине отсутствия входных данных.");
            return false;
        }
        for (int element = 0; element < arr2D.length; element++) {
            String title = arr2D[element][0];
            String author = arr2D[element][1];
            int pageCount = IOManager.parseInt(arr2D[element][2]) ;

            addNewBook(title, author, pageCount);
            return true;
        }
        return false;
    }

    public static boolean addNewBook(String title, String author, int pageCount) {
        if (ValidationUtils.checkString(title, IOManager.getBooksNamesToList()) &&
                ValidationUtils.checkString(author, IOManager.getBookAuthorsNames()) &&
                ValidationUtils.checkInt(String.valueOf(pageCount), BOOK_MIN_PAGE, BOOK_MAX_PAGE)) {

            new Book.BookBuilder()
                    .title(title)
                    .author(author)
                    .pageCount(pageCount)
                    .build();

            return true;
        } else {
            System.err.println("BookCreatorUtil: Fail data in [" + title + " " + author + " " + pageCount + "]");
        }
        return false;
    }

}