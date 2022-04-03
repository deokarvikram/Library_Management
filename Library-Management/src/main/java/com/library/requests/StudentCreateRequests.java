package com.library.requests;

import com.library.models.Student;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentCreateRequests {

    private String name;
    private String country;
    private String contact;
    private int age;
    private String email;
    private String password;


    public Student toStudent()
    {
        return Student.builder()
                .name(name)
                .contact(contact)
                .country(country)
                .age(age)
                .email(email)
                .password(password)
                .build();
    }
}
