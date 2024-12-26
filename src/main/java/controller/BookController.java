package controller;

import datamodels.Book;
import random.RandomBookGenerator;
import reader.ReaderUserBook;
import reader.ReaderUserContext;
import searchItems.BinarySearcher;
import sorters.ShellSort;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static controller.Controller.checkingForAutoCompletion;

public class BookController {
    private static final List<String> titles;
    private static final List<String> authors;
    private static boolean isSort = false;

    static {
        titles = new ArrayList<>();
        authors = new ArrayList<>();
        createBookCollections();
    }


    private static List<Book> database = new ArrayList<>();

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
                            .pageCount(Integer.parseInt(parse[2]))
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
                while (true) {
                    System.out.println("Введите количество обьектов для автозаполнения от 1 до 100");
                    String input = Controller.scanner.nextLine();
                    if (checkingForAutoCompletion(input)) {
                        int value = Integer.parseInt(input);
                        database = RandomBookGenerator.generateRandomBooks(value, titles, authors);
                        System.out.println("Коллекция <Книг> создана, количество объектов - " + value);
                        System.out.println("-----------------------------------------------------");
                        break;
                    } else {
                        System.out.println("Введено не корректное значение!");
                    }
                }
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
                    4. Поиск книги по параметрам\s
                    5. Печать коллекции в консоль\s
                    0. Выход из программы.\s""");
            String input = Controller.scanner.nextLine();
            if (Controller.isRes0_5(input)) {
                switch (input) {
                    case "1":
                        ShellSort.shellSort(database, byTittle());
                        System.out.println("""
                                -----------------------------------------------------\s
                                Коллекция успешно отсортирована по <Названиям>\s
                                -----------------------------------------------------\s
                                Введите следующее действие:\s""");
                        isSort = false;
                        actions();
                        break;
                    case "2":
                        ShellSort.shellSort(database, byAuthor());
                        System.out.println("""
                                -----------------------------------------------------\s
                                Коллекция успешно отсортирована по <Автору>\s
                                -----------------------------------------------------\s
                                Введите следующее действие:\s""");
                        actions();
                        isSort = false;
                        break;
                    case "3":
                        ShellSort.shellSort(database, byPageCount());
                        System.out.println("""
                                -----------------------------------------------------\s
                                Коллекция успешно отсортирована по <Количеству страниц>\s
                                -----------------------------------------------------\s
                                Введите следующее действие:\s""");
                        isSort = true;
                        actions();
                        break;
                    case "4":
                        if (isSort) {
                            Book book = creatingASearchObject();
                            int resultIndex = BinarySearcher.binarySearch(database, book);
                            printObject(resultIndex, database);
                        } else {
                            System.out.println("Перед поиском, коллекция должна быть отсортирована по количеству стриниц!");
                        }
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

    private static void printObject(Integer resultIndex, List<Book> list) {
        if (resultIndex >= 0) {
            System.out.println("-----------------------------------------------------");
            System.out.println("Искомый объект: " + list.get(resultIndex).toString());
            System.out.println("Индекс объекта в коллекции: " + ++resultIndex);
            System.out.println("-----------------------------------------------------");
        } else {
            System.out.println("Данного объекта нет в коллекции!");
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

    private static Book creatingASearchObject() {
        ReaderUserContext readerUser = new ReaderUserContext(new ReaderUserBook());
        String[] parse = readerUser.create(titles, authors, Controller.scanner);
        return new Book.BookBuilder()
                .title(parse[0])
                .author(parse[1])
                .pageCount(Integer.parseInt(parse[2]))
                .build();
    }

    // Компаратор по названию
    private static Comparator<Book> byTittle() {
        return Comparator.comparing(Book::getTitle);
    }

    // Компаратор по автору
    private static Comparator<Book> byAuthor() {
        return Comparator.comparing(Book::getAuthor);
    }

    // Компаратор по количеству страниц
    private static Comparator<Book> byPageCount() {
        return Comparator.comparingInt(Book::getPageCount);
    }
}

