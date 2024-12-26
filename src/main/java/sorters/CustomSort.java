package sorters;

import datamodels.Book;
import datamodels.Car;
import datamodels.RootVegetable;

import java.util.ArrayList;
import java.util.List;

public class CustomSort<T> {

    //  определение четных и нечетных элементов и добавление их в список
    public void sort(List<T> items) {
        ArrayList<T> evenValues = new ArrayList<>();
        // Заполнение списка четными элементами
        for (T item : items) {
            if (item instanceof Car && ((Car) item).getPower() % 2 == 0) {
                evenValues.add(item);
            } else if (item instanceof Book && ((Book) item).getPageCount() % 2 == 0) {
                evenValues.add(item);
            } else if (item instanceof RootVegetable && ((RootVegetable) item).getWeight() % 2 == 0) {
                evenValues.add(item);
            }
        }

        //Сортировка четных элементов
        evenValues.sort((o1, o2) -> {
            if (o1 instanceof Car && o2 instanceof Car) {
                return Integer.compare(((Car) o1).getPower(), ((Car) o2).getPower());
            } else if (o1 instanceof Book && o2 instanceof Book) {
                return Integer.compare(((Book) o1).getPageCount(), ((Book) o2).getPageCount());
            } else if (o1 instanceof RootVegetable && o2 instanceof RootVegetable) {
                return Double.compare(((RootVegetable) o1).getWeight(), ((RootVegetable) o2).getWeight());
            }
            return Integer.MAX_VALUE;
        });

        //Индекс для отслеживания четных значений
        int evenIndex = 0;

        // заполнение списка, оставляя нечетные значения на своих местах
        for (int i = 0; i < items.size(); i++) {
            T item = items.get(i);
            if ((item instanceof Car && ((Car) item).getPower() % 2 == 0) ||
                    (item instanceof Book && ((Book) item).getPageCount() % 2 == 0) ||
                    (item instanceof RootVegetable && ((RootVegetable) item).getWeight() % 2 == 0)) {
                items.set(i, evenValues.get(evenIndex++));  // Замена элемента в указанной позиции в этом списке указанным элементом
            }
        }
    }
}