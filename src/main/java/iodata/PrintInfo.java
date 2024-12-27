package iodata;

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
}
