package sorters;

import datamodels.Book;
import datamodels.Car;
import datamodels.RootVegetable;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.compare;

public class CustomSort<T> {
    public void sort(List<T> items) {
        // Сначала создаем массив для хранения четных значений и их индексов
        ArrayList<T> evenItems = new ArrayList<>();
        ArrayList<Integer> evenIndices = new ArrayList<>();
        // Заполняем массив четными элементами и запоминаем их индексы
        for (int i = 0; i < items.size(); i++) {
            T item = items.get(i);
            if ((item instanceof Car && ((Car) item).getPower() % 2 == 0) ||
                    (item instanceof Book && ((Book) item).getPageCount() % 2 == 0) ||
                    (item instanceof RootVegetable && ((RootVegetable) item).getWeight() % 2 == 0)) {
                evenItems.add(item);
                evenIndices.add(i);
            }
        }
        // Сортируем четные элементы
        for (int i = 0; i < evenItems.size(); i++) {
            for (int j = i + 1; j < evenItems.size(); j++) {
                if (compare(evenItems.get(i), evenItems.get(j)) > 0) {
                    // Меняем местами элементы
                    T temp = evenItems.get(i);
                    evenItems.set(i, evenItems.get(j));
                    evenItems.set(j, temp);
                }
            }
        }

        // Вставляем отсортированные четные элементы обратно в оригинальный список
        for (int i = 0; i < evenIndices.size(); i++) {
            items.set(evenIndices.get(i), evenItems.get(i));
        }
    }

    private int compare(T o1, T o2) {
        if (o1 instanceof Car && o2 instanceof Car) {
            return Integer.compare(((Car) o1).getPower(), ((Car) o2).getPower());
        } else if (o1 instanceof Book && o2 instanceof Book) {
            return Integer.compare(((Book) o1).getPageCount(), ((Book) o2).getPageCount());
        } else if (o1 instanceof RootVegetable && o2 instanceof RootVegetable) {
            return Double.compare(((RootVegetable) o1).getWeight(), ((RootVegetable) o2).getWeight());
        }
        return 0;
    }
}