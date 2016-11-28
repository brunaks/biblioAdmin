package UseCases;

import DTOs.BookCopyInformation;
import Entities.Book;
import Entities.BookCopy;
import Persistence.BookRepository;
import Persistence.InMemoryBookRepository;

/**
 * Created by Bruna Koch Schmitt on 27/11/2016.
 */
public class RegisterBookCopy {

    private BookRepository bookRepository;
    private BookCopyInformation bookCopyInformation;

    public RegisterBookCopy(BookRepository bookRepository, BookCopyInformation bookCopyInformation) {
        this.bookRepository = bookRepository;
        this.bookCopyInformation = bookCopyInformation;
    }

    public void execute() {
        BookCopy bookCopy = convert(bookCopyInformation);
        if (bookCopy.isValid()) {
            Book book = bookRepository.getBookWith(bookCopy.getISBN());
            if (book != null) {
                bookRepository.save(bookCopy);
            }
        }
    }

    private BookCopy convert(BookCopyInformation bookCopyInformation) {
        BookCopy bookCopy = new BookCopy();
        bookCopy.setId(bookCopyInformation.id);
        bookCopy.setISBN(bookCopyInformation.ISBN);
        bookCopy.setStatus(bookCopyInformation.status);
        return bookCopy;
    }
}
