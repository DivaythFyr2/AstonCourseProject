package controller;

import datamodels.RootVegetable;
import datamodelscreators.RootVegetableCreator;
import filewriter.FileWriterUtil;
import iodata.PrintInfo;
import searchItems.BinarySearcher;
import sorters.ShellSort;
import sorters.CustomSort;

import static reader.ValidationUtils.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class RootVegetableController {

    static final List<String> rootType;
    static final List<String> rootColor;
    private static boolean isSort = false;

    static {
        rootType = new ArrayList<>();
        rootColor = new ArrayList<>();
        createBookCollections();
    }

    private static final RootVegetableCreator rootVegetableCreator = new RootVegetableCreator(rootType, rootColor);
    private static List<RootVegetable> database = new ArrayList<>();

    private RootVegetableController() {
    }

    public static void rootVegetableCreation(String type) {
        switch (type) {
            case "1":
                database = rootVegetableCreator.createAndAddRootVegetables(Controller.scanner);
                actions();
                break;
            case "2":
                database = rootVegetableCreator.readerFileRootVegetables(Controller.scanner);
                actions();
                break;
            case "3":
                database = rootVegetableCreator.generateRandomVegetables(Controller.scanner);
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
                    4. Сортировать по "Кастомизированной" сортировке <Весу>\s
                    5. Поиск корнеплода по параметру\s
                    6. Печать коллекции в консоль\s
                    7. Запись коллекции в файл\s
                    0. Выход из программы.\s
                    """);
            String input = Controller.scanner.nextLine();
            if (isRes0_7(input)) {
                switch (input) {
                    case "1":
                        ShellSort.shellSort(database, RootVegetable.byType());
                        System.out.println("""
                                -----------------------------------------------------\s
                                Коллекция успешно отсортирована по <Типу>\s
                                -----------------------------------------------------\s
                                Введите следующее действие:\s""");
                        isSort = false;
                        actions();
                        break;
                    case "2":
                        ShellSort.shellSort(database, RootVegetable.byColor());
                        System.out.println("""
                                -----------------------------------------------------\s
                                Коллекция успешно отсортирована по <Цвету>\s
                                -----------------------------------------------------\s
                                Введите следующее действие:\s""");
                        isSort = false;
                        actions();
                        break;
                    case "3":
                        ShellSort.shellSort(database, RootVegetable.byWeight());
                        System.out.println("""
                                -----------------------------------------------------\s
                                Коллекция успешно отсортирована по <Весу>\s
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
                                сортировкий по полю <Вес>\s
                                ----------------------------------------------------------------\s
                                Введите следующее действие:\s""");
                        isSort = false;
                        actions();
                        break;
                    case "5":
                        if (isSort) {
                            RootVegetable rootVegetable = RootVegetableCreator.creatingASearchObject(rootType, rootColor, Controller.scanner);
                            int resultIndex = BinarySearcher.binarySearch(database, rootVegetable);
                            PrintInfo.printObject(resultIndex, database);
                        } else {
                            System.out.println("""
                                    -------------------------------------------------------------------------\s
                                    Перед поиском, коллекция должна быть отсортирована по весу!\s
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
}