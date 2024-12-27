package controller;

import datamodels.Car;
import datamodelscreators.CarCreator;
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
import java.util.stream.Stream;

import sorters.CustomSort;


public class CarController {
    private static final List<String> validation;
    private static boolean isSort = false;

    static {
        try (Stream<String> lines = Files.lines(Path.of("src/main/resources/Car Manufacturers.txt"))) {
            validation = lines.toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final CarCreator carCreator = new CarCreator(validation);
    private static List<Car> database = new ArrayList<>();

    private CarController() {
    }

    static void carCreation(String type) {
        switch (type) {
            case "1":
                database = carCreator.createAndAddCars(Controller.scanner);
                actions();
                break;
            case "2":
                database = carCreator.readerFileCars(Controller.scanner);
                actions();
                break;
            case "3":
                database = carCreator.generateRandomCars(Controller.scanner);
                actions();
                break;
        }
    }

    private static void actions() {
        while (true) {
            System.out.println("""
                    Доступные действия над коллекцией:\s
                    1. Сортировать по "Марке"\s
                    2. Сортировать по "Мощности"\s
                    3. Сортировать по "Году производства"\s
                    4. Сортировать по "Кастомизированной" сортировке <Мощность>\s
                    5. Поиск машины по параметрам\s
                    6. Печать коллекции в консоль\s
                    7. Запись коллекции в файл\s
                    0. Выход из программы.\s
                    """);
            String input = Controller.scanner.nextLine();
            if (isRes0_7(input)) {
                switch (input) {
                    case "1":
                        ShellSort.shellSort(database, Car.byModel());
                        System.out.println("""
                                -----------------------------------------------------\s
                                Коллекция успешно отсортирована по <Марке>\s
                                -----------------------------------------------------\s
                                Введите следующее действие:\s""");
                        isSort = false;
                        actions();
                        break;
                    case "2":
                        ShellSort.shellSort(database, Car.byPower());
                        System.out.println("""
                                -----------------------------------------------------\s
                                Коллекция успешно отсортирована по <Мощности>\s
                                -----------------------------------------------------\s
                                Введите следующее действие:\s""");
                        isSort = true;
                        actions();
                        break;
                    case "3":
                        ShellSort.shellSort(database, Car.byYearOfManufacture());
                        System.out.println("""
                                -----------------------------------------------------\s
                                Коллекция успешно отсортирована по <Году производства>\s
                                -----------------------------------------------------\s
                                Введите следующее действие:\s""");
                        isSort = false;
                        actions();
                        break;
                    case "4":
                        CustomSort.sort(database);
                        System.out.println("""
                                ----------------------------------------------------------------\s
                                Коллекция успешно отсортирована <Кастомизированной>
                                сортировкий по полю <Мощность>\s
                                ----------------------------------------------------------------\s
                                Введите следующее действие:\s""");
                        isSort = false;
                        actions();
                        break;
                    case "5":
                        if (isSort) {
                            Car car = CarCreator.creatingASearchObject(validation, Controller.scanner);
                            int resultIndex = BinarySearcher.binarySearch(database, car);
                            PrintInfo.printObject(resultIndex, database);
                        } else {
                            System.out.println("""
                                    -------------------------------------------------------------------------\s
                                    Перед поиском, коллекция должна быть отсортирована по мощности!\s
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
}