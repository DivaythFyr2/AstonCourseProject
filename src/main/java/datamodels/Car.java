package datamodels;

import java.util.Comparator;

public class Car implements Comparable<Car> {
    private String model;
    private String power;
    private String yearOfManufacture;

    public static final Comparator<Car> BY_MODEL = Comparator.comparing(Car::getModel);
    public static final Comparator<Car> BY_POWER = (car1, car2) -> {
        int carPower1 = Integer.parseInt(car1.getPower());
        int carPower2 = Integer.parseInt(car2.getPower());
        return Integer.compare(carPower1, carPower2);
    };
    public static final Comparator<Car> BY_YEAR_OF_MANUFACTURE = (car1, car2) -> {
        int carYear1 = Integer.parseInt(car1.getYearOfManufacture());
        int carYear2 = Integer.parseInt(car2.getYearOfManufacture());
        return Integer.compare(carYear1, carYear2);
    };

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

