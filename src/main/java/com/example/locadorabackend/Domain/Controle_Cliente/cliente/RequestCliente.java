package com.example.locadorabackend.Domain.Controle_Cliente.cliente;

import com.example.locadorabackend.Domain.Controle_Cliente.locacao.Locacao;
import jakarta.validation.constraints.NotBlank;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public record RequestCliente (
        Long id,
        @NotBlank String numInscricao,
        @NotBlank String nome,
        @NotBlank String dtNascimento,
        @NotBlank String sexo,
        boolean isAtivo,
        String cpf,
        String telefone,
        String endereco,
        List<Locacao> locacoes,
        List<Dependente> dependentes,
        Socio socio,
        String tipoCliente

) {

    public Date getDataNascimento() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(dtNascimento);
        } catch (Exception e) {
            return null;
        }
    }

}
