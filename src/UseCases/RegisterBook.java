package UseCases;

import DTOs.BookInformation;
import Entities.Book;
import Persistence.BookRepository;
import Receiver.Receiver;

import java.util.List;

public class RegisterBook {

    private Receiver receiver;
    private BookInformation bookInformation;
    private BookRepository bookRepository;
    private Book book;

    public RegisterBook(BookRepository bookRepository, Receiver receiver, BookInformation bookInformation) {
        this.bookRepository = bookRepository;
        this.bookInformation = bookInformation;
        this.receiver = receiver;
    }

    public void execute() {
        this.book = createBook();
        if (book.isValid() && bookIsUnique()) {
            bookRepository.save(book);
            receiver.bookRegistrationSuccessful();
        } else {
            receiver.bookRegistrationFailed();
        }
    }

    private boolean bookIsUnique() {
        List<Book> books = bookRepository.getAll();
        for (Book book : books) {
            if (this.book.getISBN().equalsIgnoreCase(book.getISBN())) {
                return false;
            }
        }
        return true;
    }

    private Book createBook() {
        Book book = new Book(receiver);
        book.setTitle(bookInformation.title);
        book.setAuthor(bookInformation.author);
        book.setISBN(bookInformation.ISBN);
        book.setEdition(bookInformation.edition);
        book.setPublishingCompany(bookInformation.publishingCompany);
        return book;
    }
}
