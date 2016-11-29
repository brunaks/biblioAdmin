package Routes;

import DTOs.BookInformation;
import Persistence.BookRepository;
import Receiver.Receiver;
import UseCases.RegisterBook;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Created by Bruna Koch Schmitt on 28/11/2016.
 */
public class RegisterBookRoute implements Route {

    private Receiver receiver;
    private BookRepository bookRepository;
    Gson converter;

    public RegisterBookRoute(BookRepository bookRepository, Receiver receiver) {
        this.bookRepository = bookRepository;
        this.receiver = receiver;
        converter = new Gson();
        receiver.refresh();
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        BookInformation bookInformation = convertRequest(request);
        RegisterBook registerBook = new RegisterBook(bookRepository, receiver, bookInformation);
        registerBook.execute();
        return converter.toJson(this.receiver);
    }

    private BookInformation convertRequest(Request request) {
        return converter.fromJson(request.body(), BookInformation.class);
    }
}
