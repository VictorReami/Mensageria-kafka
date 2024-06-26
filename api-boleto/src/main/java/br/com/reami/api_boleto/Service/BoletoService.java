package br.com.reami.api_boleto.Service;

import br.com.reami.api_boleto.DTO.BoletoDTO;
import br.com.reami.api_boleto.Entity.BoletoEntity;
import br.com.reami.api_boleto.Enums.SituacaoBoleto;
import br.com.reami.api_boleto.Exception.ApplicationException;
import br.com.reami.api_boleto.Exception.NotFoundException;
import br.com.reami.api_boleto.Mapper.BoletoMapper;
import br.com.reami.api_boleto.Repository.BoletoRepository;
import br.com.reami.api_boleto.Service.Kafka.BoletoProducer;
import br.com.reami.avro.Boleto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BoletoService {


    private final BoletoRepository boletoRepository;

    private final BoletoProducer boletoProducer;

    public BoletoService(BoletoRepository boletoRepository, BoletoProducer boletoProducer) {
        this.boletoRepository = boletoRepository;
        this.boletoProducer = boletoProducer;
    }


    public BoletoDTO salvar(String codigoBarras){
        var boletoOptional = boletoRepository.findByCodigoBarras(codigoBarras);

        if(boletoOptional.isPresent() == true){
            //lancar um exception
            throw new ApplicationException("Já existe um boleto com esse codigo de barras ou já existe uma solicitação de pagamento para este boleto.");
        }

        var boletoEntity = BoletoEntity.builder()
                .codigoBarras(codigoBarras)
                .situacaoBoleto(SituacaoBoleto.INICIALIZADO)
                .dataCriacao(LocalDateTime.now())
                .dataAtualizacao(LocalDateTime.now())
                .build();

        boletoRepository.save(boletoEntity);
        boletoProducer.enviarMensagem(BoletoMapper.boletoToAvro(boletoEntity));

        return BoletoMapper.boletoToDTO(boletoEntity);
    }

    public BoletoDTO buscarBoletoPorCodigoBarras(String codigoBarras){
        return BoletoMapper.boletoToDTO(recuperaBoleto(codigoBarras));
    }

    private BoletoEntity recuperaBoleto(String codigoBarras){
        return boletoRepository.findByCodigoBarras(codigoBarras)
                .orElseThrow(() -> new NotFoundException("Boleto não encontrado"));
    }

    public void atualizar(BoletoEntity boleto){
        var boletoAtual = recuperaBoleto(boleto.getCodigoBarras());

        boletoAtual.setSituacaoBoleto(boleto.getSituacaoBoleto());
        boletoAtual.setDataAtualizacao(LocalDateTime.now());

        boletoRepository.save(boletoAtual);
    }

}
