package sorters;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public interface SortStrategy<T> {
    void sort(List<T> items, Comparator<T> comparator);
}
