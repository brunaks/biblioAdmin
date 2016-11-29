import Entities.Book;
import Receiver.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Bruna Koch Schmitt on 27/11/2016.
 */
public class BookTest {

    SimpleReceiver receiver = new SimpleReceiver();

    @Test
    public void bookTitleShouldNotBeBlank() {
        Book book = new Book(receiver);
        book.setTitle("");
        book.setAuthor("Author");
        book.setEdition("1");
        book.setISBN("1111111111111");
        book.setPublishingCompany("Publishing Company");
        Assert.assertFalse(book.isValid());
        Assert.assertTrue(receiver.titleIsEmpty);
        Assert.assertFalse(receiver.authorIsEmpty);
        Assert.assertFalse(receiver.editionIsEmpty);
        Assert.assertFalse(receiver.publishingCompanyIsEmpty);
        Assert.assertFalse(receiver.ISBNFormatIsNotValid);
    }

    @Test
    public void authorTitleShouldNotBeBlank() {
        Book book = new Book(receiver);
        book.setTitle("Title");
        book.setAuthor("");
        book.setEdition("1");
        book.setISBN("1111111111111");
        book.setPublishingCompany("Publishing Company");
        Assert.assertFalse(book.isValid());
        Assert.assertFalse(receiver.titleIsEmpty);
        Assert.assertTrue(receiver.authorIsEmpty);
        Assert.assertFalse(receiver.editionIsEmpty);
        Assert.assertFalse(receiver.publishingCompanyIsEmpty);
        Assert.assertFalse(receiver.ISBNFormatIsNotValid);
    }

    @Test
    public void editionTitleShouldNotBeBlank() {
        Book book = new Book(receiver);
        book.setTitle("Title");
        book.setAuthor("Author");
        book.setEdition("");
        book.setISBN("1111111111111");
        book.setPublishingCompany("Publishing Company");
        Assert.assertFalse(book.isValid());
        Assert.assertFalse(receiver.titleIsEmpty);
        Assert.assertFalse(receiver.authorIsEmpty);
        Assert.assertTrue(receiver.editionIsEmpty);
        Assert.assertFalse(receiver.publishingCompanyIsEmpty);
        Assert.assertFalse(receiver.ISBNFormatIsNotValid);
    }

    @Test
    public void ISBNShouldNotBeBlank() {
        Book book = new Book(receiver);
        book.setTitle("Title");
        book.setAuthor("Author");
        book.setEdition("1");
        book.setISBN("");
        book.setPublishingCompany("Publishing Company");
        Assert.assertFalse(book.isValid());
        Assert.assertFalse(receiver.titleIsEmpty);
        Assert.assertFalse(receiver.authorIsEmpty);
        Assert.assertFalse(receiver.editionIsEmpty);
        Assert.assertFalse(receiver.publishingCompanyIsEmpty);
        Assert.assertTrue(receiver.ISBNFormatIsNotValid);
    }

    @Test
    public void publishingCompanyTitleShouldNotBeBlank() {
        Book book = new Book(receiver);
        book.setTitle("Title");
        book.setAuthor("Author");
        book.setEdition("1");
        book.setISBN("1111111111111");
        book.setPublishingCompany("");
        Assert.assertFalse(book.isValid());
        Assert.assertFalse(receiver.titleIsEmpty);
        Assert.assertFalse(receiver.authorIsEmpty);
        Assert.assertFalse(receiver.editionIsEmpty);
        Assert.assertTrue(receiver.publishingCompanyIsEmpty);
        Assert.assertFalse(receiver.ISBNFormatIsNotValid);
    }

    @Test
    public void bookIsValid_allFieldsFilled() {
        Book book = new Book(receiver);
        book.setTitle("Title");
        book.setAuthor("Author");
        book.setEdition("1");
        book.setISBN("1111111111111");
        book.setPublishingCompany("Publishing Company");
        Assert.assertTrue(book.isValid());
        Assert.assertFalse(receiver.titleIsEmpty);
        Assert.assertFalse(receiver.authorIsEmpty);
        Assert.assertFalse(receiver.editionIsEmpty);
        Assert.assertFalse(receiver.publishingCompanyIsEmpty);
        Assert.assertFalse(receiver.ISBNFormatIsNotValid);
    }

    @Test
    public void ISBNShouldNotHaveLessThan13Digits() {
        Book book = new Book(receiver);
        book.setTitle("Title");
        book.setAuthor("Author");
        book.setEdition("1");
        book.setISBN("111111111111");
        book.setPublishingCompany("Publishing Company");
        Assert.assertFalse(book.isValid());
        Assert.assertFalse(receiver.titleIsEmpty);
        Assert.assertFalse(receiver.authorIsEmpty);
        Assert.assertFalse(receiver.editionIsEmpty);
        Assert.assertFalse(receiver.publishingCompanyIsEmpty);
        Assert.assertTrue(receiver.ISBNFormatIsNotValid);
    }

    @Test
    public void ISBNShouldNotHaveMoreThan13Digits() {
        Book book = new Book(receiver);
        book.setTitle("Title");
        book.setAuthor("Author");
        book.setEdition("1");
        book.setISBN("11111111111111");
        book.setPublishingCompany("Publishing Company");
        Assert.assertFalse(book.isValid());
        Assert.assertFalse(receiver.titleIsEmpty);
        Assert.assertFalse(receiver.authorIsEmpty);
        Assert.assertFalse(receiver.editionIsEmpty);
        Assert.assertFalse(receiver.publishingCompanyIsEmpty);
        Assert.assertTrue(receiver.ISBNFormatIsNotValid);
    }

    @Test
    public void ISBNShouldNotHaveLettersExceptTheLastOne() {
        Book book = new Book(receiver);
        book.setTitle("Title");
        book.setAuthor("Author");
        book.setEdition("1");
        book.setISBN("a111111111111");
        book.setPublishingCompany("Publishing Company");
        Assert.assertFalse(book.isValid());
        Assert.assertFalse(receiver.titleIsEmpty);
        Assert.assertFalse(receiver.authorIsEmpty);
        Assert.assertFalse(receiver.editionIsEmpty);
        Assert.assertFalse(receiver.publishingCompanyIsEmpty);
        Assert.assertTrue(receiver.ISBNFormatIsNotValid);
    }

    @Test
    public void ISBNTheLastDigitCanBeTheLetterX() {
        Book book = new Book(receiver);
        book.setTitle("Title");
        book.setAuthor("Author");
        book.setEdition("1");
        book.setISBN("111111111111X");
        book.setPublishingCompany("Publishing Company");
        Assert.assertTrue(book.isValid());
        Assert.assertFalse(receiver.titleIsEmpty);
        Assert.assertFalse(receiver.authorIsEmpty);
        Assert.assertFalse(receiver.editionIsEmpty);
        Assert.assertFalse(receiver.publishingCompanyIsEmpty);
        Assert.assertFalse(receiver.ISBNFormatIsNotValid);
    }

    @Test
    public void ISBNTheLastDigitCannotBeALetterExceptX() {
        Book book = new Book(receiver);
        book.setTitle("Title");
        book.setAuthor("Author");
        book.setEdition("1");
        book.setISBN("111111111111A");
        book.setPublishingCompany("Publishing Company");
        Assert.assertFalse(book.isValid());
        Assert.assertFalse(receiver.titleIsEmpty);
        Assert.assertFalse(receiver.authorIsEmpty);
        Assert.assertFalse(receiver.editionIsEmpty);
        Assert.assertFalse(receiver.publishingCompanyIsEmpty);
        Assert.assertTrue(receiver.ISBNFormatIsNotValid);
    }
}
