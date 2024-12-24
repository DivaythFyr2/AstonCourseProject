package controller;

import datamodels.Book;
import reader.ReaderUserBook;
import reader.ReaderUserContext;
import sorters.ShellSort;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class BookController {
    private static final List<String> titles;
    private static final List<String> authors;

    static {
        titles = new ArrayList<>();
        authors = new ArrayList<>();
        createBookCollections();
    }


    private static final List<Book> database = new ArrayList<>();

    private BookController() {
    }

    static void bookCreation(String type) {
        switch (type) {
            case "1":
                ReaderUserContext readerUser = new ReaderUserContext(new ReaderUserBook());
                do {
                    String[] parse = readerUser.create(titles, authors, Controller.scanner);
                    database.add(new Book.BookBuilder()
                            .title(parse[0])
                            .author(parse[1])
                            .pageCount(parse[2])
                            .build());
                    System.out.println(reader.StringsConsole.ENTER_MORE);
                } while ((reader.ValidationUtils.checkInt(Controller.scanner.nextLine(), 0, 2)));
                System.out.println("Коллекция из " + database.size() + " книг создана!");
                System.out.println("-----------------------------------------------------");
                actions();
                break;
            case "2":
//                database = Утилитный метод по заполнению из файла
                actions();
                break;
            case "3":
//                database = Утилитный метод автоматического заполнения
                actions();
                break;
        }
    }

    private static void actions() {
        while (true) {
            System.out.println("""
                    Доступные действия над коллекцией:\s
                    1. Сортировать по "Названию"\s
                    2. Сортировать по "Автору"\s
                    3. Сортировать по "Количеству страниц"\s
                    4. Поиск книги по параметру\s
                    5. Печать коллекции в консоль\s
                    0. Выход из программы.\s""");
            String input = Controller.scanner.nextLine();
            if (CarController.isRes(input)) {
                switch (input) {
                    case "1":
                        ShellSort<Book> shellSort = new ShellSort<>();
                        shellSort.sort(database);
                        actions();
                        break;
                    case "2":
                        break;
                    case "3":
                        break;
                    case "4":
                        break;
                    case "5":
                        print();
                        actions();
                        break;
                    case "0":
                        Controller.scanner.close();
                        System.exit(0);
                }
            } else {
                System.out.println("Ввод не корректен, введите одну из следующих цифр:");
            }
        }
    }

    static void createBookCollections() {
        try {
            List<String> lines = Files.readAllLines(Path.of("src/main/resources/Book Manufactures.txt"));
            for (String line : lines) {
                String[] parts = line.split(" - ");
                if (parts.length >= 2) {
                    titles.add(parts[0].trim());
                    authors.add(parts[1].trim());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void print() {
        int counter = 1;
        System.out.println("--------------------------------------------------------------------");
        for (Book book : database) {
            System.out.println(counter++ + ". " + book.toString());
        }
        System.out.println("--------------------------------------------------------------------");
    }
}