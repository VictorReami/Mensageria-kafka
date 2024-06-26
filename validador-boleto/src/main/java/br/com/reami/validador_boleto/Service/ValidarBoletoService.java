package br.com.reami.validador_boleto.Service;

import br.com.reami.validador_boleto.Entity.BoletoEntity;
import br.com.reami.validador_boleto.Enums.SituacaoBoleto;
import br.com.reami.validador_boleto.Mapper.BoletoMapper;
import br.com.reami.validador_boleto.Repository.BoletoRepository;
import br.com.reami.validador_boleto.Service.Kafka.NotificacaoProducer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ValidarBoletoService {

    private final BoletoRepository boletoRepository;
    private final NotificacaoProducer notificacaoProducer;

    private final PagarBoletoService pagarBoletoService;

    public ValidarBoletoService(BoletoRepository boletoRepository, NotificacaoProducer notificacaoProducer, PagarBoletoService pagarBoletoService) {
        this.boletoRepository = boletoRepository;
        this.notificacaoProducer = notificacaoProducer;
        this.pagarBoletoService = pagarBoletoService;
    }

    public void validar(BoletoEntity boleto) throws InterruptedException {
        var codigo = Integer.parseInt(boleto.getCodigoBarras().substring(0,1));

        if(codigo % 2 == 0){
            //Todo boleto invalido
            complementarBoletoErro(boleto);

            //Salvar na base
            boletoRepository.save(boleto);

            //Notificar
            notificacaoProducer.enviarMensagem(BoletoMapper.boletoToAvro(boleto));

        }else{
            //Todo boleto valido
            complementarBoletoSucesso(boleto);

            //Salvar em base
            boletoRepository.save(boleto);

            //Notifica que o boleto foi validado com sucesso
            notificacaoProducer.enviarMensagem(BoletoMapper.boletoToAvro(boleto));

            //Seguir com o pagamento
            pagarBoletoService.pagar(boleto);
        }

    }

    private void complementarBoletoErro(BoletoEntity boleto){
        boleto.setDataCriacao(LocalDateTime.now());
        boleto.setDataAtualizacao(LocalDateTime.now());
        boleto.setSituacaoBoleto(SituacaoBoleto.ERRO_VALIDACAO);
    }

    private void complementarBoletoSucesso(BoletoEntity boleto){
        boleto.setDataCriacao(LocalDateTime.now());
        boleto.setDataAtualizacao(LocalDateTime.now());
        boleto.setSituacaoBoleto(SituacaoBoleto.VALIDADO);
    }


}
