package reader;

import java.util.List;

public final class ValidationUtils {

    private ValidationUtils() {}

    //Если строка пустая - false
    //   Если в строка не int - false
    //      Если число не попадает в заданный диапазон - false
    //          Прошли все проверки - true
    public static boolean checkInt(String param, int min, int max){
        if (param.isEmpty()){
            return false;
        } else if (param.matches("[-+]?\\d+")){
            return Integer.parseInt(param) > min && Integer.parseInt(param) < max;
        } else return false;
    }

    //Если строка пустая - false
    //   Если в строка не double - false
    //      Если число не попадает в заданный диапазон - false
    //          Прошли все проверки - true
    public static boolean checkDouble(String param, double min, double max) {
        if (param.isEmpty()){
            return false;
        } else if (param.matches("[0-9]{1,13}(\\.[0-9]*)?")){
            return Double.parseDouble(param) > min && Double.parseDouble(param) < max;
        } else return false;
    }

    //Если строка пустая - false
    //    Если сторка не содержится в коллекции с ваниантами наименований - false
    //          Прошли все проверки - true
    public static boolean checkString(String param, List<String> list) {
        if (param == null || param.isEmpty()) return false;
        return list.stream().anyMatch(param::equalsIgnoreCase);
    }
}
