package random;

import datamodels.RootVegetable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static reader.ValidationConstants.*;

public class RandomRootVegetableGenerator {

    public static List<RootVegetable> generateRandomVegetables(int count, List<String> types, List<String> colors) {
        List<RootVegetable> rootVegetables = new ArrayList<>();
        Random r = new Random();

        for (int i = 0; i < count; i++) {
            String type = types.get(r.nextInt(types.size()));
            double weight = ROOT_VEGETABLES_MIN_WEIGHT + r.nextDouble(ROOT_VEGETABLES_MAX_WEIGHT - ROOT_VEGETABLES_MIN_WEIGHT + 1);
            weight = Math.round(weight * 100) / 100.0;
            String color = colors.get(r.nextInt(colors.size()));

            rootVegetables.add(new RootVegetable.RootVegetableBuilder()
                    .type(type)
                    .weight(weight)
                    .color(color)
                    .build());
        }
        return rootVegetables;
    }
}
