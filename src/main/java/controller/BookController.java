package controller;

import datamodels.Book;
import reader.ReaderUserBook;
import reader.ReaderUserContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
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
                //Временная коллекция для валидации String
                ArrayList<String> listTitle = new ArrayList<>(Arrays.asList("Война и мир", "Гамлет", "1984"));
                ArrayList<String> listAuthor = new ArrayList<>(Arrays.asList("Толстой", "Шекспир", "Оруэлл"));

                ReaderUserContext readerUser = new ReaderUserContext(new ReaderUserBook());
                do {
                    String[] parse = readerUser.create(listTitle, listAuthor, Controller.scanner);
                    database.add(new Book.BookBuilder().title(parse[0]).author(parse[1]).pageCount(parse[2]).build());
                    System.out.println(reader.StringsConsole.ENTER_MORE);
                } while ((reader.ValidationUtils.checkInt(Controller.scanner.nextLine(), 0, 2)));
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
            if (Controller.isRes(input)) {
                switch (input) {
                    case "1":
                        break;
                    case "2":
                        break;
                    case "3":
                        break;
                    case "4":
                        break;
                    case "5":
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
}

