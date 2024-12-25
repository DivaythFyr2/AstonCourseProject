package ioData;

import datamodels.Car;
import reader.ValidationUtils;
import static reader.ValidationConstants.*;

public class CarCreatorUtil {
    private static final String CARS_EXTERNAL_FILE = "src/main/resources/External/Cars.txt";
    private static final String CARS_MANUFACTURES_FILE = "src/main/resources/Manufactures/Car Manufacturers.txt";

    public static boolean addRandomsCars(int count) {
        String[][] arr2D = IOManager.readDataFromTXTFileTo2DArray(CARS_MANUFACTURES_FILE);
        for (int element = 0; element < count; element++) {
            int rnd = (int) (Math.random() * arr2D.length);
            String model = arr2D[rnd][0];
            int power = IOManager.parseInt(arr2D[rnd][1]) ;
            int yearOfManufacture = Integer.parseInt(arr2D[rnd][2]) ;

            addNewCar(model, power, yearOfManufacture);
        }
        return false;
    }

    public static boolean addCarsFromTXTFile() {
        return addCarsFromTXTFile(CARS_EXTERNAL_FILE);
    }

    public static boolean addCarsFromTXTFile(String fileName) {
        String[][] arr2D = IOManager.readDataFromTXTFileTo2DArray(fileName);
        if (arr2D == null) {
            System.err.println("CarCreatorUtil: Ошибка добавления из внешнего файла по причине отсутствия входных данных.");
            return false;
        }
        for (int element = 0; element < arr2D.length; element++) {
            String model = arr2D[element][0];
            int power = IOManager.parseInt(arr2D[element][1]) ;
            int yearOfManufacture = Integer.parseInt(arr2D[element][2]) ;

            addNewCar(model, power, yearOfManufacture);
            return true;
        }
        return false;
    }


    public static boolean addNewCar(String model, int power, int yearOfManufacture) {
        if (ValidationUtils.checkString(model, IOManager.getCarsNamesToList())
                && ValidationUtils.checkDouble(String.valueOf(power), CAR_MIN_POWER, CAR_MAX_POWER)
                && ValidationUtils.checkInt(String.valueOf(yearOfManufacture), CAR_MIN_YEAR, CAR_MAX_YEAR)) {

            new Car.CarBuilder()
                    .model(model)
                    .power(power)
                    .yearOfManufacture(yearOfManufacture)
                    .build();

            return true;
        } else {
            System.err.println("CarCreatorUtil: Fail data in [" + model + " " + power + " " + yearOfManufacture + "]");
        }
        return false;
    }
}