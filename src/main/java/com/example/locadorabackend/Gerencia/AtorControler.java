package com.example.locadorabackend.Gerencia;


import com.example.locadorabackend.Domain.Controle_Acervo.actor.Actor;
import com.example.locadorabackend.Domain.Controle_Acervo.actor.ActorRepository;
import com.example.locadorabackend.Domain.Controle_Acervo.actor.RequestActor;
import com.example.locadorabackend.Domain.Controle_Acervo.titulo.Titulo;
import com.example.locadorabackend.Domain.Controle_Acervo.titulo.TituloRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/ator")
public class AtorControler {
    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private TituloRepository tituloRepository;

    @GetMapping
    public List<Actor> getAllAtor(){
        return actorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actor> buscarAtor(@PathVariable Long id) {
        Optional<Actor> ator = actorRepository.findById(id);
        return ator.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity setActor(@RequestBody @Validated RequestActor data){
        Actor ator = new Actor(data);
        this.actorRepository.save(ator);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Actor> atualizarAtor(@PathVariable Long id, @RequestBody @Validated RequestActor data) {
        Optional<Actor> optionalAtor = actorRepository.findById(id);
        if (optionalAtor.isPresent()) {
            Actor ator = optionalAtor.get();
            ator.setNome(data.nome());
            actorRepository.save(ator);
            return ResponseEntity.ok(ator);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirAtor(@PathVariable Long id) {
        Optional<Actor> optionalAtor = actorRepository.findById(id);
        if (optionalAtor.isPresent()) {
            if (isAtorAssociadoATitulos(optionalAtor.get())) {
                return ResponseEntity.badRequest().body("O ator está associado a títulos e não pode ser excluído.");
            }
            actorRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nao Encontrado no Banco");
        }
    }

    private boolean isAtorAssociadoATitulos(Actor ator){
        List<Titulo> titulosAssociados = tituloRepository.findByAtoresContains(ator);
        return !titulosAssociados.isEmpty();
    }
}
