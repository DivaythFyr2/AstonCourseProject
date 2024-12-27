package datamodelscreators;

import datamodels.Book;
import iodata.Parser;
import reader.ReaderUserBook;
import reader.ReaderUserContext;
import static reader.ValidationUtils.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import static reader.StringsConsole.*;


import static reader.ValidationConstants.*;

public class BookCreator {
    private final List<String> titles;
    private final List<String> authors;

    public BookCreator(List<String> titles, List<String> authors) {
        this.titles = titles;
        this.authors = authors;
    }

    public List<Book> createAndAddBooks(Scanner scanner) {
        ReaderUserContext readerUser = new ReaderUserContext(new ReaderUserBook());
        List<Book> books = new ArrayList<>();

        do {
            String[] parse = readerUser.create(titles, authors, scanner);
            books.add(new Book.BookBuilder()
                    .title(parse[0].toUpperCase())
                    .author(parse[1].toUpperCase())
                    .pageCount(Integer.parseInt(parse[2]))
                    .build());
            System.out.println(reader.StringsConsole.ADD_OK);
            System.out.println(reader.StringsConsole.ENTER_MORE);
        } while ((reader.ValidationUtils.checkInt(scanner.nextLine(), 0, 2)));
        System.out.println("Коллекция из " + books.size() + " книг создана!");
        System.out.println("-----------------------------------------------------");

        return books;
    }
    public List<Book> readerFileBooks(Scanner scanner) {

        List<Book> books;
        do {
            File file = Parser.readPath(scanner);
            String string = Parser.readFile(file, scanner).toUpperCase();
            books = Parser.parseFileBook(string, titles, authors);
            if (books.isEmpty()) System.out.println(LIST_EMPTY);
        } while (books.isEmpty());
        return books;
    }

    public List<Book> generateRandomBooks(Scanner scanner) {
        int value;
        while (true) {
            System.out.println("Введите количество объектов для автозаполнения от 1 до 100");
            String input = scanner.nextLine();
            if(checkingForAutoCompletion(input)) {
                value = Integer.parseInt(input);
                break;
            } else {
                System.out.println("Введено не корректное значение!");
            }
        }
        List<Book> books = new ArrayList<>();
        Random r = new Random();

        for (int i = 0; i < value; i++) {
            String title = titles.get(r.nextInt(titles.size()));
            String author = authors.get(r.nextInt(authors.size()));
            int pages = BOOK_MIN_PAGE + r.nextInt(BOOK_MAX_PAGE - BOOK_MIN_PAGE + 1);

            books.add(new Book.BookBuilder()
                    .title(title)
                    .author(author)
                    .pageCount(pages)
                    .build());
        }
        System.out.println("Коллекция <Книг> создана, количество объектов - " + value);
        System.out.println("-----------------------------------------------------");
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


