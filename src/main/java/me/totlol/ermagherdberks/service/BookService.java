package me.totlol.ermagherdberks.service;

import me.totlol.ermagherdberks.entity.Book;
import me.totlol.ermagherdberks.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Márton Tóth
 */
@Service
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(Long bookId) {
        bookRepository.delete(bookId);
    }

    public Book findBookById(Long bookId) {
        return bookRepository.findOne(bookId);
    }
}
