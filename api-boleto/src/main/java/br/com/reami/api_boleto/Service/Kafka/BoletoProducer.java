package br.com.reami.api_boleto.Service.Kafka;

import br.com.reami.api_boleto.DTO.BoletoDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class BoletoProducer {

    @Value("${spring.kafka.topico-boleto}")
    public String topico;

    private final KafkaTemplate<String, BoletoDTO> kafkaTemplate;

    public BoletoProducer(KafkaTemplate<String, BoletoDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void enviarMensagem(BoletoDTO boleto){

        kafkaTemplate.send(topico, boleto);

    }


}
