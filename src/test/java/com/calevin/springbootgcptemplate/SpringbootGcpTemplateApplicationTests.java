package com.calevin.springbootgcptemplate;

import static org.assertj.core.api.Assertions.assertThat;


import com.calevin.springbootgcptemplate.controllers.AuthenticationController;
import com.calevin.springbootgcptemplate.controllers.EntityExampleController;
import com.calevin.springbootgcptemplate.controllers.UserController;
import com.calevin.springbootgcptemplate.repositories.CategoryExampleRepository;
import com.calevin.springbootgcptemplate.repositories.EntityExampleRepository;
import com.calevin.springbootgcptemplate.services.EntityExampleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
class SpringbootGcpTemplateApplicationTests {

	private final EntityExampleController entityExampleController;
	private final EntityExampleRepository entityExampleRepository;
	private final CategoryExampleRepository categoryExampleRepository;
	private final EntityExampleService entityExampleService;
	private final AuthenticationController authenticationController;
	private final UserController userController;

	@Autowired
	SpringbootGcpTemplateApplicationTests(EntityExampleController entityExampleController
			, EntityExampleRepository entityExampleRepository
			, CategoryExampleRepository categoryExampleRepository
			, EntityExampleService entityExampleService
			, AuthenticationController authenticationController
			, UserController userController) {

		this.entityExampleController = entityExampleController;
		this.entityExampleRepository = entityExampleRepository;
		this.categoryExampleRepository = categoryExampleRepository;
		this.entityExampleService = entityExampleService;
		this.authenticationController = authenticationController;
		this.userController = userController;
	}

	@Test
	void contextLoads() {
		assertThat(entityExampleController).isNotNull();
		assertThat(entityExampleRepository).isNotNull();
		assertThat(categoryExampleRepository).isNotNull();
		assertThat(entityExampleService).isNotNull();
		assertThat(authenticationController).isNotNull();
		assertThat(userController).isNotNull();
	}

}
