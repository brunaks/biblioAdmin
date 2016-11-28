package Main;

import Persistence.BookRepository;
import Persistence.InMemoryBookRepository;
import Receiver.*;
import Routes.DeleteBookRoute;
import Routes.ListBooks;
import Routes.RegisterBookRoute;
import spark.Request;
import spark.Response;
import spark.Route;
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
        Spark.get("/listCatalog", new ListBooks(bookRepository));
        Spark.post("/deleteBook", new DeleteBookRoute(bookRepository, receiver));
    }
}
