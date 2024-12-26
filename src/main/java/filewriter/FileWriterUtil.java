package filewriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileWriterUtil {
    private static final String ERROR = "Ошибка записи в файл: ";
    private static final String SUCCESS = "Запись успешно завершена";

    public static <T> void writeToFile(String filePath, List<T> data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            for (T item : data) {
                writer.write(item.toString());
                writer.newLine();
            }
            System.out.println(SUCCESS);
        } catch (IOException e) {
            System.out.println(ERROR + e.getMessage());
        }
    }

    public static <T> void writeSingleObjectToFile(String filePath, T object) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(object.toString());
            writer.newLine();
            System.out.println(SUCCESS);
        } catch (IOException e) {
            System.out.println(ERROR + e.getMessage());
        }
    }
}
