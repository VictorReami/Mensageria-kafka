package br.com.reami.validador_boleto.Service;

import br.com.reami.validador_boleto.Entity.BoletoEntity;
import br.com.reami.validador_boleto.Enums.SituacaoBoleto;
import br.com.reami.validador_boleto.Repository.BoletoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ValidarBoletoService {

    private final BoletoRepository boletoRepository;

    public ValidarBoletoService(BoletoRepository boletoRepository) {
        this.boletoRepository = boletoRepository;
    }

    public void validar(BoletoEntity boleto){
        var codigo = Integer.parseInt(boleto.getCodigoBarras().substring(0,1));

        if(codigo % 2 == 0){
            //Todo boleto invalido
            complementarBoletoErro(boleto);

            //Salvar na base
            boletoRepository.save(boleto);

            //Notificar

        }else{
            //Todo boleto valido
            //Salvar em base
            //Notifica que o boleto foi validado com sucesso
            //Seguir com o pagamento
        }

    }

    private void complementarBoletoErro(BoletoEntity boleto){
        boleto.setDataCriacao(LocalDateTime.now());
        boleto.setDataAtualizacao(LocalDateTime.now());
        boleto.setSituacaoBoleto(SituacaoBoleto.ERRO_VALIDACAO);
    }


}
