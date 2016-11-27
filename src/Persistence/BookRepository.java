package Persistence;

import Entities.Book;

import java.util.List;

/**
 * Created by 0143138 on 08/11/2016.
 */
public interface BookRepository {
    void save(Book book);
    List<Book> getAll();
}
