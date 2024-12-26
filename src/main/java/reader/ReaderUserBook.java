package reader;

import java.util.List;
import java.util.Scanner;

import static reader.StringsConsole.*;
import static reader.ValidationUtils.*;
import static reader.ValidationConstants.*;

public final class ReaderUserBook implements ReaderStrategy {

    @Override
    public String[] readConsole(List<String> list, List<String> list2, Scanner scanner) {
        System.out.println(ADD_START);
        String[] strings = new String[BOOK.length];
        for (int i = 0; i < BOOK.length; i++) {
            System.out.print(BOOK[i]);
            String input;
            while (true) {
                input = scanner.nextLine();
                if (i == 0) {
                    if (!checkString(input, list)) System.out.println(FAIL + BOOK[i]);
                    else break;
                }
                if (i == 1) {
                    if (!checkString(input, list2)) System.out.println(FAIL + BOOK[i]);
                    else break;
                }
                if (i == 2) {
                    if (!checkInt(input, BOOK_MIN_PAGE, BOOK_MAX_PAGE)) System.out.println(FAIL + BOOK[i]);
                    else break;
                }
            }
            strings[i] = input;
        }
        return strings;
    }
}
