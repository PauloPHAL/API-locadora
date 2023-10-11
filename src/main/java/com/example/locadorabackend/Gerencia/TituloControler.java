package com.example.locadorabackend.Gerencia;

import com.example.locadorabackend.Domain.titulo.RequestTitulo;
import com.example.locadorabackend.Domain.titulo.Titulo;
import com.example.locadorabackend.Domain.titulo.TituloRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/titulo")
public class TituloControler {

    @Autowired
    private TituloRepository tituloRepository;

    @GetMapping
    public List<Titulo> getAllTitulos(){
        return tituloRepository.findAll();
    }

    @PostMapping
    public ResponseEntity setTitulo(@RequestBody @Validated RequestTitulo data){
        Titulo titulo = new Titulo(data);
        this.tituloRepository.save(titulo);
        return ResponseEntity.ok().build();
    }
}
