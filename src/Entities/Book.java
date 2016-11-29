package Entities;

import Receiver.Receiver;

import java.util.ArrayList;
import java.util.List;

public class Book {

    private Receiver receiver;
    private String author;
    private String title;
    private ISBN ISBN;
    private String edition;
    private String publishingCompany;
    private List<BookCopy> bookCopies = new ArrayList<>();

    public Book(Receiver receiver) {
        this.receiver = receiver;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setISBN(String ISBN) {
        this.ISBN = new ISBN(ISBN, receiver);
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public void setPublishingCompany(String publishingCompany) {
        this.publishingCompany = publishingCompany;
    }

    public boolean isValid() {
        return isTitleValid() && isAuthorValid() && isEditionValid() && isPublishingCompanyValid() && ISBN.isValid();
    }

    public boolean isTitleValid() {
        if (title.isEmpty()) {
            receiver.logErrorTitleIsEmpty();
            return false;
        } else {
            return true;
        }
    }

    public boolean isAuthorValid() {
        if (author.isEmpty()) {
            receiver.logErrorAuthorIsEmpty();
            return false;
        } else {
            return true;
        }
    }

    public boolean isEditionValid() {
        if (edition.isEmpty()) {
            receiver.logErrorEditionIsEmpty();
            return false;
        } else {
            return true;
        }
    }

    public boolean isPublishingCompanyValid() {
        if (publishingCompany.isEmpty()) {
            receiver.logErrorPublishingCompanyIsEmpty();
            return false;
        } else {
            return true;
        }
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
        private Receiver receiver;

        public ISBN(String ISBN, Receiver receiver) {
            this.code = ISBN;
            this.receiver = receiver;
        }

        public String toString() {
            return this.code;
        }

        public boolean isValid() {
            return formatIsValid();
        }

        private boolean formatIsValid() {
            return lengthIsValid() && first12digitsAreOnlyNumber() && lastDigitIsANumberOrAnXLetter();
        }

        public boolean lengthIsValid() {
            if (!code.isEmpty() && code.length() == 13) {
                return true;
            } else {
                receiver.logErrorISBNLengthIsIncorrect();
                return false;
            }
        }

        private boolean first12digitsAreOnlyNumber() {
            if (code.substring(0,12).matches("[0-9]+")) {
                return true;
            } else {
                receiver.logErrorISBNMustbeNumeric();
                return false;
            }
        }

        private boolean lastDigitIsANumberOrAnXLetter() {
            if (code.substring(12).matches("[0-9]+") || code.substring(12).matches("[Xx]")) {
                return true;
            } else {
                receiver.logErrorISBNLastDigitMustBeNumericOrX();
                return false;
            }
        }
    }
}
