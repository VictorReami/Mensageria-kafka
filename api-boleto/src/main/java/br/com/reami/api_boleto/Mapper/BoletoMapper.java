package br.com.reami.api_boleto.Mapper;


import br.com.reami.api_boleto.DTO.BoletoDTO;
import br.com.reami.api_boleto.Entity.BoletoEntity;
import br.com.reami.avro.Boleto;


public class BoletoMapper {

    public static BoletoDTO boletoToDTO(BoletoEntity boleto){
        return BoletoDTO.builder()
                .codigoBarras(boleto.getCodigoBarras())
                .situacaoBoleto(boleto.getSituacaoBoleto())
                .dataCriacao(boleto.getDataCriacao())
                .dataAtualizacao(boleto.getDataAtualizacao())
                .build();
    }

    public static Boleto boletoToAvro(BoletoEntity boleto){
        return Boleto.newBuilder()
                .setCodigoBarras(boleto.getCodigoBarras())
                .setSituacaoBoleto(boleto.getSituacaoBoleto().ordinal())
                .build();
    }



}
