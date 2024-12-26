package controller;

import datamodels.RootVegetable;
import random.RandomRootVegetableGenerator;
import reader.ReaderUserContext;
import reader.ReaderUserRootVegetables;
import searchItems.BinarySearcher;
import sorters.ShellSort;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static controller.Controller.checkingForAutoCompletion;

public class RootVegetableController {

    static final List<String> rootType;
    static final List<String> rootColor;
    private static boolean isSort = false;

    static {
        rootType = new ArrayList<>();
        rootColor = new ArrayList<>();
        createBookCollections();
    }

    private static List<RootVegetable> database = new ArrayList<>();

    private RootVegetableController() {
    }

    public static void rootVegetableCreation(String type) {
        switch (type) {
            case "1":
                ReaderUserContext readerUser = new ReaderUserContext(new ReaderUserRootVegetables());
                do {
                    String[] parse = readerUser.create(rootType, rootColor, Controller.scanner);
                    database.add(new RootVegetable.RootVegetableBuilder()
                            .type(parse[0])
                            .weight(Double.parseDouble(parse[1]))
                            .color(parse[2])
                            .build());
                    System.out.println(reader.StringsConsole.ENTER_MORE);
                } while ((reader.ValidationUtils.checkInt(Controller.scanner.nextLine(), 0, 2)));
                System.out.println("Коллекция из " + database.size() + " корнеплодов создана!");
                System.out.println("-----------------------------------------------------");
                actions();
                break;
            case "2":
                // Утилитный метод по заполнению из файла
                break;
            case "3":
                while (true) {
                    System.out.println("Введите количество обьектов для автозаполнения от 1 до 100");
                    String input = Controller.scanner.nextLine();
                    if (checkingForAutoCompletion(input)) {
                        int value = Integer.parseInt(input);
                        database = RandomRootVegetableGenerator.generateRandomVegetables(value, rootType, rootColor);
                        System.out.println("Коллекция <Корнеплодов> создана, количество объектов - " + value);
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
                    1. Сортировать по "Типу"\s
                    2. Сортировать по "Цвету"\s
                    3. Сортировать по "Весу"\s
                    4. Поиск корнеплода по параметру\s
                    5. Печать коллекции в консоль\s
                    0. Выход из программы.\s""");
            String input = Controller.scanner.nextLine();
            if (Controller.isRes0_5(input)) {
                switch (input) {
                    case "1":
                        ShellSort.shellSort(database, byType());
                        System.out.println("""
                                -----------------------------------------------------\s
                                Коллекция успешно отсортирована по <Типу>\s
                                -----------------------------------------------------\s
                                Введите следующее действие:\s""");
                        isSort = false;
                        actions();
                        break;
                    case "2":
                        ShellSort.shellSort(database, byColor());
                        System.out.println("""
                                -----------------------------------------------------\s
                                Коллекция успешно отсортирована по <Цвету>\s
                                -----------------------------------------------------\s
                                Введите следующее действие:\s""");
                        isSort = false;
                        actions();
                        break;
                    case "3":
                        ShellSort.shellSort(database, byWeight());
                        System.out.println("""
                                -----------------------------------------------------\s
                                Коллекция успешно отсортирована по <Весу>\s
                                -----------------------------------------------------\s
                                Введите следующее действие:\s""");
                        isSort = true;
                        actions();
                        break;
                    case "4":
                        if (isSort) {
                            RootVegetable rootVegetable = creatingASearchObject();
                            int resultIndex = BinarySearcher.binarySearch(database, rootVegetable);
                            printObject(resultIndex, database);
                        } else {
                            System.out.println("Перед поиском, коллекция должна быть отсортирована по весу!");
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

    private static void printObject(Integer resultIndex, List<RootVegetable> list) {
        if (resultIndex >= 0) {
            System.out.println("-----------------------------------------------------");
            System.out.println("Искомый объект: " + list.get(resultIndex).toString());
            System.out.println("Индекс объекта в коллекции: " + ++resultIndex);
            System.out.println("-----------------------------------------------------");
        } else {
            System.out.println("Данного объекта нет в коллекции!");
        }
    }

    private static void createBookCollections() {
        try {
            List<String> lines = Files.readAllLines(Path.of("src/main/resources/Root vegetable Manufactures.txt"));
            for (String line : lines) {
                String[] parts = line.split(" - ");
                if (parts.length >= 2) {
                    rootType.add(parts[0].trim());
                    rootColor.add(parts[1].trim());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void print() {
        int counter = 1;
        System.out.println("--------------------------------------------------------------------");
        for (RootVegetable rootVegetable : database) {
            System.out.println(counter++ + ". " + rootVegetable.toString());
        }
        System.out.println("--------------------------------------------------------------------");
    }

    private static RootVegetable creatingASearchObject() {
        ReaderUserContext readerUser = new ReaderUserContext(new ReaderUserRootVegetables());
        String[] parse = readerUser.create(rootType, rootColor, Controller.scanner);
        return new RootVegetable.RootVegetableBuilder()
                .type(parse[0])
                .weight(Double.parseDouble(parse[1]))
                .color(parse[2])
                .build();
    }

    // Компаратор по Типу
    private static Comparator<RootVegetable> byType() {
        return Comparator.comparing(RootVegetable::getType);
    }

    // Компаратор по Цвету
    private static Comparator<RootVegetable> byColor() {
        return Comparator.comparing(RootVegetable::getColor);
    }

    // Компаратор по Весу
    private static Comparator<RootVegetable> byWeight() {
        return Comparator.comparingDouble(RootVegetable::getWeight);
    }
}