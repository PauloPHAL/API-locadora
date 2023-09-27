package com.example.locadorabackend.Gerencia;


import com.example.locadorabackend.Domain.actor.Actor;
import com.example.locadorabackend.Domain.actor.ActorRepository;
import com.example.locadorabackend.Domain.actor.RequestActor;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    public ResponseEntity setActor(@RequestBody @Validated RequestActor data){
        Actor ator = new Actor(data);
        this.actorRepository.save(ator);
        return ResponseEntity.ok().build();
    }



}
