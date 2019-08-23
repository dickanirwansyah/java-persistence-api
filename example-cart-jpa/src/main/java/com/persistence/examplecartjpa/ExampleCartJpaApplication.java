package com.persistence.examplecartjpa;

import com.persistence.examplecartjpa.entity.Category;
import com.persistence.examplecartjpa.entity.Product;
import com.persistence.examplecartjpa.repository.CategoryRepository;
import com.persistence.examplecartjpa.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.Date;

@SpringBootApplication
public class ExampleCartJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExampleCartJpaApplication.class, args);
	}

}


//@Component
//class CommandData implements CommandLineRunner {
//
//	@Autowired
//	private CategoryRepository categoryRepository;
//
//	@Autowired
//	private ProductRepository productRepository;
//
//	@Override
//	public void run(String... args) throws Exception {
//		categoryRepository.save(Category.builder()
//		.name("phone").createdAt(new Date()).updatedAt(new Date()).build());
//
//		Category category = categoryRepository.findById(1);
//		productRepository.save(Product.builder()
//		.createdAt(new Date()).updatedAt(new Date())
//				.category(category).name("iphone").stock(1).price(9000l).build());
//	}
//}