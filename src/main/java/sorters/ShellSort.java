package sorters;

import java.util.List;

public class ShellSort<T extends Comparable<T>> {
    public void sort(List<T> items) {
        int n = items.size();
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                T temp = items.get(i);
                int j;
                for (j = i; j >= gap && items.get(j - gap).compareTo(temp) > 0; j -= gap) {
                    items.set(j, items.get(j - gap));
                }
                items.set(j, temp);
            }
        }
    }
}