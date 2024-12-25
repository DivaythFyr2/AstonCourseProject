package random;

import datamodels.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static reader.StringsConsole.*;

public class RandomCarGenerator {

    public static List<Car> generateRandomCars(int count, List<String> models) {
        List<Car> cars = new ArrayList<>();
        Random r = new Random();

        for (int i = 0; i < count; i++) {
            String model = models.get(r.nextInt(models.size()));
            String power = String.valueOf(CAR_MIN_POWER + r.nextInt(CAR_MAX_POWER));
            String year = String.valueOf(CAR_MIN_YEAR + r.nextInt(CAR_MAX_YEAR));

            cars.add(new Car.CarBuilder()
                    .model(model)
                    .power(power)
                    .yearOfManufacture(year)
                    .build());
        }
        return cars;
    }
}
