package com.academicproject.moomin.realstates;

import com.academicproject.moomin.realstates.service.impl.ImgurService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RealstatesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealstatesApplication.class, args);
	}
	@Bean
	public ImgurService imgurService() {
		return new ImgurService();
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
