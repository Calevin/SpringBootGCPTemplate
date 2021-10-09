package com.calevin.springbootgcptemplate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@SpringBootApplication
public class SpringbootGcpTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootGcpTemplateApplication.class, args);
	}

	@RestController
	class HelloWorldController {
		@Value("${greeting}")
		private String greeting;

		@GetMapping("/")
		public String hello() {
			log.info(" greeting: {}", greeting);
			return greeting;
		}
	}
}
