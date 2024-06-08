package br.com.reami.api_boleto.Controller;

import br.com.reami.api_boleto.DTO.BoletoDTO;
import br.com.reami.api_boleto.DTO.BoletoRequestDTO;
import br.com.reami.api_boleto.Service.BoletoService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/boleto")
public class BoletoController {

    private final BoletoService boletoService;

    public BoletoController(BoletoService boletoService) {
        this.boletoService = boletoService;
    }

    @GetMapping("/{codigoBarras}")
    public ResponseEntity<BoletoDTO> buscarBoletoCodigoBarras(@PathVariable("codigoBarras") String codigoBarras){
        var boletoDTO = boletoService.buscarBoletoPorCodigoBarras(codigoBarras);

        return ResponseEntity.ok(boletoDTO);

    }

    @PostMapping
    public ResponseEntity<BoletoDTO> salvar(@Valid @RequestBody BoletoRequestDTO boletoRequestDTO){

        var boleto = boletoService.salvar(boletoRequestDTO.getCodigoBarras());

        return new ResponseEntity<>(boleto, HttpStatus.CREATED);
    }




}
