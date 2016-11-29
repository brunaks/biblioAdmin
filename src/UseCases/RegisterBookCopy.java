package UseCases;

import DTOs.BookCopyInformation;
import Entities.Book;
import Entities.BookCopy;
import Persistence.BookRepository;
import Receiver.*;

/**
 * Created by Bruna Koch Schmitt on 27/11/2016.
 */
public class RegisterBookCopy {

    private Receiver receiver;
    private BookRepository bookRepository;
    private BookCopyInformation bookCopyInformation;

    public RegisterBookCopy(BookRepository bookRepository, BookCopyInformation bookCopyInformation, Receiver receiver) {
        this.bookRepository = bookRepository;
        this.bookCopyInformation = bookCopyInformation;
        this.receiver = receiver;
    }

    public void execute() {
        if (bookCopyInformation != null) {
            BookCopy bookCopy = convert(bookCopyInformation);
            if (bookCopy.isValid() && BookCopyIsUnique()) {
                Book book = bookRepository.getBookWith(bookCopy.getISBN());
                if (book != null) {
                    bookRepository.save(bookCopy);
                    receiver.bookCopyRegistrationSuccessful();
                } else {
                    receiver.bookCopyRegistrationFailed();
                }
            } else {
                receiver.bookCopyRegistrationFailed();
            }
        }
    }

    private boolean BookCopyIsUnique() {
        ReadBookCopies readBookCopies = new ReadBookCopies(bookRepository);
        for (BookCopyInformation bookCopyInformation : readBookCopies.getAllFor(this.bookCopyInformation.isbn)) {
            if (bookCopyInformation.id.equalsIgnoreCase(this.bookCopyInformation.id)) {
                return false;
            }
        }
        return true;
    }

    private BookCopy convert(BookCopyInformation bookCopyInformation) {
        BookCopy bookCopy = new BookCopy(receiver);
        bookCopy.setId(bookCopyInformation.id);
        bookCopy.setISBN(bookCopyInformation.isbn);
        bookCopy.setStatus(bookCopyInformation.status);
        bookCopy.setReturnDate(bookCopyInformation.returnDate);
        return bookCopy;
    }
}
