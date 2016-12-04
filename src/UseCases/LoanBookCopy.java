package UseCases;

import DTOs.BookCopyInformation;
import Entities.Book;
import Entities.BookCopy;
import Persistence.BookRepository;
import Receiver.*;

import java.time.LocalDate;
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
                    if (bookCopies.get(i).getStatus().equals(BookCopy.Status.AVAILABLE)) {
                        bookCopies.get(i).setStatus(BookCopy.Status.TAKEN);
                        bookCopies.get(i).setReturnDate(LocalDate.now().plusDays(12).toString());
                        bookRepository.update(book);
                    }
                }
            }
        }
    }
}
