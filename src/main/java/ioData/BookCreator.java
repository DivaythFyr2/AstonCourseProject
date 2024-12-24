package ioData;

import datamodels.Book;
import reader.ValidationUtils;

public class BookCreator {

    private static final String BOOKS_EXTERNAL_FILE = "src/main/resources/External/Books.txt";

    public static boolean addBooksFromTXTFile() {
        return addBooksFromTXTFile(BOOKS_EXTERNAL_FILE);

    }

    public static boolean addBooksFromTXTFile(String fileName) {
        String[][] arr2D = IOManager.readDataFromTXTFileTo2DArray(fileName);
        for (int element = 0; element < arr2D.length; element++) {
            String title = arr2D[element][0];
            String author = arr2D[element][1];
            String pageCount = arr2D[element][2];

            addNewBook(title, author, pageCount);
        }
        return false;
    }

    public static boolean addNewBook(String title, String author, String pageCount) {
        if (ValidationUtils.checkString(title, IOManager.getBooksNamesToList())
                && ValidationUtils.checkString(author, IOManager.getBookAuthorsNames())
                && ValidationUtils.checkInt(pageCount, 1, 3000)) {

            new Book.BookBuilder()
                    .title(title)
                    .author(author)
                    .pageCount(pageCount)
                    .build();

            return true;
        } else {
            System.err.println("Fail data in [" + title + " " + author + " " + pageCount + "]");
        }
        return false;
    }

}
