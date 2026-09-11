package br.edu.catolica.customer_ms.config;

import org.springframework.boot.kafka.autoconfigure.KafkaProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaBaseConfig {
    public ProducerFactory<String, Object> producerFactory(KafkaProperties kafkaProperties){
        return new DefaultKafkaProducerFactory<>(kafkaProperties.buildProducerProperties());
    }

    public KafkaTemplate<String, Object> kafkaTemplate(ProducerFactory<String, Object> producerFactory){
        return new KafkaTemplate<>(producerFactory);
    }
}
