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
        boolean titleIsValid = false;
        boolean authorIsValid = false;
        boolean editionIsValid = false;
        boolean publishingCompanyIsValid = false;
        boolean ISBNIsValid = false;

        if (isTitleValid()) {
            titleIsValid = true;
        }
        if (isAuthorValid()) {
            authorIsValid = true;
        }
        if (isEditionValid()) {
            editionIsValid = true;
        }
        if (isPublishingCompanyValid()) {
            publishingCompanyIsValid = true;
        }
        if (ISBN.isValid()) {
            ISBNIsValid = true;
        }
        return titleIsValid && authorIsValid && editionIsValid && publishingCompanyIsValid && ISBNIsValid;
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

}
