package Entities;

import Receiver.Receiver;

import java.time.LocalDate;

/**
 * Created by 0143138 on 08/11/2016.
 */
public class BookCopy {

    private Receiver receiver;
    private String status;
    private String id;
    private String ISBN;
    private LocalDate returnDate;

    public BookCopy(Receiver receiver) {
        this.receiver = receiver;
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getISBN() {
        return ISBN;
    }

    public boolean isValid() {
        boolean idIsEmpty = false;
        boolean statusIsEmpty = false;
        if (id.isEmpty()) {
            receiver.logErrorIdCannotBeBlank();
            idIsEmpty = true;
        }
        if (status.isEmpty()) {
            receiver.logErrorStatusCannotBeBlank();
            statusIsEmpty = true;
        }
        return !idIsEmpty && !statusIsEmpty;
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
