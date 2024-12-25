package reader;

import java.util.List;
import java.util.Scanner;

import static reader.StringsConsole.*;
import static reader.ValidationUtils.*;
import static reader.ValidationConstants.*;

public final class ReaderUserRootVegetables implements ReaderStrategy {

    @Override
    public String[] readConsole(List<String> list, List<String> list2, Scanner scanner) {
        System.out.println(ADD_START);
        String[] strings = new String[ROOT_VEGETABLES.length];
        for (int i = 0; i < ROOT_VEGETABLES.length; i++) {
            System.out.print(ROOT_VEGETABLES[i]);
            String input;
            while (true) {
                input = scanner.nextLine();
                if (i == 0) {
                    if (!checkString(input, list)) System.out.println(FAIL + ROOT_VEGETABLES[i]);
                    else break;
                }
                if (i == 1) {
                    if (!checkDouble(input, ROOT_VEGETABLES_MIN_WEIGHT, ROOT_VEGETABLES_MAX_WEIGHT))
                        System.out.println(FAIL + ROOT_VEGETABLES[i]);
                    else break;
                }
                if (i == 2) {
                    if (!checkString(input, list2)) System.out.println(FAIL + ROOT_VEGETABLES[i]);
                    else break;
                }
            }
            strings[i] = input;
        }
        System.out.println(ADD_OK);
        return strings;
    }
}
