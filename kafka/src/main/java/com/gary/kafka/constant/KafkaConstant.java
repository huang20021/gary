package com.gary.kafka.constant;

public class KafkaConstant {
	public static final String P_BOOTSTRAP_SERVERS = "bootstrap.servers";
	public static final String P_ACKS = "acks";  
	public static final String P_RETRIES = "retries";
	public static final String P_BATCH_SIZE = "batch.size";
	public static final String P_LINGER_MS = "linger.ms";
	public static final String P_BUFFER_MEMORY = "buffer.memory";
	public static final String P_KEY_SERIALIZER = "key.serializer";
	public static final String P_VALUE_SERIALIZER = "value.serializer";
	
	public static final String C_BOOTSTRAP_SERVERS = "bootstrap.servers";
	public static final String C_GROUP_ID = "group.id";
	public static final String C_ENABLE_AUTO_COMMIT = "enable.auto.commit";
	public static final String C_AUOT_COMMIT_INTERVAL_MS = "auto.commit.interval.ms";
	public static final String C_KEY_DESERIALIZER = "key.deserializer";
	public static final String C_VALUE_DESERIALIZER = "value.deserializer";
	
	public static final String STRING_SERIALIZER = "org.apache.kafka.common.serialization.StringSerializer";
	public static final String BYTE_ARRAY_SERIALIZAER = "org.apache.kafka.common.serialization.ByteArraySerializaer";
	
	public static final String STRING_DESERIALIZER = "org.apache.kafka.common.serialization.StringDeserializer";
}
