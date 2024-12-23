package utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class IOManager {
    private static final int ELEMENTS_COUNT_TOTAL = 3;

    public static String[] readDataFromTXTFileToArray(String path) {
        try {
            List<String> stringList = new ArrayList<>(Files.readAllLines(Paths.get(path), UTF_8));
            return stringList.toArray(new String[0]);
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла " + path);
            return null;
        }
    }

    public static String[][] readDataFromTXTFileTo2DArray(String path) {
        try {
            List<String> stringList = new ArrayList<>(Files.readAllLines(Paths.get(path), UTF_8));
            int stringsCount = stringList.size();
            String[][] arr2D = new String[stringsCount][ELEMENTS_COUNT_TOTAL];
            for (int s=0; s < stringsCount; s ++){
                String[] strings = stringList.get(s).split(";",ELEMENTS_COUNT_TOTAL);
                for (int element =0; element< strings.length; element++){
                    arr2D[s][element] = strings[element];
                }
            }
//            System.out.println(Arrays.deepToString(arr2D));
            return arr2D;
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла " + path);
            return null;
        }
    }

    public static String readDataFromTXTFileToString(String fileName) {
        try {
            return Files.readString(Paths.get(fileName));
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла " + fileName);
            return null;
        }

    }

    public static boolean writeDataToTXTFile(List<String> list, String fileName) {
        try {
            Files.write(Paths.get(fileName), list, StandardOpenOption.CREATE);
            return true;
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл " + fileName);
        }
        return false;
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
