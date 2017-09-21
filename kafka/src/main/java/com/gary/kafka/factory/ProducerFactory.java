package com.gary.kafka.factory;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;

import com.gary.kafka.constant.KafkaConstant;

public class ProducerFactory {
	// 新的生产者是线程安全的，在线程之间共享单个生产者实例，通常单例比多个实例要快。
	private static Producer<String, String> producer;

	public static Producer<String, String> getProducer(Properties props) {
		if (producer == null) {
			producer = new KafkaProducer<>(props);
		}
		return producer;
	}

	public static Producer<String, String> getDefaultProducer() {
		if (producer == null) {
			Properties props = getDefaultProperties();
			producer = new KafkaProducer<>(props);
		}
		return producer;
	}

	public static Properties getDefaultProperties() {
		Properties props = new Properties();
		props.put(KafkaConstant.C_BOOTSTRAP_SERVERS, "192.168.137.128:9092");
		//不能连zookeeper
		//props.put(KafkaConstant.C_BOOTSTRAP_SERVERS, "192.168.137.128:12181");
		props.put(KafkaConstant.P_ACKS, "all");
		props.put(KafkaConstant.P_RETRIES, 0);
		props.put(KafkaConstant.P_BATCH_SIZE, 16384);
		props.put(KafkaConstant.P_LINGER_MS, 1);
		props.put(KafkaConstant.P_BUFFER_MEMORY, 33554432);
		props.put(KafkaConstant.P_KEY_SERIALIZER, KafkaConstant.STRING_SERIALIZER);
		props.put(KafkaConstant.P_VALUE_SERIALIZER, KafkaConstant.STRING_SERIALIZER);
		return props;
	}
}
