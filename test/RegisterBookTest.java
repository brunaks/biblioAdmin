import DTOs.BookInformation;
import Persistence.BookRepository;
import Persistence.InMemoryBookRepository;
import UseCases.ReadBooks;
import UseCases.RegisterBook;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import Receiver.SimpleReceiver;

import java.util.List;

public class RegisterBookTest {

    public BookRepository bookRepository;
    public SimpleReceiver receiver;

    @Before
    public void setUp() {
        bookRepository = new InMemoryBookRepository();
        receiver = new SimpleReceiver();
    }

    @Test
    public void registerOneBook() {
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
    }

    @Test
    public void noBookRegistered() {
        ReadBooks readBooks = new ReadBooks(bookRepository);
        Assert.assertEquals(0, readBooks.getAll().size());
        Assert.assertFalse(receiver.bookRegistrationWasSuccessful);
    }

    @Test
    public void titleShouldNotBeBlank() {
        BookInformation bookInformation = new BookInformation();
        bookInformation.author = "Author";
        bookInformation.title = "";
        bookInformation.ISBN = "1111111111111";
        bookInformation.edition = "1";
        bookInformation.publishingCompany = "Publishing Company";
        RegisterBook registerBook = new RegisterBook(bookRepository, receiver, bookInformation);
        registerBook.execute();

        ReadBooks readBooks = new ReadBooks(bookRepository);
        List<BookInformation> books = readBooks.getAll();

        Assert.assertEquals(0, books.size());
        Assert.assertFalse(receiver.bookRegistrationWasSuccessful);
    }

    @Test
    public void authorShouldNotBeBlank() {
        BookInformation bookInformation = new BookInformation();
        bookInformation.author = "";
        bookInformation.title = "Title";
        bookInformation.ISBN = "1111111111111";
        bookInformation.edition = "1";
        bookInformation.publishingCompany = "Publishing Company";
        RegisterBook registerBook = new RegisterBook(bookRepository, receiver, bookInformation);
        registerBook.execute();

        ReadBooks readBooks = new ReadBooks(bookRepository);
        List<BookInformation> books = readBooks.getAll();

        Assert.assertEquals(0, books.size());
        Assert.assertFalse(receiver.bookRegistrationWasSuccessful);
    }

    @Test
    public void editionShouldNotBeBlank() {
        BookInformation bookInformation = new BookInformation();
        bookInformation.author = "Author";
        bookInformation.title = "Title";
        bookInformation.ISBN = "1111111111111";
        bookInformation.edition = "";
        bookInformation.publishingCompany = "Publishing Company";
        RegisterBook registerBook = new RegisterBook(bookRepository, receiver, bookInformation);
        registerBook.execute();

        ReadBooks readBooks = new ReadBooks(bookRepository);
        List<BookInformation> books = readBooks.getAll();

        Assert.assertEquals(0, books.size());
        Assert.assertFalse(receiver.bookRegistrationWasSuccessful);
    }

    @Test
    public void ISBNShouldNotBeBlank() {
        BookInformation bookInformation = new BookInformation();
        bookInformation.author = "Author";
        bookInformation.title = "Title";
        bookInformation.ISBN = "";
        bookInformation.edition = "1";
        bookInformation.publishingCompany = "Publishing Company";
        RegisterBook registerBook = new RegisterBook(bookRepository, receiver, bookInformation);
        registerBook.execute();

        ReadBooks readBooks = new ReadBooks(bookRepository);
        List<BookInformation> books = readBooks.getAll();

        Assert.assertEquals(0, books.size());
        Assert.assertFalse(receiver.bookRegistrationWasSuccessful);
    }

    @Test
    public void PublishingCompanyShouldNotBeBlank() {
        BookInformation bookInformation = new BookInformation();
        bookInformation.author = "Author";
        bookInformation.title = "Title";
        bookInformation.ISBN = "1111111111111";
        bookInformation.edition = "1";
        bookInformation.publishingCompany = "";
        RegisterBook registerBook = new RegisterBook(bookRepository, receiver, bookInformation);
        registerBook.execute();

        ReadBooks readBooks = new ReadBooks(bookRepository);
        List<BookInformation> books = readBooks.getAll();

        Assert.assertEquals(0, books.size());
        Assert.assertFalse(receiver.bookRegistrationWasSuccessful);
    }

    @Test
    public void ISBNCodeShouldHave13Digits() {
        BookInformation bookInformation = new BookInformation();
        bookInformation.author = "Author";
        bookInformation.title = "Title";
        bookInformation.ISBN = "111111111111";
        bookInformation.edition = "1";
        bookInformation.publishingCompany = "Publishing Company";
        RegisterBook registerBook = new RegisterBook(bookRepository, receiver, bookInformation);
        registerBook.execute();

        ReadBooks readBooks = new ReadBooks(bookRepository);
        List<BookInformation> books = readBooks.getAll();

        Assert.assertEquals(0, books.size());
        Assert.assertFalse(receiver.bookRegistrationWasSuccessful);
    }

    @Test
    public void shouldNotRegisterTwoBooksWithSameISBN() {
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
        assertBook(bookInformation, books.get(0));
        Assert.assertTrue(receiver.bookRegistrationWasSuccessful);

        RegisterBook registerBook2 = new RegisterBook(bookRepository, receiver, bookInformation);
        registerBook2.execute();
        books = readBooks.getAll();
        Assert.assertEquals(1, books.size());
        assertBook(bookInformation, books.get(0));
        Assert.assertFalse(receiver.bookRegistrationWasSuccessful);
    }

    public void assertBook(BookInformation expectedBookInformation, BookInformation actualBookInformation) {
        Assert.assertEquals(expectedBookInformation.title, actualBookInformation.title);
        Assert.assertEquals(expectedBookInformation.author, actualBookInformation.author);
        Assert.assertEquals(expectedBookInformation.ISBN, actualBookInformation.ISBN);
        Assert.assertEquals(expectedBookInformation.edition, actualBookInformation.edition);
        Assert.assertEquals(expectedBookInformation.publishingCompany, actualBookInformation.publishingCompany);
    }
}
