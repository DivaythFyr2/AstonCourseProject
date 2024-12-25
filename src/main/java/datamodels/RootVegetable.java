package datamodels;

import controller.Controller;
import reader.ReaderUserRootVegetables;
import reader.ReaderUserContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RootVegetable {
    private String type;
    private double weight;
    private String color;

    public RootVegetable(RootVegetableBuilder rootVegetableBuilder) {
        this.type = rootVegetableBuilder.type;
        this.weight = rootVegetableBuilder.weight;
        this.color = rootVegetableBuilder.color;
    }

    public String getType() {
        return type;
    }

    public double getWeight() {
        return weight;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Корнеплод{" +
                "Тип='" + type + '\'' +
                ", Вес=" + weight +
                ", Цвет='" + color + '\'' +
                '}';
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
                    String[] parse =  readerUser.create(listType, listColor, Controller.scanner);
                    rootVegetables.add(new RootVegetableBuilder()
                            .type(String.valueOf(parse[0]))
                            .weight(Double.parseDouble(parse[1]))
                            .color(String.valueOf(parse[2]))
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
        } return rootVegetables;
    }

    public static class RootVegetableBuilder {
        private String type;
        private double weight;
        private String color;

        public RootVegetableBuilder type(String type) {
            this.type = type;
            return this;
        }

        public RootVegetableBuilder weight(double weight) {
            this.weight = weight;
            return this;
        }

        public RootVegetableBuilder color(String color) {
            this.color = color;
            return this;
        }

        public RootVegetable build() {
            return new RootVegetable(this);
        }
    }
}