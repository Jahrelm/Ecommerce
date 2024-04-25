package com.example.ecommercemanagement;

import com.example.ecommercemanagement.model.ApplicationUser;
import com.example.ecommercemanagement.model.Role;
import com.example.ecommercemanagement.repository.RoleRepository;
import com.example.ecommercemanagement.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class EcommerceManagementApplication {

	public static void main(String[] args) {

		SpringApplication.run(EcommerceManagementApplication.class, args);
	}
	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncode){
		return args -> {
			if(roleRepository.findByAuthority("ADMIN").isPresent()) return;
			Role adminRole = roleRepository.save(new Role("ADMIN"));
			roleRepository.save(new Role("USER"));

			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);

			ApplicationUser admin = new ApplicationUser(1, "admin", passwordEncode.encode("password"), roles
			, "Admin Full Name", "1234567890","Admin Country","Admin Address","Admin City","12345");

			userRepository.save(admin);
		};
	}
}
