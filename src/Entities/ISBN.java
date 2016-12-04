package Entities;

import Receiver.Receiver;

/**
 * Created by Bruna Koch Schmitt on 04/12/2016.
 */
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
