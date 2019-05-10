package com.prs;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.prs.business.User;
import com.prs.db.UserDB;

@SpringBootApplication
public class PrsJpaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrsJpaDemoApplication.class, args);
		System.out.println("Hello");
		
		List<User> users = UserDB.getAll();
		
		if (users != null) {
			for (User u: users) {
				System.out.println(u);
			}
		} else {
			System.out.println("User null");
		}
		
		System.out.println("Goodbye");
	}

}
