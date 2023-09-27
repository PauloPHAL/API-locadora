package com.example.locadorabackend.Gerencia;

import com.example.locadorabackend.Domain.classe.Classe;
import com.example.locadorabackend.Domain.classe.ClasseRepository;
import com.example.locadorabackend.Domain.classe.RequestClasse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/classe")
public class ClasseControler {

    @Autowired
    private ClasseRepository classeRepository;

    @GetMapping
    public List<Classe> getAllClasse(){
        return classeRepository.findAll();
    }

    @PostMapping
    public ResponseEntity setActor(@RequestBody @Validated RequestClasse data){
        Classe classe = new Classe(data);
        this.classeRepository.save(classe);
        return ResponseEntity.ok().build();
    }

}
