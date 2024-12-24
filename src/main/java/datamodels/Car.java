package datamodels;

public class Car implements Comparable<Car> {
    private String model;
    private String power;
    private String yearOfManufacture;

    public Car(CarBuilder builder) {
        this.model = builder.model;
        this.power = builder.power;
        this.yearOfManufacture = builder.yearOfManufacture;
    }

    public String getModel() {
        return model;
    }

    public String getPower() {
        return power;
    }

    public String getYearOfManufacture() {
        return yearOfManufacture;
    }

    @Override
    public int compareTo(Car o) {
        int modelComparison = this.model.compareTo(o.model);
        if (modelComparison != 0) {
            return modelComparison;
        }
        int thisPower = Integer.parseInt(this.power);
        int otherPower = Integer.parseInt(o.power);
        int powerComparison = Integer.compare(thisPower, otherPower);
        if (powerComparison != 0) {
            return powerComparison;
        }
        int thisYear = Integer.parseInt(this.yearOfManufacture);
        int otherYear = Integer.parseInt(o.yearOfManufacture);
        return Integer.compare(thisYear, otherYear);
    }

    @Override
    public String toString() {
        return "Автомобиль{" +
                "Модель='" + model + '\'' +
                ", Мощность=" + power +
                ", Год производства=" + yearOfManufacture +
                '}';
    }

    public static class CarBuilder {
        private String model;
        private String power;
        private String yearOfManufacture;

        public CarBuilder model(String model) {
            this.model = model;
            return this;
        }

        public CarBuilder power(String power) {
            this.power = power;
            return this;
        }

        public CarBuilder yearOfManufacture(String yearOfManufacture) {
            this.yearOfManufacture = yearOfManufacture;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }
}

