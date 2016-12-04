package Receiver;

/**
 * Created by Bruna Koch Schmitt on 28/11/2016.
 */
public class SimpleReceiver implements Receiver {

    public boolean bookRegistrationWasSuccessful = false;
    public boolean bookDeletionWasSuccessful = false;
    public boolean bookCopyRegistrationWasSuccessful = false;
    public boolean titleIsEmpty = false;
    public boolean authorIsEmpty = false;
    public boolean editionIsEmpty = false;
    public boolean publishingCompanyIsEmpty = false;
    public boolean ISBNFormatIsNotValid = false;
    public boolean idIsEmpty = false;
    public boolean statusIsEmpty = false;
    public boolean bookCopyDeletionWasSuccessful = false;

    @Override
    public void bookRegistrationSuccessful() {
        this.bookRegistrationWasSuccessful = true;
    }

    @Override
    public void bookRegistrationFailed() {
        this.bookRegistrationWasSuccessful = false;
    }

    @Override
    public void bookDeletionWasSuccessful() {
        bookDeletionWasSuccessful = true;
    }

    @Override
    public void bookDeletionFailed() {
        bookDeletionWasSuccessful = false;
    }

    @Override
    public void logErrorTitleIsEmpty() {
        titleIsEmpty = true;
    }

    @Override
    public void logErrorAuthorIsEmpty() {
        authorIsEmpty = true;
    }

    @Override
    public void logErrorEditionIsEmpty() {
        editionIsEmpty = true;
    }

    @Override
    public void logErrorPublishingCompanyIsEmpty() {
        publishingCompanyIsEmpty = true;
    }

    @Override
    public void logErrorISBNMustbeNumeric() {
        ISBNFormatIsNotValid = true;
    }

    @Override
    public void logErrorISBNLastDigitMustBeNumericOrX() {
        ISBNFormatIsNotValid = true;
    }

    @Override
    public void logErrorISBNLengthIsIncorrect() {
        ISBNFormatIsNotValid = true;
    }

    @Override
    public void logErrorIdCannotBeBlank() {
        idIsEmpty = true;
    }

    @Override
    public void logErrorStatusCannotBeBlank() {
        statusIsEmpty = true;
    }

    @Override
    public void bookCopyRegistrationSuccessful() {
        this.bookCopyRegistrationWasSuccessful = true;
    }

    @Override
    public void bookCopyRegistrationFailed() {
        this.bookCopyRegistrationWasSuccessful = false;
    }

    @Override
    public void refresh() {
        bookRegistrationWasSuccessful = false;
        bookDeletionWasSuccessful = false;
        bookCopyRegistrationWasSuccessful = false;
        titleIsEmpty = false;
        authorIsEmpty = false;
        editionIsEmpty = false;
        publishingCompanyIsEmpty = false;
        ISBNFormatIsNotValid = false;
        idIsEmpty = false;
        statusIsEmpty = false;
    }

    @Override
    public void bookCopyDeletionWasSuccessful() {
        this.bookCopyDeletionWasSuccessful = true;
    }
}
