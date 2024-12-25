package ioData;

import datamodels.Car;
import reader.ValidationUtils;

import java.util.ArrayList;
import java.util.List;

import static reader.ValidationConstants.*;

public class CarCreatorUtil {
    private static final String CARS_EXTERNAL_FILE = "src/main/resources/External/Cars.txt";
    private static final String CARS_MANUFACTURES_FILE = "src/main/resources/Manufactures/Car Manufacturers.txt";

    public static List<Car> addRandomsCars(int count) {
        String[][] arr2D = IOManager.readDataFromTXTFileTo2DArray(CARS_MANUFACTURES_FILE);
        if (arr2D == null) {
            System.err.println("CarCreatorUtil: Ошибка добавления из внешнего файла по причине отсутствия входных данных.");
            return null;
        }
        List<Car> carList = new ArrayList<>();
        for (int element = 0; element < count; element++) {
            int rnd = (int) (Math.random() * arr2D.length);
            String model = arr2D[rnd][0];
            int power = IOManager.parseInt(arr2D[rnd][1]) ;
            int yearOfManufacture = Integer.parseInt(arr2D[rnd][2]) ;

            carList.add(addNewCar(model, power, yearOfManufacture)) ;
        }
        return carList;
    }

    public static List<Car> addCarsFromTXTFile() {
        return addCarsFromTXTFile(CARS_EXTERNAL_FILE);
    }

    public static List<Car> addCarsFromTXTFile(String fileName) {
        String[][] arr2D = IOManager.readDataFromTXTFileTo2DArray(fileName);
        List<Car> carList = new ArrayList<>();
        if (arr2D == null) {
            System.err.println("CarCreatorUtil: Ошибка добавления из внешнего файла по причине отсутствия входных данных.");
            return null;
        }
        for (int element = 0; element < arr2D.length; element++) {
            String model = arr2D[element][0];
            int power = IOManager.parseInt(arr2D[element][1]) ;
            int yearOfManufacture = Integer.parseInt(arr2D[element][2]) ;

            carList.add(addNewCar(model, power, yearOfManufacture));
        }
        return carList;
    }


    public static Car addNewCar(String model, int power, int yearOfManufacture) {
        if (ValidationUtils.checkString(model, IOManager.getCarsNamesToList())
                && ValidationUtils.checkDouble(String.valueOf(power), CAR_MIN_POWER, CAR_MAX_POWER)
                && ValidationUtils.checkInt(String.valueOf(yearOfManufacture), CAR_MIN_YEAR, CAR_MAX_YEAR)) {

            Car car = new Car.CarBuilder()
                    .model(model)
                    .power(power)
                    .yearOfManufacture(yearOfManufacture)
                    .build();

            return car;
        } else {
            System.err.println("CarCreatorUtil: One of data in [" + model + " " + power + " " + yearOfManufacture + "] is not valid.");
        }
        return null;
    }
}