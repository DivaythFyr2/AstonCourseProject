package reader;

public final class StringsConsole {

    private StringsConsole() {}

       public static final String ADD_START = "Введите, пожалуйста, данные:";
       public static final String ADD_OK = "Данные успешно добавлены.";
       public static final String FAIL = "Введены неверные данные, попробуйте еще \n";
       public static final String ENTER_MORE = "Хотите ввести ещё? \n" +
               "Если \"да\" введите \"1\", если \"нет\" нажмите любую клавишу.";

       public static final String[] CAR = {"Модель: ", "Мощность, л.с.: ", "Год производства: "};
       public static final int CAR_MIN_POWER = 25;
       public static final int CAR_MAX_POWER = 2000;
       public static final int CAR_MIN_YEAR = 1885;
       public static final int CAR_MAX_YEAR = 2025;

       public static final String[] BOOK = {"Название: ", "Автор: ", "Колличество страниц: "};
       public static final int BOOK_MIN_PAGE = 15;
       public static final int BOOK_MAX_PAGE = 10000;

       public static final String[] ROOT_VEGETABLES = {"Вид: ", "Вес, гр.: ", "Цвет: "};
       public static final int ROOT_VEGETABLES_MIN_WEIGHT = 50;
       public static final int ROOT_VEGETABLES_MAX_WEIGHT = 100000;
}
