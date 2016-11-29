package Persistence;

import Entities.Book;
import Entities.BookCopy;

import java.util.List;

/**
 * Created by 0143138 on 08/11/2016.
 */
public interface BookRepository {
    void save(Book book);
    List<Book> getAll();
    Book getBookWith(String ISBN);
    void save(BookCopy bookCopy);
    void deleteBookWith(String ISBN);
    void deleteBookCopyWith(String isbn, String id);
    void update(Book book);
}
