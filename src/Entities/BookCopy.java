package Entities;

/**
 * Created by 0143138 on 08/11/2016.
 */
public class BookCopy {

    private String status;
    private String id;
    private String ISBN;

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
        return !id.isEmpty() && !status.isEmpty();
    }

    public enum Status {
        AVAILABLE, TAKEN
    }
}
