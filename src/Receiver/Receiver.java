package Receiver;

/**
 * Created by Bruna Koch Schmitt on 28/11/2016.
 */
public interface Receiver {
    void bookRegistrationSuccessful();
    void bookRegistrationFailed();
    void bookDeletionWasSuccessful();
    void bookDeletionFailed();
    void logErrorTitleIsEmpty();
    void logErrorAuthorIsEmpty();
    void logErrorEditionIsEmpty();
    void logErrorPublishingCompanyIsEmpty();
    void logErrorISBNMustbeNumeric();
    void logErrorISBNLastDigitMustBeNumericOrX();
    void logErrorISBNLengthIsIncorrect();
    void logErrorIdCannotBeBlank();
    void logErrorStatusCannotBeBlank();
    void bookCopyRegistrationSuccessful();
    void bookCopyRegistrationFailed();
    void refresh();
    void bookCopyDeletionWasSuccessful();
}
