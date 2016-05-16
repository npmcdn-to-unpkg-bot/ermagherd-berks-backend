package me.totlol.ermagherdberks.controller;

import io.swagger.annotations.Api;
import me.totlol.ermagherdberks.entity.Book;
import me.totlol.ermagherdberks.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Márton Tóth
 */
@RestController
@RequestMapping("api/books")
@Api("Books")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Book> getBooks() {
        return bookService.findAllBooks();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Book getBook(@PathVariable("id") Long id) {
        return bookService.findBookById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Book createBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Book updateBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
    }

    @RequestMapping(value = "{id}/borrow", method = RequestMethod.POST)
    public void borrowBook(@PathVariable("id") Long id) {

    }

}
