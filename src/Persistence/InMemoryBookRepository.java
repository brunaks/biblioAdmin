package Persistence;

import Entities.Book;
import Entities.BookCopy;

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

    @Override
    public Book getBookWith(String ISBN) {
        for (Book book : books) {
            if (book.getISBN().equalsIgnoreCase(ISBN)) {
                return book;
            }
        }
        return null;
    }

    @Override
    public void save(BookCopy bookCopy) {
        Book book = getBookWith(bookCopy.getISBN().toString());
        book.getBookCopies().add(bookCopy);
    }

    @Override
    public void deleteBookWith(String ISBN) {
        Book book = getBookWith(ISBN);
        books.remove(book);
    }

    @Override
    public void deleteBookCopyWith(String isbn, String id) {
        Book book = getBookWith(isbn);
        if (book != null) {
            for (BookCopy bookCopy : book.getBookCopies()) {
                if (bookCopy.getId().equalsIgnoreCase(id)) {
                    book.getBookCopies().remove(bookCopy);
                }
            }
        }
    }

    @Override
    public void update(Book book) {

    }
}
