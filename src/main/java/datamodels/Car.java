package datamodels;

import controller.Controller;
import reader.ReaderUserCar;
import reader.ReaderUserContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public static List<Car> carCreation(String type) {
        List<Car> cars = new ArrayList<>();
        switch (type) {
            case "1":
                //Временная коллекция для валидации String
                ArrayList<String> listCars = new ArrayList<>(Arrays.asList("Мерседес", "БМВ", "Рено"));

                ReaderUserContext readerUser = new ReaderUserContext(new ReaderUserCar());
                do {
                    String[] parse =  readerUser.create(listCars, null, Controller.scanner);
                    cars.add(new Car.CarBuilder()
                            .model(parse[0])
                            .power(parse[1])
                            .yearOfManufacture(parse[2])
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
        }return cars;
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

