package br.com.reami.api_boleto.Service;

import br.com.reami.api_boleto.Entity.BoletoEntity;
import br.com.reami.api_boleto.Enums.SituacaoBoleto;
import br.com.reami.api_boleto.Repository.BoletoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BoletoService {

    private final BoletoRepository boletoRepository;

    public BoletoService(BoletoRepository boletoRepository) {
        this.boletoRepository = boletoRepository;
    }

    public ? salvar(String codigoBarras){
        var boletoOptional = boletoRepository.findByCodigoBarras(codigoBarras);

        if(boletoOptional.isPresent() == true){
            //lancar um exception
            throw new RuntimeException("Já existe um boleto com esse codigo de barras ou já existe uma solicitação de pagamento para este boleto.");
        }

        var boletoEntity = BoletoEntity.builder().codigoBarras(codigoBarras)
                .situacaoBoleto(SituacaoBoleto.INICIALIZADO)
                .dataCriacao(LocalDateTime.now())
                .dataAtualizacao(LocalDateTime.now())
                .build();

        boletoRepository.save(boletoEntity);


    }


}