package br.com.reami.api_boleto.Exception;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErroResponse {

    private String erro;

    private int codigo;

    private Date timeStamp;

    private String path;
}
