package com.example.locadorabackend.Gerencia;

import com.example.locadorabackend.Domain.diretor.Diretor;
import com.example.locadorabackend.Domain.diretor.DiretorRepository;
import com.example.locadorabackend.Domain.diretor.RequestDiretor;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/diretor")
public class DiretorControler {

    @Autowired
    private DiretorRepository diretorRepository;

    @GetMapping
    public List<Diretor> getAllDiretor(){
        return diretorRepository.findAll();
    }

    @PostMapping
    public ResponseEntity setActor(@RequestBody @Validated RequestDiretor data){
        Diretor diretor = new Diretor(data);
        this.diretorRepository.save(diretor);
        return ResponseEntity.ok().build();
    }

}
