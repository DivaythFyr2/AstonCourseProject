package iodata;

import controller.Controller;
import filewriter.FileWriterUtil;

import java.util.List;

public class PrintInfo {
    public static <T> void print(List<T> objects) {
        int counter = 1;
        System.out.println("--------------------------------------------------------------------");
        for(T object: objects) {
            System.out.println(counter++ + ". " + object.toString());
        }
        System.out.println("--------------------------------------------------------------------");
    }

    public static <T> void printObject(Integer resultIndex, List<T> list) {
        if (resultIndex >= 0) {
            System.out.println("-----------------------------------------------------");
            System.out.println("Искомый объект: " + list.get(resultIndex).toString());
            System.out.println("Индекс объекта в коллекции: " + ++resultIndex);
            System.out.println("-----------------------------------------------------");
            System.out.println("""
                    Хотите записать данный объект в файл?
                    1. Да
                    2. Нет
                    """);
            String next = Controller.scanner.nextLine();
            if (next.equals("1")) {
                System.out.println("Введите путь для сохранения найденного объекта в файл:");
                String filePath = Controller.scanner.nextLine();
                FileWriterUtil.writeSingleObjectToFile(filePath, list.get(--resultIndex));
                System.out.println("-----------------------------------------------------");
            }
        } else {
            System.out.println("Данного объекта нет в коллекции!");
        }
    }
}

