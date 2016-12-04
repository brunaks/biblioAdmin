package Entities;

import Receiver.Receiver;
import java.time.LocalDate;

/**
 * Created by 0143138 on 08/11/2016.
 */
public class BookCopy {

    private Receiver receiver;
    private Status status;
    private String id;
    private ISBN ISBN;
    private LocalDate returnDate;

    public BookCopy(Receiver receiver) {
        this.receiver = receiver;
    }

    public String getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setISBN(ISBN ISBN) {
        this.ISBN = ISBN;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ISBN getISBN() {
        return ISBN;
    }

    public boolean isValid() {
        boolean idIsEmpty = false;
        boolean statusIsEmpty = false;
        boolean ISBNIsNotValid = false;
        if (id.isEmpty()) {
            receiver.logErrorIdCannotBeBlank();
            idIsEmpty = true;
        }
        if (status == null) {
            receiver.logErrorStatusCannotBeBlank();
            statusIsEmpty = true;
        }
        if (!ISBN.isValid()) {
            ISBNIsNotValid = true;
        }
        return !(idIsEmpty || statusIsEmpty || ISBNIsNotValid);
    }

    public void setReturnDate(String returnDate) {
        if (returnDate.isEmpty()) {
            this.returnDate = null;
        } else {
            this.returnDate = LocalDate.of(Integer.parseInt(returnDate.substring(0,4)),
                                           Integer.parseInt(returnDate.substring(5,7)),
                                           Integer.parseInt(returnDate.substring(8)));
        }
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public enum Status {
        AVAILABLE, TAKEN
    }
}
