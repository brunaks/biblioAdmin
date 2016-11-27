import DTOs.BookInformation;
import Entities.Book;
import Persistence.BookRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bruna Koch Schmitt on 22/11/2016.
 */
public class ReadBooks {

    private final BookRepository bookRepository;

    public ReadBooks(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public List<BookInformation> getAll() {
        List<Book> books = bookRepository.getAll();
        return convert(books);
    }

    private List<BookInformation> convert(List<Book> books) {
        List<BookInformation> convertedBooks = new ArrayList<>();
        for (Book book : books) {
            BookInformation bookInformation = new BookInformation();
            bookInformation.title = book.getTitle();
            bookInformation.author = book.getAuthor();
            bookInformation.ISBN = book.getISBN();
            bookInformation.publishingCompany = book.getPublishingCompany();
            bookInformation.edition = book.getEdition();
            convertedBooks.add(bookInformation);
        }

        return convertedBooks;
    }
}
