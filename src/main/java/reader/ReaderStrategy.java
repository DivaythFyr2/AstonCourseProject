package reader;

import java.util.List;
import java.util.Scanner;

public interface ReaderStrategy {

    String[] readConsole(List<String> list, List<String> list2, Scanner scanner);

}
