package ioData;

import datamodels.RootVegetable;
import reader.ValidationUtils;
import static reader.ValidationConstants.*;

public class RootVegetablesCreatorUtil {
    private static final String VEGETABLES_EXTERNAL_FILE = "src/main/resources/External/RootVegetables.txt";
    private static final String VEGETABLES_MANUFACTURES_FILE = "src/main/resources/Manufactures/Root vegetable Manufactures.txt";



    public static boolean addRandomsRootVegetables(int count){
        String[][] arr2D = IOManager. readDataFromTXTFileTo2DArray(VEGETABLES_MANUFACTURES_FILE);
        for (int element = 0; element < count; element++) {
            int rnd = (int) (Math.random() * arr2D.length);
            String type = arr2D[rnd][0];
            Double weight = IOManager.parseDouble(arr2D[rnd][1]) ;
            String color = arr2D[rnd][2];

            addNewRootVegetable(type, weight, color);

        }
        return false;
    }

    public static boolean addRootVegetablesFromTXTFile() {
        return addRootVegetablesFromTXTFile(VEGETABLES_EXTERNAL_FILE);
    }

    public static boolean addRootVegetablesFromTXTFile(String fileName) {
        String[][] arr2D = IOManager. readDataFromTXTFileTo2DArray(fileName);
        if (arr2D == null) {
            System.err.println("RootVegetablesCreatorUtil: Ошибка добавления из внешнего файла по причине отсутствия входных данных.");
            return false;
        }
        for (int element = 0; element < arr2D.length; element++) {
            String type = arr2D[element][0];
            Double weight = IOManager.parseDouble(arr2D[element][1]) ;
            String color = arr2D[element][2];

            addNewRootVegetable(type, weight, color);
            return true;
        }
        return false;
    }

    public static boolean addNewRootVegetable(String type, Double weight, String color) {

        if (ValidationUtils.checkString(type, IOManager.getVegetablesNamesToList())
                && ValidationUtils.checkDouble(String.valueOf(weight), ROOT_VEGETABLES_MIN_WEIGHT, ROOT_VEGETABLES_MAX_WEIGHT)
                && ValidationUtils.checkString(color, IOManager.getVegetablesColorsToList())) {

            new RootVegetable.RootVegetableBuilder()
                    .type(type)
                    .weight(weight)
                    .color(color)
                    .build();

            return true;
        } else {
            System.err.println("RootVegetablesCreatorUtil: Fail data in [" + type + " " + weight + " " + color + "]");
        }
        return false;
    }
}