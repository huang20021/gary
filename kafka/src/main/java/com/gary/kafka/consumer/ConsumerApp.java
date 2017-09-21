package com.gary.kafka.consumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import com.gary.kafka.factory.ConsumerFactory;

public class ConsumerApp {
	
	public static void main(String[] args) {
//		autoCommit();
		
	}
	
	/**
	 * 手动控制偏移量
	 * @param args
	 */
	public static void manualCommit() {
		Properties prod = ConsumerFactory.getDefaultManualProperties();
		Consumer<String, String> consumer = ConsumerFactory.getConsumer(prod);
		consumer.subscribe(Arrays.asList("foo", "bar", "my-topic"));
		final int minBatchSize = 10;
	     List<ConsumerRecord<String, String>> buffer = new ArrayList<>();
	     while (true) {
	         ConsumerRecords<String, String> records = consumer.poll(100);
	         for (ConsumerRecord<String, String> record : records) {
	             buffer.add(record);
	         }
	         if (buffer.size() >= minBatchSize) {
	        	 
	             //insertIntoDb(buffer);
	        	 try {
	        		 //模拟插入数据库
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	        	 
	             consumer.commitSync();
	             buffer.clear();
	         }
	     }
	}
	
	/**
	 * 自动提交偏移量
	 * @param args
	 */
	public static void autoCommit() {
		Consumer<String, String> consumer = ConsumerFactory.getDefaultConsumer();
		consumer.subscribe(Arrays.asList("foo", "bar", "my-topic"));
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			for (ConsumerRecord<String, String> record : records)
				System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
		}
	}

}
