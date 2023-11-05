package br.com.fiap.postech.parquimetro.dto;

import br.com.fiap.postech.parquimetro.dominio.Condutor;
import br.com.fiap.postech.parquimetro.dominio.Endereco;
import br.com.fiap.postech.parquimetro.dominio.FormaDePagamento;

import java.util.UUID;

public record DadosDetalhamentoCondutorDTO(
        UUID id,
        String nome,
        Endereco endereco,
        String contato,
        FormaDePagamento formaDePagamento) {
    public DadosDetalhamentoCondutorDTO(Condutor condutor) {
        this(condutor.getId(), condutor.getNome(), condutor.getEndereco(), condutor.getContato(), condutor.getFormaDePagamento());
    }
}
