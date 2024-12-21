import java.util.List;

public class ShellSort<T extends Comparable<T>> implements SortStrategy<T> {
    @Override
    public void sort(List<T> items) {
        int count = items.size();
        for (int gap = count / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < count; i++) {
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
