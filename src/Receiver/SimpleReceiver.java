package Receiver;

/**
 * Created by Bruna Koch Schmitt on 28/11/2016.
 */
public class SimpleReceiver implements Receiver {

    public boolean bookRegistrationWasSuccessful;
    public boolean bookDeletionWasSuccessful;

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
}
