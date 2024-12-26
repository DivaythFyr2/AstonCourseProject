package sorters;

import datamodels.Book;
import datamodels.Car;
import datamodels.RootVegetable;

import java.util.ArrayList;
import java.util.List;

public class CustomSort {
    public static <T> void sort(List<T> items) {
        // Создадим
        ArrayList<T> listItems = new ArrayList<>(); // Список для хранения четных значений
        ArrayList<Integer> listIndex = new ArrayList<>(); // список для их индексов
        // Заполняем список четными элементами и запоминаем их индексы. НЕЧЕТНЫЕ ОСТАЮТСЯ НА СВОИХ МЕСТАХ. ИХ ВООБЩЕ НЕ ТРОГАЕМ
        for (int i = 0; i < items.size(); i++) {  // берем нашу сортировку Car, Book или овощи
            T item = items.get(i); // вытаскиваем каждый объект из списка и проверяем его числовой парамтр на четность
            if ((item instanceof Car && ((Car) item).getPower() % 2 == 0) || // и проверяем его числовой параметр на четность
                    (item instanceof Book && ((Book) item).getPageCount() % 2 == 0) ||
                    (item instanceof RootVegetable && ((RootVegetable) item).getWeight() % 2 == 0)) {
                listItems.add(item);  // если он относится к какому-то классу, добавляем его
                listIndex.add(i); // и запоминаем его индекс в другом списке
            }
        }
        // Сортируем четные элементы (алгоритм пузырьковой сортировки)
        for (int i = 0; i < listItems.size(); i++) { // перебираем все четные элементы в списке
            for (int j = i + 1; j < listItems.size(); j++) {   // сравниваем его и последующий элемент друг с другом
                if (compare(listItems.get(i), listItems.get(j)) > 0) { // вызов метода compare (НЕ СВЯЗАН С COMPARATOR и COMPARABLE)
                    // Если текущий элемент больше следующего, меняем их местами
                    T temp = listItems.get(i); // запоминаем текущий объекта
                    listItems.set(i, listItems.get(j)); // вставляем на местотекущего объекта  следующий объект
                    listItems.set(j, temp); // на место следующего ставим объект текущий
                }
            }
        }
        // Вставляем отсортированные четные элементы обратно в оригинальный список
        for (int i = 0; i < listIndex.size(); i++) {
            items.set(listIndex.get(i), listItems.get(i));
        }
    }

    private static int compare(Object o1, Object o2) { // метод сравнение объектов
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