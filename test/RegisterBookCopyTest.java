import DTOs.BookCopyInformation;
import DTOs.BookInformation;
import Entities.BookCopy;
import Persistence.BookRepository;
import Persistence.InMemoryBookRepository;
import Receiver.SimpleReceiver;
import UseCases.ReadBookCopies;
import UseCases.RegisterBook;
import UseCases.RegisterBookCopy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class RegisterBookCopyTest {

    BookRepository bookRepository;
    SimpleReceiver receiver;

    @Before
    public void setUp() {
        bookRepository = new InMemoryBookRepository();
        receiver = new SimpleReceiver();
    }

    @Test
    public void registerOneCopy() {
        BookInformation bookInformation = new BookInformation();
        bookInformation.author = "Author";
        bookInformation.title = "Title";
        bookInformation.ISBN = "1111111111111";
        bookInformation.edition = "1";
        bookInformation.publishingCompany = "Publishing Company";
        RegisterBook registerBook = new RegisterBook(bookRepository, receiver, bookInformation);
        registerBook.execute();

        BookCopyInformation bookCopyInformation = new BookCopyInformation();
        bookCopyInformation.id = "1";
        bookCopyInformation.isbn = "1111111111111";
        bookCopyInformation.status = BookCopy.Status.AVAILABLE.toString();
        RegisterBookCopy registerBookCopy = new RegisterBookCopy(bookRepository, bookCopyInformation, receiver);
        registerBookCopy.execute();

        ReadBookCopies readBookCopies = new ReadBookCopies(bookRepository);
        List<BookCopyInformation> bookCopies = readBookCopies.getAllFor(bookCopyInformation.isbn);
        Assert.assertEquals(1, bookCopies.size());
        Assert.assertEquals("1", bookCopies.get(0).id);
        Assert.assertEquals("1111111111111", bookCopies.get(0).isbn);
        Assert.assertEquals(BookCopy.Status.AVAILABLE.toString(), bookCopies.get(0).status);
    }

    @Test
    public void noBookRegistered_ShouldNotRegisterCopy() {
        BookCopyInformation bookCopyInformation = new BookCopyInformation();
        bookCopyInformation.id = "1";
        bookCopyInformation.isbn = "1111111111111";
        bookCopyInformation.status = BookCopy.Status.AVAILABLE.toString();
        RegisterBookCopy registerBookCopy = new RegisterBookCopy(bookRepository, bookCopyInformation, receiver);
        registerBookCopy.execute();

        ReadBookCopies readBookCopies = new ReadBookCopies(bookRepository);
        List<BookCopyInformation> bookCopies = readBookCopies.getAllFor(bookCopyInformation.isbn);
        Assert.assertEquals(0, bookCopies.size());
    }

    @Test
    public void registerTwoCopies() {
        BookInformation bookInformation = new BookInformation();
        bookInformation.author = "Author";
        bookInformation.title = "Title";
        bookInformation.ISBN = "1111111111111";
        bookInformation.edition = "1";
        bookInformation.publishingCompany = "Publishing Company";
        RegisterBook registerBook = new RegisterBook(bookRepository, receiver, bookInformation);
        registerBook.execute();

        BookCopyInformation bookCopyInformation = new BookCopyInformation();
        bookCopyInformation.id = "1";
        bookCopyInformation.isbn = "1111111111111";
        bookCopyInformation.status = BookCopy.Status.AVAILABLE.toString();
        RegisterBookCopy registerBookCopy = new RegisterBookCopy(bookRepository, bookCopyInformation, receiver);
        registerBookCopy.execute();

        BookCopyInformation bookCopyInformation2 = new BookCopyInformation();
        bookCopyInformation2.id = "2";
        bookCopyInformation2.isbn = "1111111111111";
        bookCopyInformation2.status = BookCopy.Status.AVAILABLE.toString();
        RegisterBookCopy registerBookCopy2 = new RegisterBookCopy(bookRepository, bookCopyInformation2, receiver);
        registerBookCopy2.execute();

        ReadBookCopies readBookCopies = new ReadBookCopies(bookRepository);
        List<BookCopyInformation> bookCopies = readBookCopies.getAllFor(bookCopyInformation.isbn);
        Assert.assertEquals(2, bookCopies.size());
        Assert.assertEquals("1", bookCopies.get(0).id);
        Assert.assertEquals("1111111111111", bookCopies.get(0).isbn);
        Assert.assertEquals(BookCopy.Status.AVAILABLE.toString(), bookCopies.get(0).status);

        Assert.assertEquals("2", bookCopies.get(1).id);
        Assert.assertEquals("1111111111111", bookCopies.get(1).isbn);
        Assert.assertEquals(BookCopy.Status.AVAILABLE.toString(), bookCopies.get(1).status);

    }

    @Test
    public void idIsBlank_ShouldNotRegisterCopy() {
        BookInformation bookInformation = new BookInformation();
        bookInformation.author = "Author";
        bookInformation.title = "Title";
        bookInformation.ISBN = "1111111111111";
        bookInformation.edition = "1";
        bookInformation.publishingCompany = "Publishing Company";
        RegisterBook registerBook = new RegisterBook(bookRepository, receiver, bookInformation);
        registerBook.execute();

        BookCopyInformation bookCopyInformation = new BookCopyInformation();
        bookCopyInformation.id = "";
        bookCopyInformation.isbn = "1111111111111";
        bookCopyInformation.status = BookCopy.Status.AVAILABLE.toString();
        RegisterBookCopy registerBookCopy = new RegisterBookCopy(bookRepository, bookCopyInformation, receiver);
        registerBookCopy.execute();

        ReadBookCopies readBookCopies = new ReadBookCopies(bookRepository);
        List<BookCopyInformation> bookCopies = readBookCopies.getAllFor(bookCopyInformation.isbn);
        Assert.assertEquals(0, bookCopies.size());
    }

    @Test
    public void ISBNIsBlank_ShouldNotRegisterCopy() {
        BookInformation bookInformation = new BookInformation();
        bookInformation.author = "Author";
        bookInformation.title = "Title";
        bookInformation.ISBN = "1111111111111";
        bookInformation.edition = "1";
        bookInformation.publishingCompany = "Publishing Company";
        RegisterBook registerBook = new RegisterBook(bookRepository, receiver, bookInformation);
        registerBook.execute();

        BookCopyInformation bookCopyInformation = new BookCopyInformation();
        bookCopyInformation.id = "1";
        bookCopyInformation.isbn = "";
        bookCopyInformation.status = BookCopy.Status.AVAILABLE.toString();
        RegisterBookCopy registerBookCopy = new RegisterBookCopy(bookRepository, bookCopyInformation, receiver);
        registerBookCopy.execute();

        ReadBookCopies readBookCopies = new ReadBookCopies(bookRepository);
        List<BookCopyInformation> bookCopies = readBookCopies.getAllFor(bookCopyInformation.isbn);
        Assert.assertEquals(0, bookCopies.size());
    }

    @Test
    public void statusIsBlank_ShouldNotRegisterCopy() {
        BookInformation bookInformation = new BookInformation();
        bookInformation.author = "Author";
        bookInformation.title = "Title";
        bookInformation.ISBN = "1111111111111";
        bookInformation.edition = "1";
        bookInformation.publishingCompany = "Publishing Company";
        RegisterBook registerBook = new RegisterBook(bookRepository, receiver, bookInformation);
        registerBook.execute();

        BookCopyInformation bookCopyInformation = new BookCopyInformation();
        bookCopyInformation.id = "1";
        bookCopyInformation.isbn = "1111111111111";
        bookCopyInformation.status = "";
        RegisterBookCopy registerBookCopy = new RegisterBookCopy(bookRepository, bookCopyInformation, receiver);
        registerBookCopy.execute();

        ReadBookCopies readBookCopies = new ReadBookCopies(bookRepository);
        List<BookCopyInformation> bookCopies = readBookCopies.getAllFor(bookCopyInformation.isbn);
        Assert.assertEquals(0, bookCopies.size());
    }

    @Test
    public void sameId_doNotRegisterTwoCopies() {
        BookInformation bookInformation = new BookInformation();
        bookInformation.author = "Author";
        bookInformation.title = "Title";
        bookInformation.ISBN = "1111111111111";
        bookInformation.edition = "1";
        bookInformation.publishingCompany = "Publishing Company";
        RegisterBook registerBook = new RegisterBook(bookRepository, receiver, bookInformation);
        registerBook.execute();

        BookCopyInformation bookCopyInformation = new BookCopyInformation();
        bookCopyInformation.id = "1";
        bookCopyInformation.isbn = "1111111111111";
        bookCopyInformation.status = BookCopy.Status.AVAILABLE.toString();
        RegisterBookCopy registerBookCopy = new RegisterBookCopy(bookRepository, bookCopyInformation, receiver);
        registerBookCopy.execute();

        BookCopyInformation bookCopyInformation2 = new BookCopyInformation();
        bookCopyInformation2.id = "1";
        bookCopyInformation2.isbn = "1111111111111";
        bookCopyInformation2.status = BookCopy.Status.AVAILABLE.toString();
        RegisterBookCopy registerBookCopy2 = new RegisterBookCopy(bookRepository, bookCopyInformation2, receiver);
        registerBookCopy2.execute();

        ReadBookCopies readBookCopies = new ReadBookCopies(bookRepository);
        List<BookCopyInformation> bookCopies = readBookCopies.getAllFor(bookCopyInformation.isbn);
        Assert.assertEquals(1, bookCopies.size());
        Assert.assertEquals("1", bookCopies.get(0).id);
        Assert.assertEquals("1111111111111", bookCopies.get(0).isbn);
        Assert.assertEquals(BookCopy.Status.AVAILABLE.toString(), bookCopies.get(0).status);
    }
}
