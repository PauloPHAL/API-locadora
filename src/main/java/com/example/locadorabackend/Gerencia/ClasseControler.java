package com.example.locadorabackend.Gerencia;

import com.example.locadorabackend.Domain.actor.Actor;
import com.example.locadorabackend.Domain.classe.Classe;
import com.example.locadorabackend.Domain.classe.ClasseRepository;
import com.example.locadorabackend.Domain.classe.RequestClasse;
import com.example.locadorabackend.Domain.diretor.Diretor;
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
import java.util.Optional;

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

    @GetMapping("/{id}")
    public ResponseEntity<Classe> buscarClasse(@PathVariable Long id) {
        Optional<Classe> classe = classeRepository.findById(String.valueOf(id));
        return classe.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
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

    @PutMapping("/{id}")
    public ResponseEntity<Classe> atualizarClasse(@PathVariable Long id, @RequestBody @Validated RequestClasse data) {
        Optional<Classe> optionalClasse = classeRepository.findById(String.valueOf(id));
        if (optionalClasse.isPresent()) {
            Classe classe = optionalClasse.get();
            classe.setNome(data.nome());
            classe.setValor(data.valor());
            classe.setDataDevolucao(data.getDataDevolucaoAsDate());
            classeRepository.save(classe);
            return ResponseEntity.ok(classe);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirClasse(@PathVariable Long id) {
        Optional<Classe> optionalClasse = classeRepository.findById(String.valueOf(id));
        if (optionalClasse.isPresent()) {
            classeRepository.deleteById(String.valueOf(id));
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
