import org.junit.Test;

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
    }
}
