package Entities;

/**
 * Created by 0143138 on 08/11/2016.
 */
public class Book {

    private String author;
    private String title;
    private ISBN ISBN;
    private String edition;
    private String publishingCompany;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setISBN(String ISBN) {
        this.ISBN = new ISBN(ISBN);
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public void setPublishingCompany(String publishingCompany) {
        this.publishingCompany = publishingCompany;
    }

    public boolean isValid() {
        return !(title.isEmpty() || author.isEmpty() || edition.isEmpty() || !ISBN.isValid() || publishingCompany.isEmpty());
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return ISBN.toString();
    }

    public String getPublishingCompany() {
        return publishingCompany;
    }

    public String getEdition() {
        return edition;
    }

    public class ISBN {

        private String code;

        public ISBN(String ISBN) {
            this.code = ISBN;
        }

        public String toString() {
            return this.code;
        }

        public boolean isValid() {
            return !code.isEmpty() && formatIsValid();
        }

        private boolean formatIsValid() {
            return code.length() == 13;
        }
    }
}
