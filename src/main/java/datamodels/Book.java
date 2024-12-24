package datamodels;

import controller.Controller;
import reader.ReaderUserBook;
import reader.ReaderUserContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Book implements Comparable<Book> {
    private String title;
    private String author;
    private String pageCount;

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

    public String getPageCount() {
        return pageCount;
    }

    @Override
    public int compareTo(Book o) {
        int titleComparison = this.title.compareTo(o.title);
        if (titleComparison != 0) {
            return titleComparison;
        }
        int authorComparison = this.author.compareTo(o.author);
        if (authorComparison != 0) {
            return authorComparison;
        }
        int thisPageCount = Integer.parseInt(this.pageCount);
        int otherPageCount = Integer.parseInt(o.pageCount);
        return Integer.compare(thisPageCount, otherPageCount);
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
                            .title(parse[0])
                            .author(parse[1])
                            .pageCount(parse[2])
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
        private String pageCount;

        public BookBuilder title(String title) {
            this.title = title;
            return this;
        }

        public BookBuilder author(String author) {
            this.author = author;
            return this;
        }

        public BookBuilder pageCount(String pageCount) {
            this.pageCount = pageCount;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }
}
