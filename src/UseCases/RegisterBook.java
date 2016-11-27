package UseCases;

import DTOs.BookInformation;
import Entities.Book;
import Persistence.BookRepository;

/**
 * Created by 0143138 on 08/11/2016.
 */
public class RegisterBook {

    private BookInformation bookInformation;
    private BookRepository bookRepository;
    private Book book;

    public RegisterBook(BookRepository bookRepository, BookInformation bookInformation) {
        this.bookRepository = bookRepository;
        this.bookInformation = bookInformation;
    }

    public void execute() {
        this.book = createBook();
        if (book.isValid()) {
            bookRepository.save(book);
        }
    }

    private Book createBook() {
        Book book = new Book();
        book.setTitle(bookInformation.title);
        book.setAuthor(bookInformation.author);
        book.setISBN(bookInformation.ISBN);
        book.setEdition(bookInformation.edition);
        book.setPublishingCompany(bookInformation.publishingCompany);
        return book;
    }
}
