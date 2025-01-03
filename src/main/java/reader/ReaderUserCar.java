package reader;

import controller.Controller;

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
                    if (!checkString(input, list)) {
                        if (input.equalsIgnoreCase(BACK)) Controller.completion();
                        System.out.println(FAIL + CAR[i]);
                    }
                    else break;
                }
                if (i == 1) {
                    if (!checkInt(input, CAR_MIN_POWER, CAR_MAX_POWER)) {
                        if (input.equalsIgnoreCase(BACK)) Controller.completion();
                        System.out.println(FAIL + CAR[i]);
                    }
                    else break;
                }
                if (i == 2) {
                    if (!checkInt(input, CAR_MIN_YEAR, CAR_MAX_YEAR)) {
                        if (input.equalsIgnoreCase(BACK)) Controller.completion();
                        System.out.println(FAIL + CAR[i]);
                    }
                    else break;
                }
            }
            strings[i] = input;
        }
        return strings;
    }
}