package datamodels;

import java.util.Comparator;

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

        public RootVegetable build(RootVegetableBuilder rootVegetableBuilder) {
            return new RootVegetable(this);
        }
    }

    public static class RootVegetableWeightComparator implements Comparator<RootVegetable> {

        @Override
        public int compare(RootVegetable rootVegetable1, RootVegetable rootVegetable2) {
            return Double.compare(rootVegetable1.getWeight(), rootVegetable2.getWeight());
        }
    }
}