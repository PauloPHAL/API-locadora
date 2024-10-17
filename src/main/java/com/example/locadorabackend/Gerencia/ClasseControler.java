package com.example.locadorabackend.Gerencia;

import com.example.locadorabackend.Domain.Controle_Acervo.classe.Classe;
import com.example.locadorabackend.Domain.Controle_Acervo.classe.ClasseRepository;
import com.example.locadorabackend.Domain.Controle_Acervo.classe.RequestClasse;
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
@RequestMapping("/api/classe")
public class ClasseControler {

    @Autowired
    private ClasseRepository classeRepository;

    @Autowired
    private TituloRepository tituloRepository;

    @GetMapping
    public List<Classe> getAllClasse(){
        return classeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Classe> buscarClasse(@PathVariable Long id) {
        Optional<Classe> classe = classeRepository.findById(id);
        return classe.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @SuppressWarnings("rawtypes")
    @PostMapping
    public ResponseEntity setActor(@RequestBody @Validated RequestClasse data) {
        Classe classe = new Classe(data);
        this.classeRepository.save(classe);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Classe> atualizarClasse(@PathVariable Long id, @RequestBody @Validated RequestClasse data) {
        Optional<Classe> optionalClasse = classeRepository.findById(id);
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
        Optional<Classe> optionalClasse = classeRepository.findById(id);
        if (optionalClasse.isPresent()) {

            if (isClasseAssociadoATitulos(optionalClasse.get())) {
                return ResponseEntity.badRequest().body("A Classe está associada a títulos e não pode ser excluída.");
            }
            classeRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nao Encontrado no Banco");
        }
    }

    private boolean isClasseAssociadoATitulos(Classe classe){
        return tituloRepository.existsByClasse(classe);
    }
}
