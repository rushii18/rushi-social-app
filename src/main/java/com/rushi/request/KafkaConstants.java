package com.rushi.request;

import org.springframework.beans.factory.annotation.Value;

public class KafkaConstants {

	public static String KAFKA_TOPIC = "message-topic";
	public static String KAFKA_TOPIC2 = "message-topic2";

	 @Value("${spring.kafka.bootstrap-servers}")
	    public static String bootstrapServers;

	    @Value("${spring.kafka.consumer.group-id}")
	    public static String groupId;

}
