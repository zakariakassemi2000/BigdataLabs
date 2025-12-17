package edu.supmti.kafka;

import java.time.Duration;
import java.util.*;
import org.apache.kafka.clients.consumer.*;

public class EventConsumer {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Entrer le nom du topic");
            return;
        }

        String topicName = args[0];

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "test-group");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer =
                new KafkaConsumer<>(props);

        consumer.subscribe(Arrays.asList(topicName));

        while (true) {
            ConsumerRecords<String, String> records =
                    consumer.poll(Duration.ofMillis(100));

            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset=%d key=%s value=%s%n",
                        record.offset(),
                        record.key(),
                        record.value());
            }
        }
    }
}
