package ioData;

import datamodels.Book;
import datamodels.Car;
import datamodels.RootVegetable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static reader.ValidationConstants.*;
import static reader.ValidationUtils.*;
import static reader.StringsConsole.*;

public class Parser {

    public static File readPath(Scanner scanner) {

        System.out.println(NAME_FILE);
        File file;
        while (true) {
            String path = scanner.nextLine();
            file = new File(path);
            if (!file.isAbsolute() || !file.exists())
                System.out.println(FILE_NOT_FOUND);
            else break;
        } return file;
    }

    public static String readFile(File file, Scanner scanner) {

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            sb.append(input).append(";");
        } return sb.toString();
    }

    public static List<Book> parseFileBook(String param, List<String> listTitle, List<String> listAuthor) {

        List<Book> books = new ArrayList<>();
        String[] enter = param.split(SPLIT_ENTER);

        for (String s : enter) {
            String join = String.join("", s);
            String[] parse = join.split(SPLIT_PARSE);
            if ((checkString(parse[0], listTitle))
                    && (checkString(parse[1], listAuthor))
                    && (checkInt(parse[2], BOOK_MIN_PAGE, BOOK_MAX_PAGE))) {
                books.add(new Book.BookBuilder()
                        .title(parse[0])
                        .author((parse[1]))
                        .pageCount(Integer.parseInt(parse[2]))
                        .build());
            }
        } return books;
    }

    public static List<Car> parseFileCar(String param, List<String> listModel) {

        List<Car> cars = new ArrayList<>();
        String[] enter = param.split(SPLIT_ENTER);

        for (String s : enter) {
            String join = String.join("", s);
            String[] parse = join.split(SPLIT_PARSE);
            if ((checkString(parse[0], listModel))
                    && (checkInt(parse[1], CAR_MIN_POWER, CAR_MAX_POWER))
                    && (checkInt(parse[2], CAR_MIN_YEAR, CAR_MAX_YEAR))) {
                cars.add(new Car.CarBuilder()
                        .model(parse[0])
                        .power(Integer.parseInt(parse[1]))
                        .yearOfManufacture(Integer.parseInt(parse[2]))
                        .build());
            }
        } return cars;
    }

    public static List<RootVegetable> parseFileRootVegetable(String param, List<String> listType, List<String> listColor) {

        List<RootVegetable> rootVegetables = new ArrayList<>();
        String[] enter = param.split(SPLIT_ENTER);

        for (String s : enter) {
            String join = String.join("", s);
            String[] parse = join.split(SPLIT_PARSE);
            if ((checkString(parse[0], listType))
                    && (checkDouble(parse[1], ROOT_VEGETABLES_MIN_WEIGHT, ROOT_VEGETABLES_MAX_WEIGHT))
                    && (checkString(parse[2], listColor))) {
                rootVegetables.add(new RootVegetable.RootVegetableBuilder()
                        .type(parse[0])
                        .weight(Double.parseDouble(parse[1]))
                        .color(parse[2])
                        .build());
            }
        } return rootVegetables;
    }
}