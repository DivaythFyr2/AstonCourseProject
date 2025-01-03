package controller;

import datamodels.Book;
import datamodelscreators.BookCreator;
import filewriter.FileWriterUtil;
import iodata.PrintInfo;
import searchItems.BinarySearcher;
import sorters.ShellSort;

import static reader.ValidationUtils.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import sorters.CustomSort;


public class BookController {
    private static final List<String> titles;
    private static final List<String> authors;
    private static boolean isSort = false;

    static {
        titles = new ArrayList<>();
        authors = new ArrayList<>();
        createBookCollections();
    }

    private static final BookCreator bookCreator = new BookCreator(titles, authors);
    private static List<Book> database = new ArrayList<>();

    private BookController() {
    }

    static void bookCreation(String type) {
        switch (type) {
            case "1":
                database = bookCreator.createAndAddBooks(Controller.scanner);
                actions();
                break;
            case "2":
                database = bookCreator.readerFileBooks(Controller.scanner);
                actions();
                break;
            case "3":
                database = bookCreator.generateRandomBooks(Controller.scanner);
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
                    4. Сортировать по "Кастомизированной" сортировке <Количество страниц>\s
                    5. Поиск книги по параметрам\s
                    6. Печать коллекции в консоль\s
                    7. Запись коллекции в файл\s
                    0. Выход из программы.\s
                    """);
            String input = Controller.scanner.nextLine();
            if (isRes0_7(input)) {
                switch (input) {
                    case "1":
                        ShellSort.shellSort(database, Book.byTittle());
                        System.out.println("""
                                -----------------------------------------------------\s
                                Коллекция успешно отсортирована по <Названиям>\s
                                -----------------------------------------------------\s
                                Введите следующее действие:\s""");
                        isSort = false;
                        actions();
                        break;
                    case "2":
                        ShellSort.shellSort(database, Book.byAuthor());
                        System.out.println("""
                                -----------------------------------------------------\s
                                Коллекция успешно отсортирована по <Автору>\s
                                -----------------------------------------------------\s
                                Введите следующее действие:\s""");
                        actions();
                        isSort = false;
                        break;
                    case "3":
                        ShellSort.shellSort(database, Book.byPageCount());
                        System.out.println("""
                                -----------------------------------------------------\s
                                Коллекция успешно отсортирована по <Количеству страниц>\s
                                -----------------------------------------------------\s
                                Введите следующее действие:\s""");
                        isSort = true;
                        actions();
                        break;
                    case "4":
                        CustomSort.sort(database);
                        System.out.println("""
                                ----------------------------------------------------------------\s
                                Коллекция успешно отсортирована <Кастомизированной>
                                сортировкий по полю <Количество страниц>\s
                                ----------------------------------------------------------------\s
                                Введите следующее действие:\s""");
                        isSort = false;
                        actions();
                        break;
                    case "5":
                        if (isSort) {
                            Book book = BookCreator.creatingASearchObject(titles, authors, Controller.scanner);
                            int resultIndex = BinarySearcher.binarySearch(database, book);
                            PrintInfo.printObject(resultIndex, database);
                        } else {
                            System.out.println("""
                                    -------------------------------------------------------------------------\s
                                    Перед поиском, коллекция должна быть отсортирована по количеству стриниц!\s
                                    -------------------------------------------------------------------------""");
                        }
                        break;
                    case "6":
                        PrintInfo.print(database);
                        actions();
                        break;
                    case "7":
                        System.out.println("Введите путь для сохранения коллекции в файл:");
                        String path = Controller.scanner.nextLine();
                        FileWriterUtil.writeToFile(path, database);
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

    private static void createBookCollections() {
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