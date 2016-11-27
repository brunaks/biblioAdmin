package Persistence;

import Entities.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 0143138 on 08/11/2016.
 */
public class InMemoryBookRepository implements BookRepository {
    List<Book> books = new ArrayList<>();

    @Override
    public void save(Book book) {
        books.add(book);
    }

    @Override
    public List<Book> getAll() {
        return books;
    }
}
