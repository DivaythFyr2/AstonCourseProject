package controller;

import datamodels.Car;
import reader.ReaderUserCar;
import reader.ReaderUserContext;
import sorters.ShellSort;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class CarController {
    private static final List<String> validation;

    static {
        try (Stream<String> lines = Files.lines(Path.of("src/main/resources/Car Manufacturers.txt"))) {
            validation = lines.toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final List<Car> database = new ArrayList<>();

    private CarController() {
    }

    static void carCreation(String type) {
        switch (type) {
            case "1":
                ReaderUserContext readerUser = new ReaderUserContext(new ReaderUserCar());
                do {
//                    String[] parse = readerUser.create(validation, null, Controller.scanner);
//                    database.add(new Car.CarBuilder()
//                            .model(parse[0])
//                            .power(parse[1])
//                            .yearOfManufacture(parse[2])
//                            .build());
//                    System.out.println(reader.StringsConsole.ENTER_MORE);
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
//                database = Утилитный метод автоматического заполнения
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
                    4. Поиск машины по параметру\s
                    5. Печать коллекции в консоль\s
                    0. Выход из программы.\s""");
            String input = Controller.scanner.nextLine();
            if (isRes(input)) {
                switch (input) {
                    case "1":
//                        ShellSort<Car> carShellSort = new ShellSort<>();
//                        carShellSort.sort(database);
                        System.out.println("""
                                -----------------------------------------------------\s
                                Коллекция умпешно отсортирована по <Марке> автомобиля.\s
                                -----------------------------------------------------\s
                                Введите следующее действие:\s""");
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

    private static void print() {
        int counter = 1;
        System.out.println("--------------------------------------------------------------------");
        for (Car car : database) {
            System.out.println(counter++ + ". " + car.toString());
        }
        System.out.println("--------------------------------------------------------------------");
    }

    static boolean isRes(String input) {
        return input.matches("[0-5]");
    }
}