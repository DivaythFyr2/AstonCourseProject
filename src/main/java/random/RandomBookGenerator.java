package random;

import datamodels.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static reader.ValidationConstants.*;

public class RandomBookGenerator {

    public static List<Book> generateRandomBooks(int count, List<String> titles, List<String> authors) {
        List<Book> books = new ArrayList<>();
        Random r = new Random();

        for (int i = 0; i < count; i++) {
            String title = titles.get(r.nextInt(titles.size()));
            String author = authors.get(r.nextInt(authors.size()));
            int pages = BOOK_MIN_PAGE + r.nextInt(BOOK_MAX_PAGE - BOOK_MIN_PAGE + 1);

            books.add(new Book.BookBuilder()
                    .title(title)
                    .author(author)
                    .pageCount(pages)
                    .build());
        }
        return books;
    }

}
