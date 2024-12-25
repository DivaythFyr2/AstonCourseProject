package random;

import datamodels.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static reader.StringsConsole.*;

public class RandomBookGenerator {

    public static List<Book> generateRandomBooks(int count, List<String> titles, List<String> authors) {
        List<Book> books = new ArrayList<>();
        Random r = new Random();

        for (int i = 0; i < count; i++) {
            String title = titles.get(r.nextInt(titles.size()));
            String author = authors.get(r.nextInt(authors.size()));
            String pages = String.valueOf(BOOK_MIN_PAGE + r.nextInt(BOOK_MAX_PAGE));

            books.add(new Book.BookBuilder()
                    .title(title)
                    .author(author)
                    .pageCount(Integer.parseInt(pages) )
                    .build());
        }
        return books;
    }

}
