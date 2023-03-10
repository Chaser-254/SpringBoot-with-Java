package com.example.LoginPage;

import com.example.LoginPage.models.Role;
import com.example.LoginPage.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@SpringBootApplication
public class LoginPageApplication {

	public static void main(String[] args) {

		SpringApplication.run(LoginPageApplication.class, args);
	}
	@Bean
	CommandLineRunner init(RoleRepository roleRepository){

		return args -> {
			Role adminRole = roleRepository.findByRole("Role");
			if (adminRole == null){
			Role newAdminRole = new Role();
			newAdminRole.setRole("ADMIN");
			roleRepository.save(newAdminRole);
			}
			Role userRole = roleRepository.findByRole("USER");
			if (userRole == null){
				Role newUserRole = new Role();
				newUserRole.setRole("USER");
				roleRepository.save(newUserRole);
			}
		};
	}
}
