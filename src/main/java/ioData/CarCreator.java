package ioData;

import datamodels.Car;
import reader.ValidationUtils;

public class CarCreator {
    private static final String CARS_EXTERNAL_FILE = "src/main/resources/External/Cars.txt";

    public static boolean addCarsFromTXTFile() {
        return addCarsFromTXTFile(CARS_EXTERNAL_FILE);
    }

    public static boolean addCarsFromTXTFile(String fileName) {
        String[][] arr2D = IOManager.readDataFromTXTFileTo2DArray(fileName);
        for (int element = 0; element < arr2D.length; element++) {
            String model = arr2D[element][0];
            String power = arr2D[element][1];
            String yearOfManufacture = arr2D[element][2];

            addNewCar(model, power, yearOfManufacture);
        }
        return false;
    }


    public static boolean addNewCar(String model, String power, String yearOfManufacture) {
        if (ValidationUtils.checkString(model, IOManager.getCarsNamesToList())
                && ValidationUtils.checkDouble(power, 0.5, 5)
                && ValidationUtils.checkInt(yearOfManufacture, 1, 3000)) {

            new Car.CarBuilder()
                    .model(model)
                    .power(power)
                    .yearOfManufacture(yearOfManufacture)
                    .build();

            return true;
        } else {
            System.err.println("Fail data in [" + model + " " + power + " " + yearOfManufacture + "]");
        }
        return false;
    }
}
