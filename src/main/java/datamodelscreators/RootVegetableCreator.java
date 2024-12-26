package datamodelscreators;

import controller.Controller;
import datamodels.Car;
import datamodels.RootVegetable;
import ioData.Parser;
import reader.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import java.util.List;

import static reader.StringsConsole.LIST_EMPTY;
import static reader.ValidationConstants.ROOT_VEGETABLES_MAX_WEIGHT;
import static reader.ValidationConstants.ROOT_VEGETABLES_MIN_WEIGHT;

public class RootVegetableCreator {
    private List<String> rootType;
    private List<String> rootColor;

    public RootVegetableCreator(List<String> rootType, List<String> rootColor) {
        this.rootType = rootType;
        this.rootColor = rootColor;
    }
    public List<RootVegetable> createAndAddRootVegetables(Scanner scanner) {

        ReaderUserContext readerUser = new ReaderUserContext(new ReaderUserRootVegetables());
        List<RootVegetable> rootVegetables = new ArrayList<>();
        do {
            String[] parse = readerUser.create(rootType, rootColor, scanner);
            RootVegetable rootVegetable = new RootVegetable.RootVegetableBuilder()
                    .type(parse[0])
                    .weight(Double.parseDouble(parse[1]))
                    .color(parse[2])
                    .build();
            rootVegetables.add(rootVegetable);
            System.out.println(reader.StringsConsole.ADD_OK);
            System.out.println(reader.StringsConsole.ENTER_MORE);
        } while ((reader.ValidationUtils.checkInt(scanner.nextLine(), 0, 2)));

        System.out.println("Коллекция из " + rootVegetables.size() + " корнеплодов создана!");
        System.out.println("-----------------------------------------------------");
        return rootVegetables;
    }

    public List<RootVegetable> readerFileRootVegetables(Scanner scanner) {

        List<RootVegetable> rootVegetables;
        do {
            File file = Parser.readPath(scanner);
            String string = Parser.readFile(file, scanner);
            rootVegetables = Parser.parseFileRootVegetable(string, rootType, rootColor);
            if (rootVegetables.isEmpty()) System.out.println(LIST_EMPTY);
        } while (rootVegetables.isEmpty());
        System.out.println("Коллекция из " + rootVegetables.size() + " корнеплодов создана!");
        System.out.println("-----------------------------------------------------");
        return rootVegetables;
    }

    public static List<RootVegetable> generateRandomVegetables(int count, List<String> types, List<String> colors) {
        List<RootVegetable> rootVegetables = new ArrayList<>();
        Random r = new Random();

        for (int i = 0; i < count; i++) {
            String type = types.get(r.nextInt(types.size()));
            double weight = ROOT_VEGETABLES_MIN_WEIGHT + r.nextDouble(ROOT_VEGETABLES_MAX_WEIGHT - ROOT_VEGETABLES_MIN_WEIGHT + 1);
            weight = Math.round(weight * 100) / 100.0;
            String color = colors.get(r.nextInt(colors.size()));

            rootVegetables.add(new RootVegetable.RootVegetableBuilder()
                    .type(type)
                    .weight(weight)
                    .color(color)
                    .build());
        }
        return rootVegetables;
    }
    public static RootVegetable creatingASearchObject(List<String> rootType, List<String> rootColor, Scanner scanner) {
        ReaderUserContext readerUser = new ReaderUserContext(new ReaderUserRootVegetables());
        String[] parse = readerUser.create(rootType, rootColor, scanner);
        return new RootVegetable.RootVegetableBuilder()
                .type(parse[0])
                .weight(Double.parseDouble(parse[1]))
                .color(parse[2])
                .build();
    }
}


