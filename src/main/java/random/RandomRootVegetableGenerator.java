package random;

import datamodels.RootVegetable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static reader.StringsConsole.*;

public class RandomRootVegetableGenerator {

    public static List<RootVegetable> generateRandomVegetables(int count) {
        List<RootVegetable> rootVegetables = new ArrayList<>();
        Random r = new Random();

        for (int i = 0; i <count; i++) {
            String type = types.get(r.nextInt(types.size()));
            String weight = String.valueOf(ROOT_VEGETABLES_MIN_WEIGHT + r.nextInt(ROOT_VEGETABLES_MAX_WEIGHT));
            String color = types.get(r.nextInt(colors.size()));

            rootVegetables.add(new RootVegetable().RootVegetableBuilder
                    .type(type)
                    .weight(weight)
                    .color(color));
        }
        return rootVegetables;
    }
}
