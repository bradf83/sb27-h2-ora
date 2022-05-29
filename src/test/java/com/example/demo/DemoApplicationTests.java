package com.example.demo;

import com.example.demo.repository.ProductRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private ProductRepository productRepository;

	@Test
	void failsToLookupPageOfData() {
		this.productRepository.findAll(PageRequest.ofSize(10));
	}

}
