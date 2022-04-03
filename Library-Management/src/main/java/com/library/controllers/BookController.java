package com.library.controllers;

import com.library.models.Book;
import com.library.requests.BookCreateRequests;
import com.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

    @Autowired
    BookService bookService;


    @PostMapping("/book")
    public void creatBook(@RequestBody BookCreateRequests bookCreateRequests)
    {
            bookService.addBook(bookCreateRequests);
    }

    @GetMapping("/book")
    public Book getBook(@RequestParam int id)
    {
        return bookService.getBookById(id);
    }
}
