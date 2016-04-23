package me.totlol.ermagherdberks.controller;

import me.totlol.ermagherdberks.entity.Book;
import me.totlol.ermagherdberks.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Márton Tóth
 */
@RestController
@RequestMapping("books")
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

}
