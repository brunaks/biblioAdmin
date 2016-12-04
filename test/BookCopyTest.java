import Entities.BookCopy;
import Entities.ISBN;
import Receiver.SimpleReceiver;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Bruna Koch Schmitt on 04/12/2016.
 */
public class BookCopyTest {

    @Test
    public void createValidBookCopy() {
        SimpleReceiver receiver = new SimpleReceiver();
        BookCopy bookCopy = new BookCopy(receiver);
        bookCopy.setId("1");
        bookCopy.setStatus(BookCopy.Status.AVAILABLE);
        bookCopy.setISBN(new ISBN("1111111111111", receiver));
        Assert.assertTrue(bookCopy.isValid());
        assertNoErrors(receiver);
    }

    private void assertNoErrors(SimpleReceiver receiver) {
        Assert.assertFalse(receiver.ISBNFormatIsNotValid);
        Assert.assertFalse(receiver.idIsEmpty);
        Assert.assertFalse(receiver.statusIsEmpty);
    }

    @Test
    public void ISBNIsNotValid() {
        SimpleReceiver receiver = new SimpleReceiver();
        BookCopy bookCopy = new BookCopy(receiver);
        bookCopy.setId("1");
        bookCopy.setStatus(BookCopy.Status.AVAILABLE);
        bookCopy.setISBN(new ISBN("111111111111", receiver));
        Assert.assertFalse(bookCopy.isValid());
        Assert.assertTrue(receiver.ISBNFormatIsNotValid);
        Assert.assertFalse(receiver.idIsEmpty);
        Assert.assertFalse(receiver.statusIsEmpty);
    }

    @Test
    public void IdIsNotValid() {
        SimpleReceiver receiver = new SimpleReceiver();
        BookCopy bookCopy = new BookCopy(receiver);
        bookCopy.setId("");
        bookCopy.setStatus(BookCopy.Status.AVAILABLE);
        bookCopy.setISBN(new ISBN("1111111111111", receiver));
        Assert.assertFalse(bookCopy.isValid());
        Assert.assertFalse(receiver.ISBNFormatIsNotValid);
        Assert.assertTrue(receiver.idIsEmpty);
        Assert.assertFalse(receiver.statusIsEmpty);
    }

    @Test
    public void statusIsNotValid() {
        SimpleReceiver receiver = new SimpleReceiver();
        BookCopy bookCopy = new BookCopy(receiver);
        bookCopy.setId("1");
        bookCopy.setStatus(null);
        bookCopy.setISBN(new ISBN("1111111111111", receiver));
        Assert.assertFalse(bookCopy.isValid());
        Assert.assertFalse(receiver.ISBNFormatIsNotValid);
        Assert.assertFalse(receiver.idIsEmpty);
        Assert.assertTrue(receiver.statusIsEmpty);
    }
}
