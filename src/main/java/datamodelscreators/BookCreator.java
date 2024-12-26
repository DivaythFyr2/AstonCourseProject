package datamodelscreators;

import datamodels.Book;
import reader.ReaderUserBook;
import reader.ReaderUserContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static reader.ValidationConstants.BOOK_MAX_PAGE;
import static reader.ValidationConstants.BOOK_MIN_PAGE;

public class BookCreator {
    private List<String> titles;
    private List<String> authors;

    public BookCreator(List<String> titles, List<String> authors) {
        this.titles = titles;
        this.authors = authors;
    }

    public void createAndAddBooks(List<Book> database, Scanner scanner) {
        ReaderUserContext readerUser = new ReaderUserContext(new ReaderUserBook());
        do {
            String[] parse = readerUser.create(titles, authors, scanner);
            database.add(new Book.BookBuilder()
                    .title(parse[0])
                    .author(parse[1])
                    .pageCount(Integer.parseInt(parse[2]))
                    .build());
            System.out.println(reader.StringsConsole.ADD_OK);
            System.out.println(reader.StringsConsole.ENTER_MORE);
        } while ((reader.ValidationUtils.checkInt(scanner.nextLine(), 0, 2)));
        System.out.println("Коллекция из " + database.size() + " книг создана!");
        System.out.println("-----------------------------------------------------");
    }

    //Метод для заполнения из файла

    public static List<Book> generateRandomBooks(int count, List<String> titles, List<String> authors) {
        List<Book> books = new ArrayList<>();
        Random r = new Random();

        for (int i = 0; i < count; i++) {
            String title = titles.get(r.nextInt(titles.size()));
            String author = authors.get(r.nextInt(authors.size()));
            int pages = BOOK_MIN_PAGE + r.nextInt(BOOK_MAX_PAGE - BOOK_MIN_PAGE + 1);

            books.add(new Book.BookBuilder()
                    .title(title)
                    .author(author)
                    .pageCount(pages)
                    .build());
        }
        return books;
    }
    public static Book creatingASearchObject(List<String> titles, List<String> authors, Scanner scanner) {
        ReaderUserContext readerUser = new ReaderUserContext(new ReaderUserBook());
        String[] parse = readerUser.create(titles, authors, scanner);
        return new Book.BookBuilder()
                .title(parse[0])
                .author(parse[1])
                .pageCount(Integer.parseInt(parse[2]))
                .build();
    }
}


