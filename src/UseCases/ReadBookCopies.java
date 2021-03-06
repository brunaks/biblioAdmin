package UseCases;

import DTOs.BookCopyInformation;
import Entities.Book;
import Entities.BookCopy;
import Persistence.BookRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReadBookCopies {

    private BookRepository bookRepository;

    public ReadBookCopies(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookCopyInformation> getAllFor(String ISBN) {
        Book book = bookRepository.getBookWith(ISBN);
        List<BookCopyInformation> bookCopies = new ArrayList<>();
        if (book != null) {
            for (BookCopy bookCopy : book.getBookCopies()) {
                BookCopyInformation bookCopyInformation = new BookCopyInformation();
                bookCopyInformation.isbn = book.getISBN();
                bookCopyInformation.id = bookCopy.getId();
                bookCopyInformation.status = bookCopy.getStatus().toString();
                bookCopyInformation.returnDate = getFormattedReturnDate(bookCopy.getReturnDate());
                bookCopies.add(bookCopyInformation);
            }
        }
        return bookCopies;
    }

    private String getFormattedReturnDate(LocalDate returnDate) {
        if (returnDate == null) {
            return "";
        } else {
            return returnDate.toString();
        }
    }
}

