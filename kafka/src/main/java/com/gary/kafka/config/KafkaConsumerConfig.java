package com.gary.kafka.config;

import java.util.Arrays;
import java.util.List;

import com.gary.kafka.constant.KafkaConstant;

public class KafkaConsumerConfig {
	/**
	 * 集群是通过配置bootstrap.servers指定一个或多个broker。
	 * 不用指定全部的broker，它将自动发现集群中的其余的borker（最好指定多个，万一有服务器故障）。
	 */
	private String bootstrapServers = "192.168.137.128:9092";
	private String groupId = "test";
	//设置enable.auto.commit,偏移量由auto.commit.interval.ms控制自动提交的频率
	private String enableAutoCommit = "true";
	private String autoCommitIntervalMs = "1000";
	private String keyDeserializer = KafkaConstant.STRING_DESERIALIZER;
	private String valueDeserializer = KafkaConstant.STRING_DESERIALIZER;
	private List<String> topis = Arrays.asList("foo", "bar", "my-topic");
	
	public String getBootstrapServers() {
		return bootstrapServers;
	}
	public void setBootstrapServers(String bootstrapServers) {
		this.bootstrapServers = bootstrapServers;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getEnableAutoCommit() {
		return enableAutoCommit;
	}
	public void setEnableAutoCommit(String enableAutoCommit) {
		this.enableAutoCommit = enableAutoCommit;
	}
	public String getAutoCommitIntervalMs() {
		return autoCommitIntervalMs;
	}
	public void setAutoCommitIntervalMs(String autoCommitIntervalMs) {
		this.autoCommitIntervalMs = autoCommitIntervalMs;
	}
	public String getKeyDeserializer() {
		return keyDeserializer;
	}
	public void setKeyDeserializer(String keyDeserializer) {
		this.keyDeserializer = keyDeserializer;
	}
	public String getValueDeserializer() {
		return valueDeserializer;
	}
	public void setValueDeserializer(String valueDeserializer) {
		this.valueDeserializer = valueDeserializer;
	}
	public List<String> getTopis() {
		return topis;
	}
	public void setTopis(List<String> topis) {
		this.topis = topis;
	} 
	
}
