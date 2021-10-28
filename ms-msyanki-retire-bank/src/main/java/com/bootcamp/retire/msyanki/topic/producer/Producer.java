package com.bootcamp.retire.msyanki.topic.producer;

import com.bootcamp.retire.msyanki.documents.entities.Retire;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    private static final String TOPIC_RETIRE = "retire";

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public Producer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Retire retire) {
        logger.info(String.format("#### -> Producing retire -> %s", retire));
        kafkaTemplate.send(TOPIC_RETIRE, retire);
    }


}
