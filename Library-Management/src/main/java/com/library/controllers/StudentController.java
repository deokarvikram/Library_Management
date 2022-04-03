package com.library.controllers;

import com.library.models.Student;
import com.library.models.User;
import com.library.requests.StudentCreateRequests;
import com.library.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/student")
    public void addStudent(@RequestBody StudentCreateRequests studentCreateRequests)
    {
        studentService.addStudent(studentCreateRequests);

    }

    //student
    @GetMapping("/student")
    public Student getStudent()
    {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

        User user=(User) authentication.getPrincipal();
        String studentId=user.getUsername();
      //  System.out.println(studentId);
        return studentService.getStudent(studentId);
    }


    //admin
    @GetMapping("/student/id")
    public Student getStudentById(@RequestParam String id)
    {
        return studentService.getStudent(id);
    }


}
