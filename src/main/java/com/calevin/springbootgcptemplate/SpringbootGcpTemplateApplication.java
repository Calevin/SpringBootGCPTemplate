package com.calevin.springbootgcptemplate;

import lombok.extern.slf4j.Slf4j;
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
		@GetMapping("/")
		public String hello() {
			log.trace("A TRACE Message");
			log.debug("A DEBUG Message");
			log.info("An INFO Message");
			log.warn("A WARN Message");
			log.error("An ERROR Message");
			return "hello world!";
		}
	}
}
