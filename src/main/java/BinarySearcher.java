import java.util.List;

public class BinarySearcher {
    public static <T extends Comparable<T>> int binarySearch(List<T> sortedList, T key) {
        int left = 0, right = sortedList.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (sortedList.get(mid).compareTo(key) == 0) {
                return mid;
            }
            if (sortedList.get(mid).compareTo(key) < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1; // Element not found
    }
}