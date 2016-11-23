package DTOs;

/**
 * Created by 0143138 on 08/11/2016.
 */
public class BookInformation {
    private String author;
    private String title;
    private String ISBN;
    private String edition;
    private String publishingCompany;

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public void setPublishingCompany(String publishingCompany) {
        this.publishingCompany = publishingCompany;
    }
}
