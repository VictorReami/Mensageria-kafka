package br.com.reami.api_boleto.Mapper;


import br.com.reami.api_boleto.DTO.BoletoDTO;
import br.com.reami.api_boleto.Entity.BoletoEntity;

public class BoletoMapper {

    public static BoletoDTO boleToDTO(BoletoEntity boleto){
        return BoletoDTO.builder()
                .codigoBarras(boleto.getCodigoBarras())
                .situacaoBoleto(boleto.getSituacaoBoleto())
                .dataCriacao(boleto.getDataCriacao())
                .dataAtualizacao(boleto.getDataAtualizacao())
                .build();
    }

}
