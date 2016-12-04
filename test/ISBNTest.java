import Entities.ISBN;
import Receiver.SimpleReceiver;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Bruna Koch Schmitt on 04/12/2016.
 */
public class ISBNTest {

    @Test
    public void validISBN_13Digits() {
        SimpleReceiver receiver = new SimpleReceiver();
        ISBN isbn = new ISBN("1111111111111", receiver);
        Assert.assertTrue(isbn.isValid());
        Assert.assertFalse(receiver.ISBNFormatIsNotValid);
    }

    @Test
    public void validISBN_LastDigitCanBeAX() {
        SimpleReceiver receiver = new SimpleReceiver();
        ISBN isbn = new ISBN("111111111111X", receiver);
        Assert.assertTrue(isbn.isValid());
        Assert.assertFalse(receiver.ISBNFormatIsNotValid);
    }

    @Test
    public void invalidISBN_OnlyNumbersAllowedExceptLastOne() {
        SimpleReceiver receiver = new SimpleReceiver();
        ISBN isbn = new ISBN("A111111111111", receiver);
        Assert.assertFalse(isbn.isValid());
        Assert.assertTrue(receiver.ISBNFormatIsNotValid);
    }

    @Test
    public void invalidISBN_theLastDigitCannotBeALetterExceptX() {
        SimpleReceiver receiver = new SimpleReceiver();
        ISBN isbn = new ISBN("111111111111A", receiver);
        Assert.assertFalse(isbn.isValid());
        Assert.assertTrue(receiver.ISBNFormatIsNotValid);
    }

    @Test
    public void invalidISBN_mustNotHaveLessThan13Digits() {
        SimpleReceiver receiver = new SimpleReceiver();
        ISBN isbn = new ISBN("111111111111", receiver);
        Assert.assertFalse(isbn.isValid());
        Assert.assertTrue(receiver.ISBNFormatIsNotValid);
    }

    @Test
    public void invalidISBN_mustNotHaveMoreThan13Digits() {
        SimpleReceiver receiver = new SimpleReceiver();
        ISBN isbn = new ISBN("11111111111111", receiver);
        Assert.assertFalse(isbn.isValid());
        Assert.assertTrue(receiver.ISBNFormatIsNotValid);
    }

}
