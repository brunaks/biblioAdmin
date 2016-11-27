import Entities.Book;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Bruna Koch Schmitt on 27/11/2016.
 */
public class BookTest {

    @Test
    public void bookTitleShouldNotBeBlank() {
        Book book = new Book();
        book.setTitle("");
        book.setAuthor("Author");
        book.setEdition("1");
        book.setISBN("1111111111111");
        book.setPublishingCompany("Publishing Company");
        Assert.assertFalse(book.isValid());
    }

    @Test
    public void authorTitleShouldNotBeBlank() {
        Book book = new Book();
        book.setTitle("Title");
        book.setAuthor("");
        book.setEdition("1");
        book.setISBN("1111111111111");
        book.setPublishingCompany("Publishing Company");
        Assert.assertFalse(book.isValid());
    }

    @Test
    public void editionTitleShouldNotBeBlank() {
        Book book = new Book();
        book.setTitle("Title");
        book.setAuthor("Author");
        book.setEdition("");
        book.setISBN("1111111111111");
        book.setPublishingCompany("Publishing Company");
        Assert.assertFalse(book.isValid());
    }

    @Test
    public void ISBNTitleShouldNotBeBlank() {
        Book book = new Book();
        book.setTitle("Title");
        book.setAuthor("Author");
        book.setEdition("1");
        book.setISBN("");
        book.setPublishingCompany("Publishing Company");
        Assert.assertFalse(book.isValid());
    }

    @Test
    public void publishingCompanyTitleShouldNotBeBlank() {
        Book book = new Book();
        book.setTitle("Title");
        book.setAuthor("Author");
        book.setEdition("1");
        book.setISBN("1111111111111");
        book.setPublishingCompany("");
        Assert.assertFalse(book.isValid());
    }

    @Test
    public void bookIsValid_allFieldsFilled() {
        Book book = new Book();
        book.setTitle("Title");
        book.setAuthor("Author");
        book.setEdition("1");
        book.setISBN("1111111111111");
        book.setPublishingCompany("Publishing Company");
        Assert.assertTrue(book.isValid());
    }

    @Test
    public void ISBNShouldNotHaveLessThan13Digits() {
        Book book = new Book();
        book.setTitle("Title");
        book.setAuthor("Author");
        book.setEdition("1");
        book.setISBN("111111111111");
        book.setPublishingCompany("Publishing Company");
        Assert.assertFalse(book.isValid());
    }

    @Test
    public void ISBNShouldNotHaveMoreThan13Digits() {
        Book book = new Book();
        book.setTitle("Title");
        book.setAuthor("Author");
        book.setEdition("1");
        book.setISBN("11111111111111");
        book.setPublishingCompany("Publishing Company");
        Assert.assertFalse(book.isValid());
    }

    @Test
    public void ISBNShouldNotHaveLettersExceptTheLastOne() {
        Book book = new Book();
        book.setTitle("Title");
        book.setAuthor("Author");
        book.setEdition("1");
        book.setISBN("a111111111111");
        book.setPublishingCompany("Publishing Company");
        Assert.assertFalse(book.isValid());
    }

    @Test
    public void ISBNTheLastDigitCanBeTheLetterX() {
        Book book = new Book();
        book.setTitle("Title");
        book.setAuthor("Author");
        book.setEdition("1");
        book.setISBN("111111111111X");
        book.setPublishingCompany("Publishing Company");
        Assert.assertTrue(book.isValid());
    }

    @Test
    public void ISBNTheLastDigitCannotBeALetterExceptX() {
        Book book = new Book();
        book.setTitle("Title");
        book.setAuthor("Author");
        book.setEdition("1");
        book.setISBN("111111111111A");
        book.setPublishingCompany("Publishing Company");
        Assert.assertFalse(book.isValid());
    }
}
