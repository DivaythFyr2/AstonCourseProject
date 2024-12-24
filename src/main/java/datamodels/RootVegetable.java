package datamodels;

import java.util.Comparator;

public class RootVegetable implements Comparable<RootVegetable> {
    private String type;
    private String weight;
    private String color;

    public static final Comparator<RootVegetable> BY_TYPE = Comparator.comparing(RootVegetable::getType);
    public static final Comparator<RootVegetable> BY_WEIGHT = (vegetable1, vegetable2) -> {
        int vegetableWeight1 = Integer.parseInt(vegetable1.getWeight());
        int vegetableWeight2 = Integer.parseInt(vegetable2.getWeight());
        return Integer.compare(vegetableWeight1, vegetableWeight2);
    };
    public static final Comparator<RootVegetable> BY_COLOR = Comparator.comparing(RootVegetable::getColor);

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