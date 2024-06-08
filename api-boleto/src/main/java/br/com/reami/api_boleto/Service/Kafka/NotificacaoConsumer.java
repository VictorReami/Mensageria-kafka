package br.com.reami.api_boleto.Service.Kafka;

import br.com.reami.api_boleto.Entity.BoletoEntity;
import br.com.reami.api_boleto.Mapper.BoletoMapper;
import br.com.reami.api_boleto.Service.BoletoService;
import br.com.reami.avro.Boleto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoConsumer {

    private final BoletoService boletoService;

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificacaoConsumer.class);

    public NotificacaoConsumer(BoletoService boletoService) {
        this.boletoService = boletoService;
    }

    @KafkaListener(topics = "spring.kafka.topico-notificacao")
    public void consumer(@Payload Boleto boleto){
        LOGGER.info(String.format("Consumindo mensagem -> %s", boleto));

        boletoService.atualizar(BoletoMapper.boletoToEntity(boleto));
    }




}
