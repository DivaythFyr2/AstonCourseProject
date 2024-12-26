package sorters;

import datamodels.Book;
import datamodels.Car;
import datamodels.RootVegetable;

import java.util.ArrayList;
import java.util.List;

public class CustomSort<T> {
    public void sort(List<T> items) {
        // Список для хранения четных значений и их индексов
        ArrayList<T> listItems = new ArrayList<>();
        ArrayList<Integer> listIndex = new ArrayList<>();
        // Заполняем список четными элементами и запоминаем их индексы
        for (int i = 0; i < items.size(); i++) {
            T item = items.get(i);
            if ((item instanceof Car && ((Car) item).getPower() % 2 == 0) ||
                    (item instanceof Book && ((Book) item).getPageCount() % 2 == 0) ||
                    (item instanceof RootVegetable && ((RootVegetable) item).getWeight() % 2 == 0)) {
                listItems.add(item);
                listIndex.add(i);
            }
        }
        // Сортируем четные элементы (алгоритм пузырьковой сортировки)
        for (int i = 0; i < listItems.size(); i++) {
            for (int j = i + 1; j < listItems.size(); j++) {
                if (compare(listItems.get(i), listItems.get(j)) > 0) {
                    // Меняем местами элементы
                    T temp = listItems.get(i);
                    listItems.set(i, listItems.get(j));
                    listItems.set(j, temp);
                }
            }
        }
        // Вставляем отсортированные четные элементы обратно в оригинальный список
        for (int i = 0; i < listIndex.size(); i++) {
            items.set(listIndex.get(i), listItems.get(i));
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