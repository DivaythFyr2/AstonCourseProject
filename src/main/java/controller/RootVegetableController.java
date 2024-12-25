package controller;

import datamodels.RootVegetable;
import reader.ReaderUserContext;
import reader.ReaderUserRootVegetables;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class RootVegetableController {

    static final List<String> rootType;
    static final List<String> color;

    static {
        rootType = new ArrayList<>();
        color = new ArrayList<>();
        createBookCollections();
    }

    private static final List<RootVegetable> database = new ArrayList<>();

    private RootVegetableController() {
    }

    public static void rootVegetableCreation(String type) {
        switch (type) {
            case "1":
                ReaderUserContext readerUser = new ReaderUserContext(new ReaderUserRootVegetables());
                do {
//                    String[] parse = readerUser.create(rootType, color, Controller.scanner);
//                    database.add(new RootVegetable.RootVegetableBuilder()
//                            .type(parse[0])
//                            .weight(parse[1])
//                            .color(parse[2])
//                            .build());
//                    System.out.println(reader.StringsConsole.ENTER_MORE);
                } while ((reader.ValidationUtils.checkInt(Controller.scanner.nextLine(), 0, 2)));
                System.out.println("Коллекция из " + database.size() + " корнеплодов создана!");
                System.out.println("-----------------------------------------------------");
                actions();
                break;
            case "2":
                // Утилитный метод по заполнению из файла
                break;
            case "3":
                // Утилитный метод автоматического заполнения
                break;
        }
    }

    private static void actions() {
        while (true) {
            System.out.println("""
                    Доступные действия над коллекцией:\s
                    1. Сортировать по "Типу"\s
                    2. Сортировать по "Цвету"\s
                    3. Сортировать по "Весу"\s
                    4. Поиск корнеплода по параметру\s
                    5. Печать коллекции в консоль\s
                    0. Выход из программы.\s""");
            String input = Controller.scanner.nextLine();
            if (CarController.isRes(input)) {
                switch (input) {
                    case "1":
                        break;
                    case "2":
                        break;
                    case "3":
                        break;
                    case "4":
                        break;
                    case "5":
                        print();
                        actions();
                        break;
                    case "0":
                        Controller.scanner.close();
                        System.exit(0);
                }
            } else {
                System.out.println("Ввод не корректен, введите одну из следующих цифр:");
            }
        }
    }

    private static void createBookCollections() {
        try {
            List<String> lines = Files.readAllLines(Path.of("src/main/resources/Root vegetable Manufactures.txt"));
            for (String line : lines) {
                String[] parts = line.split(" - ");
                if (parts.length >= 2) {
                    rootType.add(parts[0].trim());
                    color.add(parts[1].trim());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void print() {
        int counter = 1;
        System.out.println("--------------------------------------------------------------------");
        for (RootVegetable rootVegetable : database) {
            System.out.println(counter++ + ". " + rootVegetable.toString());
        }
        System.out.println("--------------------------------------------------------------------");
    }
}