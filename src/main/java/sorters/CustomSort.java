package sorters;

import datamodels.Book;
import datamodels.Car;
import datamodels.RootVegetable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CustomSort {
    public static void sortItems(List<Object> items) {
        // Список для хранения четных значений
        List<Object> evenValues = new ArrayList<>();

        // Извлекаем четные значения
        for (Object item : items) {
            int value = 0;

            if (item instanceof Car) {
                value = ((Car) item).getPower();
            } else if (item instanceof Book) {
                value = ((Book) item).getPageCount();
            } else if (item instanceof RootVegetable) {
                value = (int) ((RootVegetable) item).getWeight();
            }

            if (value % 2 == 0) {
                evenValues.add(item);
            } else {
                evenValues.add(null); // Заполняем null для нечетных значений
            }
        }

        // Сортируем четные значения
        List<Object> sortedEvenValues = new ArrayList<>();
        for (Object item : items) {
            if (item instanceof Car && ((Car) item).getPower() % 2 == 0) {
                sortedEvenValues.add(item);
            } else if (item instanceof Book && ((Book) item).getPageCount() % 2 == 0) {
                sortedEvenValues.add(item);
            } else if (item instanceof RootVegetable && ((RootVegetable) item).getWeight() % 2 == 0) {
                sortedEvenValues.add(item);
            }
        }

        sortedEvenValues.sort(Comparator.comparingInt(o -> {
            if (o instanceof Car) return ((Car) o).getPower();
            if (o instanceof Book) return ((Book) o).getPageCount();
            if (o instanceof RootVegetable) return (int) ((RootVegetable) o).getWeight();
            return Integer.MAX_VALUE; // Для безопасности
        }));

        // Индекс для отслеживания текущего четного значения
        int evenIndex = 0;

        // Заполняем исходный список, оставляя нечетные значения на местах
        for (int i = 0; i < items.size(); i++) {
            if (evenValues.get(i) != null) {
                items.set(i, sortedEvenValues.get(evenIndex++));
            }
        }
    }
}