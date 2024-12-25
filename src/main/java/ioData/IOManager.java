package ioData;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class IOManager {
    private static final int ELEMENTS_COUNT_TOTAL = 3; // количество элементов массива извлекаемых из строки
    private static final String CARS_MANUFACTURES_FILE = "src/main/resources/Manufactures/Car Manufacturers.txt";
    private static final String BOOKS_MANUFACTURES_FILE = "src/main/resources/Manufactures/Book Manufactures.txt";
    private static final String VEGETABLES_MANUFACTURES_FILE = "src/main/resources/Manufactures/Root vegetable Manufactures.txt";


    public static List<String> getCarsNamesToList() {
        return getValuesFromTXTFileToList(CARS_MANUFACTURES_FILE, 0);
    }

    public static List<String> getBooksNamesToList() {
        return getValuesFromTXTFileToList(BOOKS_MANUFACTURES_FILE, 0);
    }

    public static List<String> getBookAuthorsNames() {
        return getValuesFromTXTFileToList(BOOKS_MANUFACTURES_FILE, 1);
    }

    public static List<String> getVegetablesNamesToList() {
        return getValuesFromTXTFileToList(VEGETABLES_MANUFACTURES_FILE, 0);
    }

    public static List<String> getVegetablesColorsToList() {
        return getValuesFromTXTFileToList(VEGETABLES_MANUFACTURES_FILE, 2);
    }

    public static List<String> getValuesFromTXTFileToList(String fileName, int requiredValueNumber) {
        List<String> result = new ArrayList<>();
        String[][] arr2D = readDataFromTXTFileTo2DArray(fileName);
        if (requiredValueNumber > 0 || requiredValueNumber < ELEMENTS_COUNT_TOTAL) {
            for (int s = 0; s < arr2D.length; s++) {
                if (!(arr2D == null)) {
                    result.add(arr2D[s][requiredValueNumber]);
                }
            }
        } else {
            System.err.println("IOManager: Required value number " + requiredValueNumber + "in not correct.");
        }
        return result;
    }

    public static String[][] readDataFromTXTFileTo2DArray(String path) {
        try {
            List<String> stringList = new ArrayList<>(Files.readAllLines(Paths.get(path)));
            int stringsCount = stringList.size();
            String[][] arr2D = new String[stringsCount][ELEMENTS_COUNT_TOTAL];
            for (int s = 0; s < stringsCount; s++) {
                String[] strings = stringList.get(s).split(";", ELEMENTS_COUNT_TOTAL);
                for (int element = 0; element < strings.length; element++) {
                    arr2D[s][element] = strings[element];
                }
            }
//            System.out.println(Arrays.deepToString(arr2D));
            return arr2D;
        } catch (IOException e) {
            System.err.println("IOManager: Ошибка чтения файла " + path);
            return null;
        }
    }

    public static Double parseDouble(String str) {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            System.err.println("IOManager: Ошибка перевода из String(" + str + ") в Double. Возвращено значение null.");
            return null;
        }
    }

    public static Integer parseInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            System.err.println("IOManager: Ошибка перевода из String(" + str + ") в Integer. Возвращено значение null.");
            return null;
        }
    }

    public static boolean appendDataToTXTFile(String fileName, String text) {
        try {
            new File(fileName).createNewFile();
            text += "\n";
            Files.write(Paths.get(fileName), text.getBytes(), StandardOpenOption.APPEND);
            return true;
        } catch (IOException e) {
            System.err.println("IOManager: Ошибка добавления текста в файл " + text);
        }
        return false;
    }

    public static boolean appendDataToTXTFile(String fileName, List<String> list) {
        try {
            new File(fileName).createNewFile();
            Files.write(Paths.get(fileName), list, StandardOpenOption.APPEND);
            return true;
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл " + fileName);
        }
        return false;

    }


    public static String readDataFromTXTFileToString(String fileName) {
        try {
            return Files.readString(Paths.get(fileName));
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла " + fileName);
            return null;
        }
    }

    public static String[] readDataFromTXTFileToArray(String path) {
        try {
            List<String> stringList = new ArrayList<>(Files.readAllLines(Paths.get(path), UTF_8));
            return stringList.toArray(new String[0]);
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла " + path);
            return null;
        }
    }


    public static boolean writeDataToTXTFile(String text, String fileName) {
        try {
            new File(fileName).createNewFile();
            Files.writeString(Paths.get(fileName), text);
            return true;
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл " + fileName);
            e.printStackTrace();
        }
        return false;
    }


}
