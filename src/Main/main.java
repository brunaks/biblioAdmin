package Main;

import DTOs.BookCopyInformation;
import DTOs.BookInformation;
import Entities.BookCopy;
import Persistence.BookRepository;
import Persistence.InMemoryBookRepository;
import Receiver.*;
import Routes.*;
import UseCases.RegisterBook;
import UseCases.RegisterBookCopy;
import spark.Spark;

/**
 * Created by Bruna Koch Schmitt on 28/11/2016.
 */
public class main {

    public static void main (String[] args) {
        Spark.port(Integer.parseInt(System.getenv("PORT")));
        Spark.externalStaticFileLocation("resources/public");

        BookRepository bookRepository = new InMemoryBookRepository();
        Receiver receiver = new SimpleReceiver();

        fillTestData(bookRepository);
        Spark.post("/registerBook", new RegisterBookRoute(bookRepository, receiver));
        Spark.get("/listCatalog", new ReadBooksRoute(bookRepository));
        Spark.delete("/deleteBook", new DeleteBookRoute(bookRepository, receiver));
        Spark.post("/deleteBook", new DeleteBookRoute(bookRepository, receiver));
        Spark.post("/registerBookCopy", new RegisterBookCopyRoute(bookRepository, receiver));
        Spark.get("/listCopies", new ReadCopiesRoute(bookRepository));
        Spark.delete("/deleteBookCopy", new DeleteBookCopyRoute(bookRepository, receiver));
        Spark.post("/loanBookCopy", new LoanBookCopyRoute(bookRepository, receiver));
        Spark.post("/returnBookCopy", new ReturnBookCopyRoute(bookRepository,receiver));
    }

    private static void fillTestData(BookRepository bookRepository) {
        Receiver dummyReceiver = new SimpleReceiver();
        BookInformation bookInformation = new BookInformation();
        bookInformation.author = "Author";
        bookInformation.title = "Title";
        bookInformation.ISBN = "1111111111111";
        bookInformation.edition = "1";
        bookInformation.publishingCompany = "Publishing Company";
        RegisterBook registerBook = new RegisterBook(bookRepository, dummyReceiver, bookInformation);
        registerBook.execute();

        BookCopyInformation bookCopyInformation = new BookCopyInformation();
        bookCopyInformation.id = "1";
        bookCopyInformation.isbn = "1111111111111";
        bookCopyInformation.status = BookCopy.Status.AVAILABLE.toString();
        bookCopyInformation.returnDate = "";
        RegisterBookCopy registerBookCopy = new RegisterBookCopy(bookRepository, bookCopyInformation, dummyReceiver);
        registerBookCopy.execute();

        BookCopyInformation bookCopyInformation2 = new BookCopyInformation();
        bookCopyInformation2.id = "2";
        bookCopyInformation2.isbn = "1111111111111";
        bookCopyInformation2.status = BookCopy.Status.AVAILABLE.toString();
        bookCopyInformation2.returnDate = "";
        RegisterBookCopy registerBookCopy2 = new RegisterBookCopy(bookRepository, bookCopyInformation2, dummyReceiver);
        registerBookCopy2.execute();
    }
}
