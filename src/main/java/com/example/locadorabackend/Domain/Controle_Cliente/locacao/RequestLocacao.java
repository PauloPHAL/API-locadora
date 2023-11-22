package com.example.locadorabackend.Domain.Controle_Cliente.locacao;

import com.example.locadorabackend.Domain.Controle_Acervo.item.Item;
import com.example.locadorabackend.Domain.Controle_Cliente.cliente.Cliente;

import java.text.SimpleDateFormat;
import java.util.Date;

public record RequestLocacao(
        Long id,

        String type,

        Item item,

        Cliente cliente,

        String dataDevolucao,

        String dataLocacao,

        double valorMulta
) {

    public Date getDataDevolucao() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(dataDevolucao);
        } catch (Exception e) {
            return null;
        }
    }

    public Date getDataLocacao() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(dataLocacao);
        } catch (Exception e) {
            return null;
        }
    }
}
