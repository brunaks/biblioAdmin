package Receiver;

/**
 * Created by Bruna Koch Schmitt on 28/11/2016.
 */
public class SimpleReceiver implements Receiver {

    public boolean bookRegistrationWasSuccessful;

    @Override
    public void bookRegistrationSuccessful() {
        this.bookRegistrationWasSuccessful = true;
    }

    @Override
    public void bookRegistrationFailed() {
        this.bookRegistrationWasSuccessful = false;
    }
}
