package datamodelscreators;

import datamodels.Car;
import ioData.Parser;
import reader.ReaderUserCar;
import reader.ReaderUserContext;
import static reader.ValidationUtils.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static reader.StringsConsole.LIST_EMPTY;
import static reader.ValidationConstants.*;

public class CarCreator {
    private final List<String> validation;

    public CarCreator(List<String> validation) {
        this.validation = validation;
    }

    public List<Car> createAndAddCars(Scanner scanner) {
        ReaderUserContext readerUser = new ReaderUserContext(new ReaderUserCar());
        List<Car> cars = new ArrayList<>();
        do {
            String[] parse = readerUser.create(validation, null, scanner);
            Car car = new Car.CarBuilder()
                    .model(parse[0].toUpperCase())
                    .power(Integer.parseInt(parse[1]))
                    .yearOfManufacture(Integer.parseInt(parse[2]))
                    .build();
            cars.add(car);
            System.out.println(reader.StringsConsole.ADD_OK);
            System.out.println(reader.StringsConsole.ENTER_MORE);
        } while ((reader.ValidationUtils.checkInt(scanner.nextLine(), 0, 2)));

        System.out.println("Коллекция из " + cars.size() + " автомобилей создана!");
        System.out.println("-----------------------------------------------------");

        return cars;
    }
    public List<Car> readerFileCars(Scanner scanner) {

        List<Car> cars;
        do {
            File file = Parser.readPath(scanner);
            String string = Parser.readFile(file, scanner).toUpperCase();
            cars = Parser.parseFileCar(string, validation);
            if (cars.isEmpty()) System.out.println(LIST_EMPTY);
        } while (cars.isEmpty());
        return cars;
    }

    public List<Car> generateRandomCars(Scanner scanner) {
        int value;
        while (true) {
            System.out.println("Введите количество объектов для автозаполнения от 1 до 100");
            String input = scanner.nextLine();
            if(checkingForAutoCompletion(input)) {
                value = Integer.parseInt(input);
                break;
            } else {
                System.out.println("Введено не корректное значение!");
            }
        }
        List<Car> cars = new ArrayList<>();
        Random r = new Random();

        for (int i = 0; i < value; i++) {
            String model = validation.get(r.nextInt(validation.size()));
            int power = CAR_MIN_POWER + r.nextInt(CAR_MAX_POWER - CAR_MIN_POWER + 1);
            int year = CAR_MIN_YEAR + r.nextInt(CAR_MAX_YEAR - CAR_MIN_YEAR + 1);

            cars.add(new Car.CarBuilder()
                    .model(model)
                    .power(power)
                    .yearOfManufacture(year)
                    .build());
        }
        System.out.println("Коллекция <Автомобилей> создана, количество объектов - " + value);
        System.out.println("-----------------------------------------------------");
        return cars;
    }

    public static Car creatingASearchObject(List<String> validation, Scanner scanner) {
        ReaderUserContext readerUser = new ReaderUserContext(new ReaderUserCar());
        String[] parse = readerUser.create(validation, null, scanner);
        return new Car.CarBuilder()
                .model(parse[0])
                .power(Integer.parseInt(parse[1]))
                .yearOfManufacture(Integer.parseInt(parse[2]))
                .build();
    }
}

