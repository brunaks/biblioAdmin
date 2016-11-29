package UseCases;

import Entities.Book;
import Persistence.BookRepository;
import Receiver.Receiver;

/**
 * Created by Bruna Koch Schmitt on 29/11/2016.
 */
public class DeleteBookCopy {

    private Receiver receiver;
    private BookRepository bookRepository;

    public DeleteBookCopy(BookRepository bookRepository, Receiver receiver) {
        this.bookRepository = bookRepository;
        this.receiver = receiver;
    }

    public void with(String isbn, String id) {
        if (!isbn.isEmpty() && !id.isEmpty()) {
            Book book = bookRepository.getBookWith(isbn);
            if (book != null) {
                bookRepository.deleteBookCopyWith(isbn, id);
                receiver.bookCopyDeletionWasSuccessful();
            }
        }
    }
}