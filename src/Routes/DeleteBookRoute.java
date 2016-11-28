package Routes;

import Persistence.BookRepository;
import Receiver.Receiver;
import UseCases.DeleteBook;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Created by Bruna Koch Schmitt on 28/11/2016.
 */
public class DeleteBookRoute implements Route {


    private BookRepository bookRepository;
    private Receiver receiver;
    Gson converter;

    public DeleteBookRoute(BookRepository bookRepository, Receiver receiver) {
        this.bookRepository = bookRepository;
        this.receiver = receiver;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        String isbn = request.queryParams("isbn");
        DeleteBook deleteBook = new DeleteBook(bookRepository, receiver);
        deleteBook.with(isbn);
        return converter.toJson(receiver);
    }
}
