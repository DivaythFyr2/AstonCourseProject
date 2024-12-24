package ioData;

import datamodels.RootVegetable;
import reader.ValidationUtils;

public class RootVegetablesCreator {
    private static final String VEGETABLES_EXTERNAL_FILE = "src/main/resources/External/RootVegetables.txt";

    public static boolean addRootVegetablesFromTXTFile() {
        return addRootVegetablesFromTXTFile(VEGETABLES_EXTERNAL_FILE);
    }

    public static boolean addRootVegetablesFromTXTFile(String fileName) {
        String[][] arr2D = IOManager. readDataFromTXTFileTo2DArray(fileName);
        for (int element = 0; element < arr2D.length; element++) {
            String type = arr2D[element][0];
            String weight = arr2D[element][1];
            String color = arr2D[element][2];

            addNewRootVegetable(type, weight, color);

        }
        return false;
    }

    public static boolean addNewRootVegetable(String type, String weight, String color) {

        if (ValidationUtils.checkString(type, IOManager.getVegetablesNamesToList())
                && ValidationUtils.checkDouble(weight, 0.1, 1)
                && ValidationUtils.checkString(color, IOManager.getVegetablesColorsToList())) {

            new RootVegetable.RootVegetableBuilder()
                    .type(type)
                    .weight(weight)
                    .color(color)
                    .build();

            return true;
        } else {
            System.err.println("Fail data in [" + type + " " + weight + " " + color + "]");
        }
        return false;
    }
}
