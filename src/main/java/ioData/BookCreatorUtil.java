package ioData;

import datamodels.Book;
import datamodels.Car;
import reader.ValidationUtils;

import java.util.ArrayList;
import java.util.List;

import static reader.ValidationConstants.*;

public class BookCreatorUtil {

    private static final String BOOKS_EXTERNAL_FILE = "src/main/resources/External/Books.txt";
    private static final String BOOKS_MANUFACTURES_FILE = "src/main/resources/Manufactures/Book Manufactures.txt";
    private static final String  BOOKS_EXTERNAL_OUTPUT_FILE= "src/main/resources/External/BooksOut.txt";

    public static List<Book>  addRandomsBooks(int count){
        String[][] arr2D = IOManager.readDataFromTXTFileTo2DArray(BOOKS_MANUFACTURES_FILE);
        List<Book> bookList = new ArrayList<>();
        if (arr2D == null) {
            System.err.println("BookCreatorUtil: Ошибка добавления из внешнего файла по причине отсутствия входных данных.");
            return null;
        }
        for (int element = 0; element < count; element++) {
            int rnd = (int) (Math.random() * arr2D.length);
            String title = arr2D[rnd][0];
            String author = arr2D[rnd][1];
            int pageCount = IOManager.parseInt(arr2D[rnd][2]) ;

            bookList.add(addNewBook(title, author, pageCount));
        }
        return bookList;
    }

    public static List<Book> addBooksFromTXTFile() {
        return addBooksFromTXTFile(BOOKS_EXTERNAL_FILE);
    }

    public static List<Book> addBooksFromTXTFile(String fileName) {
        String[][] arr2D = IOManager.readDataFromTXTFileTo2DArray(fileName);
        List<Book> bookList = new ArrayList<>();
        if (arr2D == null) {
            System.err.println("BookCreatorUtil: Ошибка добавления из внешнего файла по причине отсутствия входных данных.");
            return null;
        }
        for (int element = 0; element < arr2D.length; element++) {
            String title = arr2D[element][0];
            String author = arr2D[element][1];
            int pageCount = IOManager.parseInt(arr2D[element][2]) ;

            bookList.add(addNewBook(title, author, pageCount));
        }
        return bookList;
    }

    public static Book addNewBook(String title, String author, int pageCount) {
        if (ValidationUtils.checkString(title, IOManager.getBooksNamesToList()) &&
                ValidationUtils.checkString(author, IOManager.getBookAuthorsNames()) &&
                ValidationUtils.checkInt(String.valueOf(pageCount), BOOK_MIN_PAGE, BOOK_MAX_PAGE)) {

           Book book = new Book.BookBuilder()
                    .title(title)
                    .author(author)
                    .pageCount(pageCount)
                    .build();

            return book;
        } else {
            System.err.println("BookCreatorUtil: One of data in [" + title + " " + author + " " + pageCount + "] is not valid.");
        }
        return null;
    }

    public static boolean appendBookListInTXTFile(List<Book> books, String fileName) {
        if (books == null) {
            System.err.println("BookCreatorUtil: List<Book> is NULL.");
            return false;
        }
        for (Book book : books) {
            String outputData = String.valueOf(book.getTitle())
                    + ";" + String.valueOf(book.getAuthor())
                    + ";" + String.valueOf(book.getPageCount());

            boolean result = IOManager.appendStringToTXTFile(outputData, fileName);
        }
        return true;
    }

    public static boolean appendBookListInTXTFile(List<Book> books) {
        return appendBookListInTXTFile(books, BOOKS_EXTERNAL_OUTPUT_FILE);
    }

}