package datamodels;

import controller.Controller;
import reader.ReaderUserRootVegetables;
import reader.ReaderUserContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RootVegetable implements Comparable<RootVegetable> {
    private String type;
    private String weight;
    private String color;

    public RootVegetable(RootVegetableBuilder rootVegetableBuilder) {
        this.type = rootVegetableBuilder.type;
        this.weight = rootVegetableBuilder.weight;
        this.color = rootVegetableBuilder.color;
    }

    public String getType() {
        return type;
    }

    public String getWeight() {
        return weight;
    }

    public String getColor() {
        return color;
    }

    @Override
    public int compareTo(RootVegetable o) {
        int typeComparison = this.type.compareTo(o.type);
        if (typeComparison != 0) {
            return typeComparison;
        }
        int thisWeight = Integer.parseInt(this.weight);
        int otherWeight = Integer.parseInt(o.weight);
        int weightComparison = Integer.compare(thisWeight, otherWeight);
        if (weightComparison != 0) {
            return weightComparison;
        }
        return this.color.compareTo(o.color);
    }

    @Override
    public String toString() {
        return "Корнеплод{" +
                "Тип='" + type + '\'' +
                ", Вес=" + weight +
                ", Цвет='" + color + '\'' +
                '}';
    }

    public static class RootVegetableBuilder {
        private String type;
        private String weight;
        private String color;

        public RootVegetableBuilder type(String type) {
            this.type = type;
            return this;
        }

        public RootVegetableBuilder weight(String weight) {
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