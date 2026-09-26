package br.edu.catolica.customer_ms.service;

import br.edu.catolica.customer_ms.exception.EventOrderException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    // Aguarda a confirmação do broker para que falhas de envio cheguem a quem chamou
    public void sendEvent(String topicName, String key, Object event){
        try {
            kafkaTemplate.send(topicName, key, event).get(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new EventOrderException("Envio do evento interrompido");
        } catch (ExecutionException | TimeoutException e) {
            throw new EventOrderException("Falha ao publicar evento: " + e.getMessage());
        }
    }
}
