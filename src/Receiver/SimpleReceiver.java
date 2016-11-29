package Receiver;

/**
 * Created by Bruna Koch Schmitt on 28/11/2016.
 */
public class SimpleReceiver implements Receiver {

    public boolean bookRegistrationWasSuccessful = false;
    public boolean bookDeletionWasSuccessful = false;
    public boolean titleIsEmpty = false;
    public boolean authorIsEmpty = false;
    public boolean editionIsEmpty = false;
    public boolean publishingCompanyIsEmpty = false;
    public boolean ISBNFormatIsNotValid = false;
    private boolean idCannotBeBlank = false;
    private boolean statusCannotBeBlank = false;

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
        idCannotBeBlank = true;
    }

    @Override
    public void logErrorStatusCannotBeBlank() {
        statusCannotBeBlank = true;
    }
}
