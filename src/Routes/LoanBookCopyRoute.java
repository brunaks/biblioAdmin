package Routes;

import Persistence.BookRepository;
import Receiver.Receiver;
import UseCases.LoanBookCopy;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Created by Bruna Koch Schmitt on 29/11/2016.
 */
public class LoanBookCopyRoute implements Route {

    private BookRepository bookRepository;
    private Receiver receiver;
    Gson converter;

    public LoanBookCopyRoute(BookRepository bookRepository, Receiver receiver) {
        this.bookRepository = bookRepository;
        this.receiver = receiver;
        converter = new Gson();
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        receiver.refresh();
        String isbn = request.queryParams("isbn");
        String id = request.queryParams("id");
        LoanBookCopy loanBookCopy = new LoanBookCopy(bookRepository, receiver);
        loanBookCopy.with(isbn, id);
        return converter.toJson(receiver);
    }
}
