package br.com.reami.api_boleto.DTO;

import br.com.reami.api_boleto.Enums.SituacaoBoleto;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoletoDTO {

    private String codigoBarras;

    private SituacaoBoleto situacaoBoleto;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataAtualizacao;

}
