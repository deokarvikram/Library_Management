package com.library.services;

import com.library.models.Author;
import com.library.models.Book;
import com.library.repositories.BookRepository;
import com.library.requests.BookCreateRequests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorService authorService;

    public void addBook(BookCreateRequests createRequests)
    {
        Book book = createRequests.toBook();

        Author author= authorService.getAuthorByEmail(createRequests.getEmail());

        if(author==null)
        {
            author = Author.builder()
                    .email(createRequests.getEmail())
                    .name(createRequests.getAuthorName())
                    .country(createRequests.getCountry())
                    .build();

           authorService.addAuthor(author);
        }
        book.setAuthor(author);

        bookRepository.save(book);
    }

    public Book getBookById(int id)
    {
        return bookRepository.findById(id).orElse(null);
    }

    public void addOrUpdate(Book book)
    {
        bookRepository.save(book);
    }
}
