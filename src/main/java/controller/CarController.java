package controller;

import datamodels.Car;
import random.RandomCarGenerator;
import reader.ReaderUserCar;
import reader.ReaderUserContext;
import searchItems.BinarySearcher;
import sorters.ShellSort;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
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

    static void creatingASearchObject(String type) {
        switch (type) {
            case "1":
                ReaderUserContext readerUser = new ReaderUserContext(new ReaderUserCar());
                do {
                    String[] parse = readerUser.create(validation, null, Controller.scanner);
                    database.add(new Car.CarBuilder()
                            .model(parse[0])
                            .power(Integer.parseInt(parse[1]))
                            .yearOfManufacture(Integer.parseInt(parse[2]))
                            .build());
                    System.out.println(reader.StringsConsole.ENTER_MORE);
                } while ((reader.ValidationUtils.checkInt(Controller.scanner.nextLine(), 0, 2)));
                System.out.println("Коллекция из " + database.size() + " автомобилей создана!");
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
                        database = RandomCarGenerator.generateRandomCars(value, validation);
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
                        ShellSort.shellSort(database, byModel());
                        System.out.println("""
                                -----------------------------------------------------\s
                                Коллекция успешно отсортирована по <Марке>\s
                                -----------------------------------------------------\s
                                Введите следующее действие:\s""");
                        isSort = true;
                        actions();
                        break;
                    case "2":
                        ShellSort.shellSort(database, byPower());
                        System.out.println("""
                                -----------------------------------------------------\s
                                Коллекция умпешно отсортирована по <Мощности>\s
                                -----------------------------------------------------\s
                                Введите следующее действие:\s""");
                        isSort = true;
                        actions();
                        break;
                    case "3":
                        ShellSort.shellSort(database, byYearOfManufacture());
                        System.out.println("""
                                -----------------------------------------------------\s
                                Коллекция умпешно отсортирована по <Году производства>\s
                                -----------------------------------------------------\s
                                Введите следующее действие:\s""");
                        isSort = true;
                        actions();
                        break;
                    case "4":
                        if (isSort) {
                            Car car = creatingASearchObject();
                            // ТУТ БУДЕТ ПРАВКА, ВЫЗОВ ПОИСКА БЕЗ КОМПОРАТОРА!
                            // НА ДАННЫЙ МОМЕНТ ИЩЕТ В ТОМ СЛУЧАЕ ЕСЛИ ДО ЭТОГО НЕ БЫЛА ПРОИЗВЕДЕНА СОРТИРОВКА КОТОРАЯ ОТЛИЧАЕТСЯ ОТ ПЕРЕДАННОЙ!
                            int result = BinarySearcher.binarySearch(database, car, byPower());
                            if (result >= 0) {
                                System.out.println("Искомый объект: " + database.get(result).toString());
                                System.out.println("Индекс объекта в коллекции: " + ++result);
                                System.out.println("-----------------------------------------------------");
                            } else {
                                System.out.println("Данного объекта нет в коллекции!");
                            }
                        } else {
                            System.out.println("Перед поиском, коллекция должна быть отсортирована!");
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

    private static void print() {
        int counter = 1;
        System.out.println("--------------------------------------------------------------------");
        for (Car car : database) {
            System.out.println(counter++ + ". " + car.toString());
        }
        System.out.println("--------------------------------------------------------------------");
    }

    private static Car creatingASearchObject() {
        ReaderUserContext readerUser = new ReaderUserContext(new ReaderUserCar());
        String[] parse = readerUser.create(validation, null, Controller.scanner);
        return new Car.CarBuilder()
                .model(parse[0])
                .power(Integer.parseInt(parse[1]))
                .yearOfManufacture(Integer.parseInt(parse[2]))
                .build();
    }

    // Компаратор по модели
    private static Comparator<Car> byModel() {
        return Comparator.comparing(Car::getModel);
    }

    // Компаратор по мощности
    private static Comparator<Car> byPower() {
        return Comparator.comparingInt(Car::getPower);
    }

    // Компаратор по году изготовления
    private static Comparator<Car> byYearOfManufacture() {
        return Comparator.comparingInt(Car::getYearOfManufacture);
    }
}