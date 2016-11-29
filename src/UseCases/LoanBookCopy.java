package UseCases;

import DTOs.BookCopyInformation;
import Entities.Book;
import Entities.BookCopy;
import Persistence.BookRepository;
import Receiver.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bruna Koch Schmitt on 29/11/2016.
 */
public class LoanBookCopy {

    private Receiver receiver;
    private BookRepository bookRepository;

    public LoanBookCopy(BookRepository bookRepository, Receiver receiver) {
        this.bookRepository = bookRepository;
        this.receiver = receiver;
    }

    public void with(String isbn, String id) {
        Book book = bookRepository.getBookWith(isbn);
        if (book != null) {
            List<BookCopy> bookCopies = book.getBookCopies();
            for (int i = 0; i < bookCopies.size(); i++) {
                if (bookCopies.get(i).getId().equalsIgnoreCase(id)) {
                    if (bookCopies.get(i).getStatus().equalsIgnoreCase(BookCopy.Status.AVAILABLE.toString())) {
                        bookCopies.get(i).setStatus(BookCopy.Status.TAKEN.toString());
                        bookRepository.update(book);
                    }
                }
            }
        }
    }
}
