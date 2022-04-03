package com.library.services;

import com.library.models.*;
import com.library.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {

    @Autowired
    BookService bookService;

    @Autowired
    StudentService studentService;

    @Autowired
    TransactionRepository transactionRepository;

    @Transactional
    public String issueBook(int bookid,String studentid) throws Exception {
        Book book = bookService.getBookById(bookid);
        Student student = studentService.getStudent(studentid);

        if(book==null || !book.isAvailable())
        {
            return "Book is not available or book is issued to anyone";
        }

        Transaction transaction = Transaction.builder()
                                  .transactionId(UUID.randomUUID().toString())
                                  .transactionStatus(TransactionStatus.PENDING)
                                  .transactionType(TransactionType.ISSUE)
                                    .student(student)
                                  .book(book)
                                .build();

        try{
                transactionRepository.save(transaction);
                book.setStudent(student);
                book.setAvailable(false);
                book.setIssuedDate(new Date());
                bookService.addOrUpdate(book);
                transaction.setTransactionStatus(TransactionStatus.SUCCESS);
                transactionRepository.save(transaction);

        }
        catch(Exception ex)
        {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            book.setStudent(null);
            book.setAvailable(true);
            book.setIssuedDate(null);
            bookService.addOrUpdate(book);
            transactionRepository.save(transaction);
             throw new Exception("Transaction "+transaction.getTransactionId()+" has failed");
        }

        return "Book has been issued";

    }


    public int getFine(Book book)
    {
        long milli = new Date().getTime() - book.getIssuedDate().getTime();
        long period = TimeUnit.DAYS.convert(milli,TimeUnit.MILLISECONDS);

        int days =(int) period - 4;

        return  days<=0?0:days*10;
    }

//    @Transactional
//    public String returnBook(int bookid, String studentid) throws Exception {
//        Book book=bookService.getBookById(bookid);
//
//        if(book==null || book.isAvailable())
//            return "Book is not found or book is not issued to anyone";
//
//        Student student = studentService.getStudent(studentid);
//
//        if(student==null || studentid!=book.getStudent().getId())
//            return "Student is not found or this book is not issued to this student";
//
//        Transaction transaction = Transaction.builder()
//                .transactionId(UUID.randomUUID().toString())
//                .transactionStatus(TransactionStatus.PENDING)
//                .transactionType(TransactionType.RETURN)
//                .student(student)
//                .book(book)
//                .fine(getFine(book))
//                .build();
//
//        Date date=book.getIssuedDate();
//        try
//        {
//
//            transactionRepository.save(transaction);
//            book.setStudent(null);
//            book.setAvailable(true);
//            book.setIssuedDate(null);
//            bookService.addOrUpdate(book);
//
//            transaction.setTransactionStatus(TransactionStatus.SUCCESS);
//            transactionRepository.save(transaction);
//        }catch(Exception ex)
//        {
//            transaction.setTransactionStatus(TransactionStatus.FAILED);
//            book.setStudent(student);
//            book.setAvailable(false);
//            book.setIssuedDate(date);
//            bookService.addOrUpdate(book);
//            transaction.setFine(0);
//            transactionRepository.save(transaction);
//
//            throw new Exception("Transaction "+transaction.getTransactionId()+" has failed");
//        }
//
//        return "Book has been returned";
//    }
}
