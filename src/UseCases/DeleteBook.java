package UseCases;

import Persistence.BookRepository;
import Receiver.Receiver;

/**
 * Created by Bruna Koch Schmitt on 28/11/2016.
 */
public class DeleteBook {

    private Receiver receiver;
    private BookRepository bookRepository;

    public DeleteBook(BookRepository bookRepository, Receiver receiver) {
        this.bookRepository = bookRepository;
        this.receiver = receiver;
    }

    public void with(String ISBN) {
        if (bookRepository.getBookWith(ISBN) != null) {
            bookRepository.deleteBookWith(ISBN);
            receiver.bookDeletionWasSuccessful();
        } else {
            receiver.bookDeletionFailed();
        }

    }
}
