package com.example.locadorabackend.Gerencia;


import com.example.locadorabackend.Domain.actor.Actor;
import com.example.locadorabackend.Domain.actor.ActorRepository;
import com.example.locadorabackend.Domain.actor.RequestActor;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/ator")
public class AtorControler {
    //"senha Banco De Dados": 4CGFBNShvym035rQ

    @Autowired
    private ActorRepository actorRepository;

    @GetMapping
    public List<Actor> getAllAtor(){
        return actorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actor> buscarAtor(@PathVariable Long id) {
        Optional<Actor> ator = actorRepository.findById(String.valueOf(id));
        return ator.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity setActor(@RequestBody @Validated RequestActor data){
        Actor ator = new Actor(data);
        this.actorRepository.save(ator);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Actor> atualizarAtor(@PathVariable Long id, @RequestBody Actor atorAtualizado) {
        Optional<Actor> optionalAtor = actorRepository.findById(String.valueOf(id));
        if (optionalAtor.isPresent()) {
            Actor ator = optionalAtor.get();
            ator.setNome(atorAtualizado.getNome());
            actorRepository.save(ator);
            return ResponseEntity.ok(ator);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirAtor(@PathVariable Long id) {
        Optional<Actor> optionalAtor = actorRepository.findById(String.valueOf(id));
        if (optionalAtor.isPresent()) {
            actorRepository.deleteById(String.valueOf(id));
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
