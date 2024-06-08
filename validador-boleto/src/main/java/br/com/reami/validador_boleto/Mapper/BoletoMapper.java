package br.com.reami.validador_boleto.Mapper;

import br.com.reami.avro.Boleto;
import br.com.reami.validador_boleto.Entity.BoletoEntity;
import br.com.reami.validador_boleto.Enums.SituacaoBoleto;
import lombok.Getter;


public class BoletoMapper {

    public static BoletoEntity boletoToEntity(Boleto boleto){
        return BoletoEntity.builder()
                .codigoBarras(boleto.getCodigoBarras().toString())
                .situacaoBoleto(SituacaoBoleto.values()[boleto.getSituacaoBoleto()])
                .build();
    }

}
