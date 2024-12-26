package datamodels;

public class RootVegetable implements Comparable<RootVegetable> {
    private final String type;
    private final double weight;
    private final String color;

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

    @Override
    public int compareTo(RootVegetable other) {
        return Double.compare(this.weight, other.getWeight());
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