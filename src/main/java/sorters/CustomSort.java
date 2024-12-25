package sorters;

import datamodels.Book;
import datamodels.Car;
import datamodels.RootVegetable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CustomSort<T> {

    public void sort(List<T> items) {
        List<T> evenValues = new ArrayList<>();
        for (T item : items) {
            int value = 0;

            if (item instanceof Car) {
                value = Integer.parseInt(((Car) item).getPower());
            } else if (item instanceof Book) {
                value = Integer.parseInt(((Book) item).getPageCount());
            } else if (item instanceof RootVegetable) {
                value = Integer.parseInt(((RootVegetable) item).getWeight());
            }

            if (value % 2 == 0) {
                evenValues.add(item);
            } else {
<<<<<<< HEAD
                evenValues.add(null);            }
        }

=======
                evenValues.add(null); // Заполняем null для нечетных значений
            }
        }

        // Сортируем четные значения
>>>>>>> origin/reader
        List<T> sortedEvenValues = new ArrayList<>();
        for (T item : items) {
            if (item instanceof Car && (Integer.parseInt(((Car) item).getPower()) % 2 == 0)) {
                sortedEvenValues.add(item);
            } else if (item instanceof Book && Integer.parseInt(((Book) item).getPageCount()) % 2 == 0) {
                sortedEvenValues.add(item);
            } else if (item instanceof RootVegetable && (Integer.parseInt(((RootVegetable) item).getWeight()) % 2 == 0)) {
                sortedEvenValues.add(item);
            }
        }

        sortedEvenValues.sort(Comparator.comparingInt(o -> {
            if (o instanceof Car) return Integer.parseInt(((Car) o).getPower());
            if (o instanceof Book) return Integer.parseInt(((Book) o).getPageCount());
            if (o instanceof RootVegetable) return Integer.parseInt(((RootVegetable) o).getWeight());
            return Integer.MAX_VALUE;
        }));

        int evenIndex = 0;

        for (int i = 0; i < items.size(); i++) {
            if (evenValues.get(i) != null) {
                items.set(i, sortedEvenValues.get(evenIndex++));
            }
        }
    }
}