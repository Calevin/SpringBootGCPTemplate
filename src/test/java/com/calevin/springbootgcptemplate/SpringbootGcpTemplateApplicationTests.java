package com.calevin.springbootgcptemplate;

import static org.assertj.core.api.Assertions.assertThat;


import com.calevin.springbootgcptemplate.controllers.EntityExampleController;
import com.calevin.springbootgcptemplate.repositories.CategoryExampleRepository;
import com.calevin.springbootgcptemplate.repositories.EntityExampleRepository;
import com.calevin.springbootgcptemplate.services.EntityExampleService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootGcpTemplateApplicationTests {

	@Autowired
	public SpringbootGcpTemplateApplicationTests(EntityExampleController entityExampleController
			, EntityExampleRepository entityExampleRepository
			, CategoryExampleRepository categoryExampleRepository
			, EntityExampleService entityExampleService) {

		this.entityExampleController = entityExampleController;
		this.entityExampleRepository = entityExampleRepository;
		this.categoryExampleRepository = categoryExampleRepository;
		this.entityExampleService = entityExampleService;
	}

	private EntityExampleController entityExampleController;

	private EntityExampleRepository entityExampleRepository;

	private CategoryExampleRepository categoryExampleRepository;

	private EntityExampleService entityExampleService;

	@Test
	void contextLoads() {
		assertThat(entityExampleController).isNotNull();
		assertThat(entityExampleRepository).isNotNull();
		assertThat(categoryExampleRepository).isNotNull();
		assertThat(entityExampleService).isNotNull();
	}

}
