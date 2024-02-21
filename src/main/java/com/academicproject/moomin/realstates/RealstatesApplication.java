package com.academicproject.moomin.realstates;

import com.academicproject.moomin.realstates.entity.dtos.kafkaDto.EmailObject;
import com.academicproject.moomin.realstates.service.impl.ImgurService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.TopicPartitionOffset;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RealstatesApplication {

	public static void main(String[] args) {
		System.setProperty("javax.net.ssl.checkServerIdentity", "false");
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

	@Autowired
	private KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory;

//	@Bean
//	public ConcurrentMessageListenerContainer<String, String> createContainer(ConsumerFactory<String, String> consumerFactory) {
//		ConcurrentMessageListenerContainer<String, String> container = kafkaListenerContainerFactory.createContainer((TopicPartitionOffset) consumerFactory);
//		container.getContainerProperties().setGroupId("my-group-id"); // Set your consumer group ID
//		container.addTopics("my-topic"); // Specify the topic to consume from
//		return container;
//	}

	@KafkaListener(topics = "topic", groupId = "my-group-id") // Matches container configuration
	public void consume(String message) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		EmailObject emailObject = mapper.readValue(message, EmailObject.class);
		System.out.println("Received message: " + emailObject.getEmail()+" "+ emailObject.getDescription());
	}


}
