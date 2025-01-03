package reader;

import java.util.List;
import java.util.Scanner;

/**
 * Читалка консоли.
 * Вызывем:
 *      ReaderUserContext readerUser = new ReaderUserContext(new "Нужный класс ридера Машина/Книги/Плоды);
 * Получаем (параметры коллекции с названиями, которые проверяем, и сам сканер):
 *      String[] parse =  readerUser.create(list, list2, scanner);
 * данные из которого подставляем в класс ридера Машина/Книги/Плоды, который читали.
 */
public class ReaderUserContext {

        private final ReaderStrategy strategy;

        public ReaderUserContext(ReaderStrategy strategy) {
        this.strategy = strategy;
        }

        public String[] create(List<String> list, List<String> list2, Scanner sc) {
            return strategy.readConsole(list, list2, sc);
        }
}

