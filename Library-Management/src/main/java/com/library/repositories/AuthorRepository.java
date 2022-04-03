package com.library.repositories;

import com.library.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends JpaRepository<Author,Integer> {

    @Query("from Author where email =:email")
    public Author findByEmail(String email);
}
