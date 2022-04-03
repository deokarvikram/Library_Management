package com.library;

import com.library.models.Admin;
import com.library.requests.AdminCreateRequest;
import com.library.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryManagementApplication implements CommandLineRunner {

	@Autowired
	AdminService adminService;

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
//		AdminCreateRequest admin= AdminCreateRequest.builder()
//				.name("Vikram")
//				.email("vik@gmail.com")
//				.password("123")
//				.build();
//
//			adminService.saveAdmin(admin);
	}
}
