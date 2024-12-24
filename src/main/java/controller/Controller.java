package controller;

import datamodels.Book;
import datamodels.Car;
import datamodels.RootVegetable;

import java.util.Scanner;

public class Controller {
    public final static Scanner scanner = new Scanner(System.in);

    public static void start() {
        while (true) {
            System.out.println("""
                    Введите номер класса для работы:\s
                    1. Автомобиль\s
                    2. Книга\s
                    3. Корнеплод\s
                    0. Выход из программы.\s""");
            String input = scanner.nextLine();
            if (isRes(input)) {
                switch (input) {
                    case "1":
                        System.out.println("Выбран класс <Автомобиль>");
                        Car.carCreation(completion());
                        break;
                    case "2":
                        System.out.println("Выбран класс <Книги>");
                        Book.bookCreation(completion());
                        break;
                    case "3":
                        System.out.println("Выбран класс <Корнеплоды>");
                        RootVegetable.rootVegetableCreation(completion());
                        break;
                    case "0":
                        System.exit(0);
                }
            } else {
                System.out.println("Ввод не корректен, введите одну из следующих цифр:");
            }
        }
    }

    private static String completion() {
        String result;
        Global:
        while (true) {
            System.out.println("""
                    Выберите вариант создания коллекции обьектов:\s
                    1. Вручную\s
                    2. Из файла (.txt)\s
                    3. Автозаполнение\s
                    4. Изменить выбор класса""");
            String input = scanner.nextLine();
            if (isRes(input)) {
                switch (input) {
                    case "1", "2", "3":
                        result = input;
                        break Global;
                    case "4":
                        start();
                }
            } else {
                System.out.println("Ввод не корректен, введите одну из следующих цифр:");
            }
        }
        return result;
    }

    private static boolean isRes(String input) {
        return input.matches("[0-4]");
    }
}