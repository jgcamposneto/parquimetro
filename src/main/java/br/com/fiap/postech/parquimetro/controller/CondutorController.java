package br.com.fiap.postech.parquimetro.controller;

import br.com.fiap.postech.parquimetro.dominio.Condutor;
import br.com.fiap.postech.parquimetro.dto.DadosCadastroCondutorDTO;
import br.com.fiap.postech.parquimetro.dto.DadosCadastroVeiculoDTO;
import br.com.fiap.postech.parquimetro.dto.DadosDetalhamentoCondutorDTO;
import br.com.fiap.postech.parquimetro.dto.DadosDetalhamentoVeiculoDTO;
import br.com.fiap.postech.parquimetro.service.CondutorService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/condutores")
public class CondutorController {

    @Autowired
    private CondutorService condutorService;

    @Autowired
    private Validator validator;

    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DadosCadastroCondutorDTO dados, UriComponentsBuilder uriBuilder) {
        var violacoesToMap = validar(dados);
        if (!violacoesToMap.isEmpty())
            return ResponseEntity.badRequest().body(violacoesToMap);
        var condutor = new Condutor(dados);
        condutorService.save(condutor);
        var uri = uriBuilder.path("/{id}").buildAndExpand((condutor.getId())).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoCondutorDTO(condutor));
    }

    @PostMapping("{id}/veiculo")
    public ResponseEntity cadastrarVeiculo(
            @PathVariable(value = "id") UUID id,
            @RequestBody @Valid DadosCadastroVeiculoDTO dados) {
        var dto = condutorService.cadastrarVeiculo(id, dados);
        return ResponseEntity.ok(dto);
    }


    private <T> Map<Path, String> validar(T form) {
        var violacoes = validator.validate(form);
        return violacoes
                .stream()
                .collect(Collectors.toMap(ConstraintViolation::getPropertyPath, ConstraintViolation::getMessage));
    }

}
