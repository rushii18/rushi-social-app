package com.rushi.config;

import java.util.HashMap;
import java.util.Map;

import javax.swing.Spring;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.rushi.models.Message;
import com.rushi.request.KafkaConstants;

@Configuration
@EnableKafka
public class ConsumerConfiguration {

	ConcurrentKafkaListenerContainerFactory<String, Message> kafkaListenerContainerFactory() {

		ConcurrentKafkaListenerContainerFactory<String, Message> factory = new ConcurrentKafkaListenerContainerFactory<>();

		factory.setConsumerFactory(consumerFactory());

		return factory;
	}

	public ConsumerFactory<String, Message> consumerFactory() {

		return new DefaultKafkaConsumerFactory<>(consumerConfig(), new StringDeserializer(),
				new JsonDeserializer<>(Message.class));
	}

	public Map<String, Object> consumerConfig() {
		Map<String, Object> configurations = new HashMap<>();
		configurations.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.bootstrapServers);
		configurations.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaConstants.groupId);
		configurations.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configurations.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		configurations.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		return configurations;
	}

}
