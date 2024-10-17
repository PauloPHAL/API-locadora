package com.example.locadorabackend.Gerencia;

import com.example.locadorabackend.Domain.Controle_Acervo.item.Item;
import com.example.locadorabackend.Domain.Controle_Cliente.locacao.Locacao;
import com.example.locadorabackend.Domain.Controle_Cliente.locacao.LocacaoRepository;
import com.example.locadorabackend.Domain.Controle_Cliente.locacao.RequestLocacao;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/locar")
public class LocacaoControler {

    @Autowired
    private LocacaoRepository locacaoRepository;

    @GetMapping
    public List<Locacao> getAllLocacoes(){
        return locacaoRepository.findAll();
    }

    @SuppressWarnings("rawtypes")
    @PostMapping
    public ResponseEntity setLocacao(@RequestBody @Validated RequestLocacao data){
        Item item = data.item();
        double valor = item.getTitulo().getClasse().getValor();
        Date dataDevolucao = item.getTitulo().getClasse().getDataDevolucao();
        Locacao locacao = new Locacao(data,valor,dataDevolucao);
        this.locacaoRepository.save(locacao);
        return ResponseEntity.ok().build();
    }


}
