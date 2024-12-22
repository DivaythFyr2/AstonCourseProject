package datamodels;

import java.util.Comparator;

public class Book {
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

        public Book build(BookBuilder bookBuilder) {
            return new Book(this);
        }
    }
    public static class BookPageCountComparator implements Comparator<Book> {

        @Override
        public int compare(Book book1, Book book2) {
            return Integer.compare(book1.getPageCount(), book2.getPageCount());
        }
    }
}
