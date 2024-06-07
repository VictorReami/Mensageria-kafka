package br.com.reami.api_boleto.Service.Kafka;

import br.com.reami.avro.Boleto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class BoletoProducer {

    @Value("${spring.kafka.topico-boleto}")
    public String topico;

    private final KafkaTemplate<String, Boleto> kafkaTemplate;

    public BoletoProducer(KafkaTemplate<String, Boleto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void enviarMensagem(Boleto boleto){

        kafkaTemplate.send(topico, boleto);

    }


}
