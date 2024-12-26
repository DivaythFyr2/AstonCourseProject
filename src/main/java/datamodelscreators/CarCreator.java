package datamodelscreators;

import datamodels.Car;
import reader.ReaderUserCar;
import reader.ReaderUserContext;
import ioData.Parser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static reader.StringsConsole.LIST_EMPTY;
import static reader.ValidationConstants.*;

public class CarCreator {
    private List<String> validation;

    public CarCreator(List<String> validation) {
        this.validation = validation;
    }

    public List<Car> createAndAddCars(Scanner scanner) {

        ReaderUserContext readerUser = new ReaderUserContext(new ReaderUserCar());
        List<Car> cars = new ArrayList<>();
        do {
            String[] parse = readerUser.create(validation, null, scanner);
            Car car = new Car.CarBuilder()
                    .model(parse[0])
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
            String string = Parser.readFile(file, scanner);
            cars = Parser.parseFileCar(string, validation);
            if (cars.isEmpty()) System.out.println(LIST_EMPTY);
        } while (cars.isEmpty());
        return cars;
    }

    public static List<Car> generateRandomCars(int count, List<String> models) {
        List<Car> cars = new ArrayList<>();
        Random r = new Random();

        for (int i = 0; i < count; i++) {
            String model = models.get(r.nextInt(models.size()));
            int power = CAR_MIN_POWER + r.nextInt(CAR_MAX_POWER - CAR_MIN_POWER + 1);
            int year = CAR_MIN_YEAR + r.nextInt(CAR_MAX_YEAR - CAR_MIN_YEAR + 1);

            cars.add(new Car.CarBuilder()
                    .model(model)
                    .power(power)
                    .yearOfManufacture(year)
                    .build());
        }
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

