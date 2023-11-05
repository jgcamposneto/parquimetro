package br.com.fiap.postech.parquimetro.controller;

import br.com.fiap.postech.parquimetro.dominio.Estacionamento;
import br.com.fiap.postech.parquimetro.dto.DadosDetalhamentoEstacionamentoDTO;
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
        var dto = estacionamentoService.registrar(dados);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("{id}")
public ResponseEntity detalhar(@PathVariable UUID id) {
        var estacionamento = estacionamentoService.findById(id);
        return ResponseEntity.ok(new DadosDetalhamentoEstacionamentoDTO(estacionamento));
    }
}
