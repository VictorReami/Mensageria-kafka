package br.com.reami.api_boleto.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoletoRequestDTO {

    @NotNull(message = "O codigo de barras não pode ser nulo")
    @NotEmpty(message = "O codigo de barras não pode ser nulo")
    private String codigoBarras;
}
