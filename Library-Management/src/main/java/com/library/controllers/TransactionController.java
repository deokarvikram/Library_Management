package com.library.controllers;

import com.library.models.User;
import com.library.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/issue_book")
    public String issueBook(@RequestParam("bookid") int bookid) throws Exception {

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();;
        User user=(User) authentication.getPrincipal();
        String studentid= user.getUsername();

        return  transactionService.issueBook(bookid,studentid);
    }

//    @PostMapping("/return_book")
//    public String returnBook(@RequestParam("bookid") int bookid, @RequestParam("studentid") int studentid) throws Exception {
//            return transactionService.returnBook(bookid,studentid);
//    }
}
