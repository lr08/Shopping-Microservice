package com.example.demo.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class AppConfig {

	@Bean
	public NewTopic myTopic() {
		return TopicBuilder.name(AppConstants.TOPIC_NAME).build();
	}
}
