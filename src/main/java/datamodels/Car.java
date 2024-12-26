package datamodels;

public class Car implements Comparable<Car> {
    private final String model;
    private final int power;
    private final int yearOfManufacture;

    public Car(CarBuilder builder) {
        this.model = builder.model;
        this.power = builder.power;
        this.yearOfManufacture = builder.yearOfManufacture;
    }

    public String getModel() {
        return model;
    }

    public int getPower() {
        return power;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }


    @Override
    public String toString() {
        return "Автомобиль{" +
                "Модель='" + model + '\'' +
                ", Мощность=" + power +
                ", Год производства=" + yearOfManufacture +
                '}';
    }

    @Override
    public int compareTo(Car other) {
        return Integer.compare(this.power, other.getPower());
    }

    public static class CarBuilder {
        private String model;
        private int power;
        private int yearOfManufacture;

        public CarBuilder model(String model) {
            this.model = model;
            return this;
        }

        public CarBuilder power(int power) {
            this.power = power;
            return this;
        }

        public CarBuilder yearOfManufacture(int yearOfManufacture) {
            this.yearOfManufacture = yearOfManufacture;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }
}

