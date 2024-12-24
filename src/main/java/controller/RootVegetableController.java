package controller;

import datamodels.RootVegetable;
import reader.ReaderUserContext;
import reader.ReaderUserRootVegetables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ЭТОТ КЛАСС ЕЩЕ НЕ ГОТОВ!!!!!!!!!!
 */
public class RootVegetableController {
    private RootVegetableController() {
    }

    public static List<RootVegetable> rootVegetableCreation(String type) {
        List<RootVegetable> rootVegetables = new ArrayList<>();
        switch (type) {
            case "1":
                //Временная коллекция для валидации String
                ArrayList<String> listType = new ArrayList<>(Arrays.asList("Помидор", "Огурец", "Перец"));
                ArrayList<String> listColor = new ArrayList<>(Arrays.asList("Красный", "Зеленый", "Желтый"));

                ReaderUserContext readerUser = new ReaderUserContext(new ReaderUserRootVegetables());
                do {
                    String[] parse = readerUser.create(listType, listColor, Controller.scanner);
                    rootVegetables.add(new RootVegetable.RootVegetableBuilder()
                            .type(parse[0])
                            .weight(parse[1])
                            .color(parse[2])
                            .build());
                    System.out.println(reader.StringsConsole.ENTER_MORE);
                } while ((reader.ValidationUtils.checkInt(Controller.scanner.nextLine(), 0, 2)));
                break;
            case "2":
                // Утилитный метод по заполнению из файла
                break;
            case "3":
                // Утилитный метод автоматического заполнения
                break;
        }
        return rootVegetables;
    }
}

