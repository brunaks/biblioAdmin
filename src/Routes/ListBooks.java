package Routes;

import Persistence.BookRepository;
import UseCases.ReadBooks;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Created by Bruna Koch Schmitt on 28/11/2016.
 */
public class ListBooks implements Route{

    private BookRepository bookRepository;
    Gson converter;

    public ListBooks(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        converter = new Gson();
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        ReadBooks readBooks = new ReadBooks(bookRepository);
        return converter.toJson(readBooks.getAll());
    }
}
