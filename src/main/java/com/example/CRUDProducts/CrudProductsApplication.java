package com.example.CRUDProducts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class CrudProductsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudProductsApplication.class, args);
	}

}
