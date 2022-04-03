package com.library.services;


import com.library.models.Student;
import com.library.models.User;
import com.library.repositories.StudentRepository;
import com.library.repositories.UserRepository;
import com.library.requests.StudentCreateRequests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${app.security.student_role}")
    String STUDENT_ROLE;

    @Transactional
    public void addStudent(StudentCreateRequests studentCreateRequests)
    {
        Student student=studentCreateRequests.toStudent();
        studentRepository.save(student);

        User user=User.builder()
                .username(studentCreateRequests.getEmail())
                .password(  passwordEncoder.encode(studentCreateRequests.getPassword()))
                .authorities(STUDENT_ROLE)
                .build();

        userRepository.save(user);
    }

    public Student getStudent(String id)
    {
        return studentRepository.findByEmail(id);
    }
}
