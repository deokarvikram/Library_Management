package com.library.services;

import com.library.models.Admin;
import com.library.models.User;
import com.library.repositories.AdminRepository;
import com.library.repositories.UserRepository;
import com.library.requests.AdminCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Value("${app.security.admin_role}")
    String ADMIN_ROLE;

    public void saveAdmin(AdminCreateRequest adminCreateRequest)
    {
        Admin admin=Admin.builder()
                .email(adminCreateRequest.getEmail())
                .name(adminCreateRequest.getName())
                .build();

        adminRepository.save(admin);

        User user=User.builder()
                .username(admin.getEmail())
                .password(passwordEncoder.encode(adminCreateRequest.getPassword()))
                .authorities(ADMIN_ROLE)
                .build();

        userRepository.save(user);
    }

}
