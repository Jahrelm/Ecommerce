package com.example.ecommercemanagement;

import com.example.ecommercemanagement.model.ApplicationUser;
import com.example.ecommercemanagement.model.Product;
import com.example.ecommercemanagement.model.Role;
import com.example.ecommercemanagement.repository.RoleRepository;
import com.example.ecommercemanagement.repository.UserRepository;
import com.example.ecommercemanagement.service.ProductService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@SpringBootApplication
@EntityScan(basePackages = "com.example.ecommercemanagement.model")
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
	@Bean
	CommandLineRunner runner(ProductService productService) {
		return args -> {
			// read json and write to db
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Product>> typeReference = new TypeReference<List<Product>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/products.json");
			try {
				List<Product> products = mapper.readValue(inputStream,typeReference);
				productService.save(products);
				System.out.println("Products Saved!");
			} catch (IOException e){
				System.out.println("Unable to save products: " + e.getMessage());
			}
		};
	}
}
