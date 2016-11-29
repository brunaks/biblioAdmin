package UseCases;

import Entities.Book;
import Entities.BookCopy;
import Persistence.BookRepository;
import Receiver.Receiver;

import java.util.List;

/**
 * Created by Bruna Koch Schmitt on 29/11/2016.
 */
public class ReturnBookCopy {

    private Receiver receiver;
    private BookRepository bookRepository;

    public ReturnBookCopy(BookRepository bookRepository, Receiver receiver) {
        this.bookRepository = bookRepository;
        this.receiver = receiver;
    }

    public void with(String isbn, String id) {
        Book book = bookRepository.getBookWith(isbn);
        if (book != null) {
            List<BookCopy> bookCopies = book.getBookCopies();
            for (int i = 0; i < bookCopies.size(); i++) {
                if (bookCopies.get(i).getId().equalsIgnoreCase(id)) {
                    if (bookCopies.get(i).getStatus().equalsIgnoreCase(BookCopy.Status.TAKEN.toString())) {
                        bookCopies.get(i).setStatus(BookCopy.Status.AVAILABLE.toString());
                        bookRepository.update(book);
                    }
                }
            }
        }
    }

}
