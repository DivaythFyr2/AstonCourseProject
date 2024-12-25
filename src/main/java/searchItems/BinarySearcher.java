package searchItems;

import java.util.Comparator;
import java.util.List;

public class BinarySearcher {
    public static <T> int binarySearch(List<T> sortedList, T key, Comparator<T> comparator) {
        int left = 0, right = sortedList.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = comparator.compare(sortedList.get(mid), key);
            if (comparison == 0) {
                return mid;
            }
            if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}