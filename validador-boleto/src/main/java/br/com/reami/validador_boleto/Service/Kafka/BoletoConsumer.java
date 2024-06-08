package br.com.reami.validador_boleto.Service.Kafka;

import br.com.reami.avro.Boleto;
import br.com.reami.validador_boleto.Mapper.BoletoMapper;
import br.com.reami.validador_boleto.Service.ValidarBoletoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BoletoConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoletoConsumer.class);

    private final ValidarBoletoService validarBoletoService;

    public BoletoConsumer(ValidarBoletoService validarBoletoService) {
        this.validarBoletoService = validarBoletoService;
    }


    @KafkaListener(topics = "${spring.kafka.topico-boleto}", groupId = "${spring.kafka.consumer.group-id}")
    public void consomeBoleto(Boleto boleto, Acknowledgment ack) throws InterruptedException {
        LOGGER.info(String.format("Consumindo mensagem -> %s", boleto));
        validarBoletoService.validar(BoletoMapper.boletoToEntity(boleto));
        ack.acknowledge();
        //Thread.sleep(3000);
    }

}
