package reader;

import java.util.List;
import java.util.Scanner;

import static reader.StringsConsole.*;
import static reader.ValidationUtils.*;
import static reader.ValidationConstants.*;

public final class ReaderUserCar implements ReaderStrategy {

    @Override
    public String[] readConsole(List<String> list, List<String> list2, Scanner scanner) {
        System.out.println(ADD_START);
        String[] strings = new String[CAR.length];
        for (int i = 0; i < CAR.length; i++) {
            System.out.print(CAR[i]);
            String input;
            while (true) {
                input = scanner.nextLine();
                if (i == 0) {
                    if (!checkString(input, list)) System.out.println(FAIL + CAR[i]);
                    else break;
                }
                if (i == 1) {
                    if (!checkInt(input, CAR_MIN_POWER, CAR_MAX_POWER))
                        System.out.println(FAIL + CAR[i]);
                    else break;
                }
                if (i == 2) {
                    if (!checkDouble(input, CAR_MIN_YEAR, CAR_MAX_YEAR)) System.out.println(FAIL + CAR[i]);
                    else break;
                }
            }
            strings[i] = input;
        }
        System.out.println(ADD_OK);
        return strings;
    }
}