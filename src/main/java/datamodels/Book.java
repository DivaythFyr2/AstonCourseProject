package datamodels;

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

    public static void bookCreation(String type) {
        switch (type) {
            case "1":
                // Утилитный метод по ручному заполнению
                break;
            case "2":
                // Утилитный метод по заполнению из файла
                break;
            case "3":
                // Утилитный метод автоматического заполнения
                break;
        }
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
