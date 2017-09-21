package com.gary.kafka.factory;

import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import com.gary.kafka.constant.KafkaConstant;

public class ConsumerFactory {
	// 消费者TCP长连接到broker来拉取消息。故障导致的消费者关闭失败，将会泄露这些连接，消费者不是线程安全的
	// private static Consumer<String, String> consumer;

	public static Consumer<String, String> getConsumer(Properties props) {
		Consumer<String, String> consumer = new KafkaConsumer<>(props);
		return consumer;
	}

	public static Consumer<String, String> getDefaultConsumer() {
		Properties props = getDefaultProperties();
		Consumer<String, String> consumer = new KafkaConsumer<>(props);
		return consumer;
	}

	public static Properties getDefaultProperties() {
		Properties props = new Properties();
		props.put(KafkaConstant.C_BOOTSTRAP_SERVERS, "192.168.137.128:9092");
		props.put(KafkaConstant.C_GROUP_ID, "test");
		props.put(KafkaConstant.C_ENABLE_AUTO_COMMIT, "true");
		props.put(KafkaConstant.C_AUOT_COMMIT_INTERVAL_MS, "1000");
		props.put(KafkaConstant.C_KEY_DESERIALIZER, KafkaConstant.STRING_DESERIALIZER);
		props.put(KafkaConstant.C_VALUE_DESERIALIZER, KafkaConstant.STRING_DESERIALIZER);
		return props;
	}
	
	public static Properties getDefaultManualProperties() {
		Properties props = new Properties();
		props.put(KafkaConstant.C_BOOTSTRAP_SERVERS, "192.168.137.128:9092");
		props.put(KafkaConstant.C_GROUP_ID, "test");
		props.put(KafkaConstant.C_ENABLE_AUTO_COMMIT, "false");
		props.put(KafkaConstant.C_AUOT_COMMIT_INTERVAL_MS, "1000");
		props.put("session.timeout.ms", "30000");
		props.put(KafkaConstant.C_KEY_DESERIALIZER, KafkaConstant.STRING_DESERIALIZER);
		props.put(KafkaConstant.C_VALUE_DESERIALIZER, KafkaConstant.STRING_DESERIALIZER);
		return props;
	}
}
