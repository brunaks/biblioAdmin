package Main;

import Persistence.BookRepository;
import Persistence.InMemoryBookRepository;
import Receiver.*;
import Routes.*;
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
}
