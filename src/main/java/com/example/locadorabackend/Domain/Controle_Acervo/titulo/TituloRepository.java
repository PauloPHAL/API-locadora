package com.example.locadorabackend.Domain.Controle_Acervo.titulo;

import com.example.locadorabackend.Domain.Controle_Acervo.classe.Classe;
import com.example.locadorabackend.Domain.Controle_Acervo.diretor.Diretor;
import com.example.locadorabackend.Domain.Controle_Acervo.actor.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TituloRepository extends JpaRepository<Titulo,Long> {
    List<Titulo> findByAtoresContains(Actor ator);
    boolean existsByClasse(Classe classe);
    boolean existsByDiretor(Diretor diretor);
}
