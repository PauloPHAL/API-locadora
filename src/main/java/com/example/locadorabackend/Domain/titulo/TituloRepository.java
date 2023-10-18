package com.example.locadorabackend.Domain.titulo;

import com.example.locadorabackend.Domain.actor.Actor;
import com.example.locadorabackend.Domain.classe.Classe;
import com.example.locadorabackend.Domain.diretor.Diretor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TituloRepository extends JpaRepository<Titulo,Long> {
    List<Titulo> findByAtoresContains(Actor ator);
    boolean existsByClasse(Classe classe);
    boolean existsByDiretor(Diretor diretor);
}
