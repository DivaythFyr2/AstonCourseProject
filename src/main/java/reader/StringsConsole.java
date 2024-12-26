package reader;

public final class StringsConsole {

   private StringsConsole() {}

   public static final String ADD_START = "Введите, пожалуйста, данные:";
   public static final String ADD_OK = "Данные успешно добавлены.";
   public static final String FAIL = "Введены неверные данные, попробуйте еще \n";
   public static final String ENTER_MORE = "Хотите ввести ещё? \n" +
           "Если \"да\" введите \"1\", если \"нет\" нажмите любую клавишу.";

   public static final String[] CAR = {"Модель: ", "Мощность: ", "Год производства: "};
   public static final String[] BOOK = {"Название: ", "Автор: ", "Колличество страниц: "};
   public static final String[] ROOT_VEGETABLES = {"Вид: ", "Вес: ", "Цвет: "};

   public static final String NAME_FILE = "Введите расположение и название файла:";
   public static final String FILE_NOT_FOUND = "Файл отсутвует, введите правильное расположение файла.";
   public static final String SPLIT_ENTER = ";";
   public static final String SPLIT_PARSE = " - ";
   public static final String LIST_EMPTY = "Файл не содержит данных для коллекции. Укажите необходимый файл.";


}