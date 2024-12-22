package sorters;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class CustomSort {
    // Первое доп задание

    public static <T> void sort(List<T> list, Function<T, Integer> key, Comparator<T> comparator) {
        List<T> items = new ArrayList<>();
        for(T item: list) {
            if(key.apply(item) % 2 == 0) {
                items.add(item);
            }
        }
        items.sort(comparator);

        int index = 0;
        for (int i = 0; i <list.size(); i++) {
            if(key.apply(list.get(i)) % 2 == 0) {
                list.set(i,items.get(index++));
            }
        }
    }
}
