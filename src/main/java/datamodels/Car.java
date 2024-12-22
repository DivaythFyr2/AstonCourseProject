package datamodels;

import java.util.Comparator;

public class Car {
    private String model;
    private int power;
    private int yearOfManufacture;

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

    static class CarBuilder {
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
    public static class CarPowerComparator implements Comparator<Car> {
        @Override
        public int compare(Car car1, Car car2) {
            return Integer.compare(car1.getPower(), car2.getPower());
        }
    }
}

