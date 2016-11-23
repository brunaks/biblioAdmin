import DTOs.BookInformation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bruna Koch Schmitt on 22/11/2016.
 */
public class ReadBooks {
    private List<BookInformation> books = new ArrayList<>();

    public List<BookInformation> getAll() {
        books.add(new BookInformation());
        return books;
    }
}
