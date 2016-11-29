package Routes;

import DTOs.BookCopyInformation;
import DTOs.BookInformation;
import Persistence.BookRepository;
import Receiver.Receiver;
import UseCases.RegisterBook;
import UseCases.RegisterBookCopy;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Created by I848075 on 28/11/2016.
 */
public class RegisterBookCopyRoute implements Route{

    private Receiver receiver;
    private BookRepository bookRepository;
    Gson converter;

    public RegisterBookCopyRoute(BookRepository bookRepository, Receiver receiver) {
        this.bookRepository = bookRepository;
        this.receiver = receiver;
        converter = new Gson();
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        receiver.refresh();
        BookCopyInformation bookCopyInformation = convertRequest(request);
        RegisterBookCopy registerBookCopy = new RegisterBookCopy(bookRepository, bookCopyInformation, receiver);
        registerBookCopy.execute();
        return converter.toJson(this.receiver);
    }

    private BookCopyInformation convertRequest(Request request) {
        return converter.fromJson(request.body(), BookCopyInformation.class);
    }
}
