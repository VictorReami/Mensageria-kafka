package br.com.reami.api_boleto.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoletoRequestDTO {

    private String codigoBarras;
}
