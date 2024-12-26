package ioData;

import datamodels.Book;
import datamodels.RootVegetable;
import reader.ValidationUtils;

import java.util.ArrayList;
import java.util.List;

import static reader.ValidationConstants.*;

public class RootVegetablesCreatorUtil {
    private static final String VEGETABLES_EXTERNAL_FILE = "src/main/resources/External/RootVegetables.txt";
    private static final String VEGETABLES_MANUFACTURES_FILE = "src/main/resources/Manufactures/Root vegetable Manufactures.txt";
    private static final String VEGETABLES_EXTERNAL_OUTPUT_FILE = "src/main/resources/External/RootVegetablesOutput.txt";


    public static List<RootVegetable> addRandomsRootVegetables(int count) {
        String[][] arr2D = IOManager.readDataFromTXTFileTo2DArray(VEGETABLES_MANUFACTURES_FILE);
        List<RootVegetable> rootVegetableList = new ArrayList<>();
        if (arr2D == null) {
            System.err.println("RootVegetablesCreatorUtil: Ошибка добавления из внешнего файла по причине отсутствия входных данных.");
            return null;
        }
        for (int element = 0; element < count; element++) {
            int rnd = (int) (Math.random() * arr2D.length);
            String type = arr2D[rnd][0];
            Double weight = IOManager.parseDouble(arr2D[rnd][1]);
            String color = arr2D[rnd][2];

            rootVegetableList.add(addNewRootVegetable(type, weight, color));

        }
        return rootVegetableList;
    }

    public static List<RootVegetable> addRootVegetablesFromTXTFile() {
        return addRootVegetablesFromTXTFile(VEGETABLES_EXTERNAL_FILE);
    }

    public static List<RootVegetable> addRootVegetablesFromTXTFile(String fileName) {
        String[][] arr2D = IOManager.readDataFromTXTFileTo2DArray(fileName);
        List<RootVegetable> rootVegetableList = new ArrayList<>();
        if (arr2D == null) {
            System.err.println("RootVegetablesCreatorUtil: Ошибка добавления из внешнего файла по причине отсутствия входных данных.");
            return null;
        }
        for (int element = 0; element < arr2D.length; element++) {
            String type = arr2D[element][0];
            Double weight = IOManager.parseDouble(arr2D[element][1]);
            String color = arr2D[element][2];

            rootVegetableList.add(addNewRootVegetable(type, weight, color));
        }
        return rootVegetableList;
    }

    public static RootVegetable addNewRootVegetable(String type, Double weight, String color) {

        if (ValidationUtils.checkString(type, IOManager.getVegetablesNamesToList())
                && ValidationUtils.checkDouble(String.valueOf(weight), ROOT_VEGETABLES_MIN_WEIGHT, ROOT_VEGETABLES_MAX_WEIGHT)
                && ValidationUtils.checkString(color, IOManager.getVegetablesColorsToList())) {

            RootVegetable rootVegetable = new RootVegetable.RootVegetableBuilder()
                    .type(type)
                    .weight(weight)
                    .color(color)
                    .build();

            return rootVegetable;
        } else {
            System.err.println("RootVegetablesCreatorUtil: One of data in [" + type + " " + weight + " " + color + "] is not valid.");
        }
        return null;
    }

    public static boolean appendRootVegetableListInTXTFile(List<RootVegetable> rootVegetables, String fileName) {
        if (rootVegetables == null) {
            System.err.println("RootVegetableCreatorUtil: List<RootVegetable> is NULL.");
            return false;
        }
        for (RootVegetable rootVegetable : rootVegetables) {
            String outputData = String.valueOf(rootVegetable.getType())
                    + ";" + String.valueOf(rootVegetable.getWeight())
                    + ";" + String.valueOf(rootVegetable.getColor());

            boolean result = IOManager.appendStringToTXTFile(outputData, fileName);
        }
        return true;
    }

    public static boolean appendRootVegetableListInTXTFile(List<RootVegetable> rootVegetables) {
        return appendRootVegetableListInTXTFile(rootVegetables, VEGETABLES_EXTERNAL_OUTPUT_FILE);
    }

}