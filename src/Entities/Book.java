package Entities;

import java.util.ArrayList;
import java.util.List;

public class Book {

    private String author;
    private String title;
    private ISBN ISBN;
    private String edition;
    private String publishingCompany;
    private List<BookCopy> bookCopies = new ArrayList<>();

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setISBN(String ISBN) {
        this.ISBN = new ISBN(ISBN);
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public void setPublishingCompany(String publishingCompany) {
        this.publishingCompany = publishingCompany;
    }

    public boolean isValid() {
        return !(title.isEmpty() || author.isEmpty() || edition.isEmpty() || !ISBN.isValid() || publishingCompany.isEmpty());
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return ISBN.toString();
    }

    public String getPublishingCompany() {
        return publishingCompany;
    }

    public String getEdition() {
        return edition;
    }

    public List<BookCopy> getBookCopies() {
        return bookCopies;
    }

    public class ISBN {

        private String code;

        public ISBN(String ISBN) {
            this.code = ISBN;
        }

        public String toString() {
            return this.code;
        }

        public boolean isValid() {
            return !code.isEmpty() && formatIsValid();
        }

        private boolean formatIsValid() {
            return code.length() == 13 && first12digitsAreOnlyNumber() && lastDigitIsANumberOrAnXLetter();
        }

        private boolean first12digitsAreOnlyNumber() {
            return code.substring(0,12).matches("[0-9]+");
        }

        private boolean lastDigitIsANumberOrAnXLetter() {
            return code.substring(12).matches("[0-9]+") || code.substring(12).matches("[Xx]");
        }
    }
}
