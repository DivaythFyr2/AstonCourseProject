package reader;

public final class StringsConsole {

    private StringsConsole() {}

       public static final String ADD_START = "Введите, пожалуйста, данные:";
       public static final String ADD_OK = "Данные успешно добавлены.";
       public static final String FAIL = "Введены неверные данные, попробуйте еще \n";

       public static final String[] CAR = {"Модель: ", "Мощность: ", "Год производства: "};
       public static final int CAR_MIN_POWER = 0;
       public static final int CAR_MAX_POWER = 2000;
       public static final int CAR_MIN_YEAR = 1800;
       public static final int CAR_MAX_YEAR = 2025;

       public static final String[] BOOK = {"Название: ", "Автор: ", "Колличество страниц: "};
       public static final int BOOK_MIN_PAGE = 0;
       public static final int BOOK_MAX_PAGE = 10000;

       public static final String[] ROOT_VEGETABLES = {"Вид: ", "Вес: ", "Название: "};
       public static final double ROOT_VEGETABLES_MIN_WEIGHT = 0;
       public static final double ROOT_VEGETABLES_MAX_WEIGHT = 250;
}
