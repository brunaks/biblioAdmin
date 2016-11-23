import DTOs.BookInformation;
import Persistence.BookRepository;
import Persistence.InMemoryBookRepository;
import UseCases.RegisterBook;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by 0143138 on 08/11/2016.
 */
public class RegisterBookTest {

    @Test
    public void registerOneBook() {
        BookRepository bookRepository = new InMemoryBookRepository();
        BookInformation bookInformation = new BookInformation();
        bookInformation.setAuthor("Author");
        bookInformation.setTitle("Title");
        bookInformation.setISBN("1111111111111");
        bookInformation.setEdition("1");
        bookInformation.setPublishingCompany("Publishing Company");
        RegisterBook registerBook = new RegisterBook(bookRepository, bookInformation);
        registerBook.execute();

        ReadBooks readBooks = new ReadBooks();
        List<BookInformation> books = readBooks.getAll();

        Assert.assertEquals(1, books.size());

    }
}
