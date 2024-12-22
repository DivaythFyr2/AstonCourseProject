package sorters;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class ShellSort<T> implements SortStrategy<T> {
    @Override
    public void sort(List<T> items, Comparator<T> comparator) {
        int n = items.size();
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                T temp = items.get(i);
                int j;
                for (j = i; j >= gap && comparator.compare(items.get(j - gap), temp) > 0; j -= gap) {
                    items.set(j, items.get(j - gap));
                }
                items.set(j, temp);
            }
        }
    }
}