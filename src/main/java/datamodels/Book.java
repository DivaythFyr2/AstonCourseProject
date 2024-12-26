package datamodels;

import java.util.Comparator;

public class Book implements Comparable<Book> {
    private final String title;
    private final String author;
    private final int pageCount;


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

    public static Comparator<Book> byTittle() {
        return Comparator.comparing(Book::getTitle);
    }

    public static Comparator<Book> byAuthor() {
        return Comparator.comparing(Book::getAuthor);
    }

    public static Comparator<Book> byPageCount() {
        return Comparator.comparingInt(Book::getPageCount);
    }

    @Override
    public String toString() {
        return "Книга{" +
                "Название='" + title + '\'' +
                ", Автор='" + author + '\'' +
                ", Кол-во страниц=" + pageCount +
                '}';
    }

    @Override
    public int compareTo(Book other) {
        return Integer.compare(this.pageCount, other.getPageCount());
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
