import DTOs.BookCopyInformation;
import DTOs.BookInformation;
import Entities.BookCopy;
import Persistence.InMemoryBookRepository;
import UseCases.RegisterBook;
import UseCases.RegisterBookCopy;
import org.junit.Test;

public class RegisterBookCopyTest {

    @Test
    public void registerOneCopy() {
        InMemoryBookRepository bookRepository = new InMemoryBookRepository();
        BookInformation bookInformation = new BookInformation();
        bookInformation.author = "Author";
        bookInformation.title = "Title";
        bookInformation.ISBN = "1111111111111";
        bookInformation.edition = "1";
        bookInformation.publishingCompany = "Publishing Company";
        RegisterBook registerBook = new RegisterBook(bookRepository,bookInformation);

        BookCopyInformation bookCopyInformation = new BookCopyInformation();
        bookCopyInformation.id = "1";
        bookCopyInformation.ISBN = "1111111111111";
        bookCopyInformation.status = BookCopy.Status.AVAILABLE.toString();
        RegisterBookCopy registerBookCopy = new RegisterBookCopy(bookRepository, bookCopyInformation);
        registerBookCopy.execute();
    }
}
