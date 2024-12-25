package sorters;

import java.util.Comparator;
import java.util.List;

public class ShellSort {

    public static <T> void shellSort(List<T> list, Comparator<? super T> comparator) {
        int n = list.size();
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                T temp = list.get(i);
                int j;
                for (j = i; j >= gap && comparator.compare(list.get(j - gap), temp) > 0; j -= gap) {
                    list.set(j, list.get(j - gap));
                }
                list.set(j, temp);
            }
        }
    }
}