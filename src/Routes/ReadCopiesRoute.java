package Routes;

import Persistence.BookRepository;
import UseCases.ReadBookCopies;
import UseCases.ReadBooks;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Created by I848075 on 29/11/2016.
 */
public class ReadCopiesRoute implements Route{

    private BookRepository bookRepository;
    Gson converter;

    public ReadCopiesRoute(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        converter = new Gson();
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        String isbn = request.queryParams("isbn");
        ReadBookCopies readBookCopies = new ReadBookCopies(bookRepository);
        return converter.toJson(readBookCopies.getAllFor(isbn));
    }
}
