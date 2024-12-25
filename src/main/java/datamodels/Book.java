package datamodels;

import controller.Controller;
import reader.ReaderUserBook;
import reader.ReaderUserContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Book{
    private String title;
    private String author;
    private int pageCount;

    public Book(BookBuilder bookBuilder) {
        this.title = bookBuilder.title;
        this.author = bookBuilder.author;
        this.pageCount = bookBuilder.pageCount;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPageCount() {
        return pageCount;
    }

    @Override
    public String toString() {
        return "Книга{" +
                "Название='" + title + '\'' +
                ", Автор='" + author + '\'' +
                ", Кол-во страниц=" + pageCount +
                '}';
    }

    public static List<Book> bookCreation(String type) {
        List<Book> books = new ArrayList<>();
        switch (type) {
            case "1":
                //Временная коллекция для валидации String
                ArrayList<String> listTitle = new ArrayList<>(Arrays.asList("Война и мир", "Гамлет", "1984"));
                ArrayList<String> listAuthor = new ArrayList<>(Arrays.asList("Толстой", "Шекспир", "Оруэлл"));

                ReaderUserContext readerUser = new ReaderUserContext(new ReaderUserBook());
                do {
                    String[] parse =  readerUser.create(listTitle, listAuthor, Controller.scanner);
                    books.add(new BookBuilder()
                            .title(String.valueOf(parse[0]))
                            .author(String.valueOf(parse[1]))
                            .pageCount(Integer.parseInt(parse[2]))
                            .build());
                    System.out.println(reader.StringsConsole.ENTER_MORE);
                } while ((reader.ValidationUtils.checkInt(Controller.scanner.nextLine(), 0, 2)));
                break;
            case "2":
                // Утилитный метод по заполнению из файла
                break;
            case "3":
                // Утилитный метод автоматического заполнения
                break;
        } return books;
    }

    public static class BookBuilder {
        private String title;
        private String author;
        private int pageCount;

        public BookBuilder title(String title) {
            this.title = title;
            return this;
        }

        public BookBuilder author(String author) {
            this.author = author;
            return this;
        }

        public BookBuilder pageCount(int pageCount) {
            this.pageCount = pageCount;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }
}