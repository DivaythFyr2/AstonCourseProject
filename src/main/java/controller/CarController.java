package controller;

import datamodels.Car;
import datamodelscreators.CarCreator;
import searchItems.BinarySearcher;
import sorters.ShellSort;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static controller.Controller.checkingForAutoCompletion;
import static controller.Controller.isRes0_5;


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

    private static List<Car> database = new ArrayList<>();

    private CarController() {
    }

    static void carCreation(String type) {
        switch (type) {
            case "1":
                database = new CarCreator(validation).createAndAddCars(Controller.scanner);
                actions();
                break;
            case "2":
                database = new CarCreator(validation).readerFileCars(Controller.scanner);
                actions();
                break;
            case "3":
                while (true) {
                    System.out.println("Введите количество обьектов для автозаполнения от 1 до 100");
                    String input = Controller.scanner.nextLine();
                    if (checkingForAutoCompletion(input)) {
                        int value = Integer.parseInt(input);
                        database = CarCreator.generateRandomCars(value, validation);
                        System.out.println("Коллекция <Автомобилей> создана, количество объектов - " + value);
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
                    1. Сортировать по "Марке"\s
                    2. Сортировать по "Мощности"\s
                    3. Сортировать по "Году производства"\s
                    4. Поиск машины по параметрам\s
                    5. Печать коллекции в консоль\s
                    0. Выход из программы.\s""");
            String input = Controller.scanner.nextLine();
            if (isRes0_5(input)) {
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
                                Коллекция умпешно отсортирована по <Мощности>\s
                                -----------------------------------------------------\s
                                Введите следующее действие:\s""");
                        isSort = true;
                        actions();
                        break;
                    case "3":
                        ShellSort.shellSort(database, Car.byYearOfManufacture());
                        System.out.println("""
                                -----------------------------------------------------\s
                                Коллекция умпешно отсортирована по <Году производства>\s
                                -----------------------------------------------------\s
                                Введите следующее действие:\s""");
                        isSort = false;
                        actions();
                        break;
                    case "4":
                        if (isSort) {
                            Car car = CarCreator.creatingASearchObject(validation,Controller.scanner);
                            int resultIndex = BinarySearcher.binarySearch(database, car);
                            printObject(resultIndex, database);
                        } else {
                            System.out.println("Перед поиском, коллекция должна быть отсортирована по мощности!");
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

    private static void printObject(Integer resultIndex, List<Car> list) {
        if (resultIndex >= 0) {
            System.out.println("-----------------------------------------------------");
            System.out.println("Искомый объект: " + list.get(resultIndex).toString());
            System.out.println("Индекс объекта в коллекции: " + ++resultIndex);
            System.out.println("-----------------------------------------------------");
        } else {
            System.out.println("Данного объекта нет в коллекции!");
        }
    }

    private static void print() {
        int counter = 1;
        System.out.println("--------------------------------------------------------------------");
        for (Car car : database) {
            System.out.println(counter++ + ". " + car.toString());
        }
        System.out.println("--------------------------------------------------------------------");
    }
}