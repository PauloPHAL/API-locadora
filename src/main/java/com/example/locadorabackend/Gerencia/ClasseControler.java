package com.example.locadorabackend.Gerencia;

import com.example.locadorabackend.Domain.classe.Classe;
import com.example.locadorabackend.Domain.classe.ClasseRepository;
import com.example.locadorabackend.Domain.classe.RequestClasse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
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
    public ResponseEntity setActor(@RequestBody @Validated RequestClasse data) {
        Date dataDevolucao = data.getDataDevolucaoAsDate();
        if (dataDevolucao != null) {
            Classe classe = new Classe(data.nome(), data.valor(), dataDevolucao);
            this.classeRepository.save(classe);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("Formato de data inválido");
        }
    }

}
