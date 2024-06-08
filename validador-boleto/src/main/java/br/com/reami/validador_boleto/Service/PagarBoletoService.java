package br.com.reami.validador_boleto.Service;

import br.com.reami.validador_boleto.Entity.BoletoEntity;
import br.com.reami.validador_boleto.Enums.SituacaoBoleto;
import br.com.reami.validador_boleto.Mapper.BoletoMapper;
import br.com.reami.validador_boleto.Repository.BoletoRepository;
import br.com.reami.validador_boleto.Service.Kafka.NotificacaoProducer;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PagarBoletoService {

    private final BoletoRepository boletoRepository;

    private final NotificacaoProducer notificacaoProducer;

    public PagarBoletoService(BoletoRepository boletoRepository, NotificacaoProducer notificacaoProducer) {
        this.boletoRepository = boletoRepository;
        this.notificacaoProducer = notificacaoProducer;
    }

    @SneakyThrows
    public void pagar(BoletoEntity boleto) throws InterruptedException {
        Thread.sleep(10000);

        String codigoBarrasNumeros = boleto.getCodigoBarras().replaceAll("[^0-9]", "");

        if(codigoBarrasNumeros.length() > 47){
            //Todo boleto não pago
            //complementar boleto
            complementarBoletoErro(boleto);
        }else{
            //Todo boleto pago
            //complementar boleto
            complementarBoletoPago(boleto);
        }

        //Salvar boleto
        boletoRepository.save(boleto);

        //notificar
        notificacaoProducer.enviarMensagem(BoletoMapper.boletoToAvro(boleto));
    }

    private void complementarBoletoErro(BoletoEntity boleto){
        boleto.setDataAtualizacao(LocalDateTime.now());
        boleto.setSituacaoBoleto(SituacaoBoleto.ERRO_PAGAMENTO);
    }

    private void complementarBoletoPago(BoletoEntity boleto){
        boleto.setDataAtualizacao(LocalDateTime.now());
        boleto.setSituacaoBoleto(SituacaoBoleto.PAGO);
    }


}
