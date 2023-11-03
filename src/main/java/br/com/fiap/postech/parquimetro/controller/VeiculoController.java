package br.com.fiap.postech.parquimetro.controller;

import br.com.fiap.postech.parquimetro.dto.DadosAtualizacaoVeiculoDTO;
import br.com.fiap.postech.parquimetro.service.VeiculoService;
import br.com.fiap.postech.parquimetro.dominio.Veiculo;
import br.com.fiap.postech.parquimetro.dto.DadosCadastroVeiculoDTO;
import br.com.fiap.postech.parquimetro.dto.DadosDetalhamentoVeiculoDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @Autowired
    private Validator validator;

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoVeiculoDTO>> listar(@PageableDefault(size = 10, sort = {"placa"}) Pageable paginacao) {
        return ResponseEntity.ok(veiculoService.findAll(paginacao).map(DadosDetalhamentoVeiculoDTO::new));
    }

    @GetMapping("{id}")
    public ResponseEntity detalhar(@PathVariable UUID id) {
        var veiculo = veiculoService.findById(id);
        return ResponseEntity.ok(new DadosDetalhamentoVeiculoDTO(veiculo));
    }

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroVeiculoDTO dados, UriComponentsBuilder uriBuilder) {
        var violacoesToMap = validar(dados);
        if (!violacoesToMap.isEmpty())
            return ResponseEntity.badRequest().body(violacoesToMap);
        var veiculo = new Veiculo(dados);
        veiculoService.save(veiculo);
        var uri = uriBuilder.path("/{id}").buildAndExpand((veiculo.getId())).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoVeiculoDTO(veiculo));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoVeiculoDTO dados) {
        var violacoesToMap = validar(dados);
        if (!violacoesToMap.isEmpty())
            return ResponseEntity.badRequest().body(violacoesToMap);
        var veiculo = veiculoService.findById(dados.id());
        veiculo.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoVeiculoDTO(veiculo));
    }

    @Transactional
    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable UUID id) {
        veiculoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private <T> Map<Path, String> validar(T form) {
        var violacoes = validator.validate(form);
        return violacoes
                .stream()
                .collect(Collectors.toMap(ConstraintViolation::getPropertyPath, ConstraintViolation::getMessage));
    }

}
