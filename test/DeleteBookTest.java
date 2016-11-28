import DTOs.BookInformation;
import Persistence.BookRepository;
import Persistence.InMemoryBookRepository;
import Receiver.SimpleReceiver;
import UseCases.DeleteBook;
import UseCases.ReadBooks;
import UseCases.RegisterBook;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by Bruna Koch Schmitt on 28/11/2016.
 */
public class DeleteBookTest {

    BookRepository bookRepository;
    SimpleReceiver receiver;

    @Before
    public void setUp() {
        bookRepository =  new InMemoryBookRepository();
        receiver = new SimpleReceiver();
    }

    @Test
    public void deleteOneBookWithNoCopies() {
        BookInformation bookInformation = new BookInformation();
        bookInformation.author = "Author";
        bookInformation.title = "Title";
        bookInformation.ISBN = "1111111111111";
        bookInformation.edition = "1";
        bookInformation.publishingCompany = "Publishing Company";
        RegisterBook registerBook = new RegisterBook(bookRepository, receiver, bookInformation);
        registerBook.execute();

        ReadBooks readBooks = new ReadBooks(bookRepository);
        List<BookInformation> books = readBooks.getAll();
        Assert.assertEquals(1, books.size());
        Assert.assertTrue(receiver.bookRegistrationWasSuccessful);
        assertBook(bookInformation, books.get(0));

        DeleteBook deleteBook = new DeleteBook(bookRepository, receiver);
        deleteBook.with(bookInformation.ISBN);

        books = readBooks.getAll();
        Assert.assertEquals(0, books.size());
        Assert.assertTrue(receiver.bookDeletionWasSuccessful);
    }

    @Test
    public void noBookToDelete() {
        ReadBooks readBooks = new ReadBooks(bookRepository);
        List<BookInformation> books = readBooks.getAll();
        Assert.assertEquals(0, books.size());

        DeleteBook deleteBook = new DeleteBook(bookRepository, receiver);
        deleteBook.with("1111111111111");

        books = readBooks.getAll();
        Assert.assertEquals(0, books.size());
        Assert.assertFalse(receiver.bookDeletionWasSuccessful);
    }

    public void assertBook(BookInformation expectedBookInformation, BookInformation actualBookInformation) {
        Assert.assertEquals(expectedBookInformation.title, actualBookInformation.title);
        Assert.assertEquals(expectedBookInformation.author, actualBookInformation.author);
        Assert.assertEquals(expectedBookInformation.ISBN, actualBookInformation.ISBN);
        Assert.assertEquals(expectedBookInformation.edition, actualBookInformation.edition);
        Assert.assertEquals(expectedBookInformation.publishingCompany, actualBookInformation.publishingCompany);
    }

}
