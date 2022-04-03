package com.library.requests;

import com.library.models.Book;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookCreateRequests {

    private String name;
    private int cost;
    private String publisher;

    private String authorName;
    private String country;
    private String email;

    public Book toBook()
    {
        return Book.builder().
                cost(cost)
                .name(name)
                .publisher(publisher)
                .isAvailable(true)               .
                build();

    }

}
