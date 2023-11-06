package br.com.fiap.postech.parquimetro.controller;

import br.com.fiap.postech.parquimetro.dto.DadosRegistroEstacionamentoDTO;
import br.com.fiap.postech.parquimetro.service.EstacionamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/estacionamentos")
public class EstacionamentoController {

    @Autowired
    private EstacionamentoService estacionamentoService;

    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DadosRegistroEstacionamentoDTO dados) {
        return ResponseEntity.ok(estacionamentoService.registrar(dados));
    }

    @GetMapping("{id}")
    public ResponseEntity monitorar(@PathVariable UUID id) {
        return ResponseEntity.ok(estacionamentoService.findById(id));
    }

    @GetMapping("{id}/encerrar")
    public ResponseEntity encerrar(@PathVariable UUID id) {
        return ResponseEntity.ok(estacionamentoService.encerrar(id));
    }

    @PutMapping("{id}/pagar")
    public ResponseEntity pagar(@PathVariable UUID id) {
        return ResponseEntity.ok(estacionamentoService.pagar(id));
    }

    @GetMapping("{id}/emitirecibo")
    public ResponseEntity emitirRecibo(@PathVariable UUID id) {
        return ResponseEntity.ok(estacionamentoService.emitirRecibo(id));
    }

}
