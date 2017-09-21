package com.gary.kafka.producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import com.gary.kafka.factory.ProducerFactory;

public class ProducerApp {

	public static void main(String[] args) {	
		Producer<String, String> producer = ProducerFactory.getDefaultProducer();
		for (int i = 0; i < 3; i++){
			ProducerRecord<String, String> pr = new ProducerRecord<String, String>("my-topic", Integer.toString(i), Integer.toString(i));
			producer.send(pr, new Callback() {
                public void onCompletion(RecordMetadata metadata, Exception e) {
                    if(e != null)
                        e.printStackTrace();
                    System.out.println(Thread.currentThread().getName() + ": The offset of the record we just sent is: " + metadata.offset());
                }
            });
		}

		producer.close();
	}

}
