package Routes;

import Persistence.BookRepository;
import Receiver.Receiver;
import UseCases.DeleteBook;
import UseCases.DeleteBookCopy;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Created by Bruna Koch Schmitt on 29/11/2016.
 */
public class DeleteBookCopyRoute implements Route{

    private BookRepository bookRepository;
    private Receiver receiver;
    Gson converter;

    public DeleteBookCopyRoute(BookRepository bookRepository, Receiver receiver) {
        this.bookRepository = bookRepository;
        this.receiver = receiver;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        receiver.refresh();
        String isbn = request.queryParams("isbn");
        String id = request.queryParams("id");
        DeleteBookCopy deleteBookCopy = new DeleteBookCopy(bookRepository, receiver);
        deleteBookCopy.with(isbn, id);
        return converter.toJson(receiver);
    }
}
