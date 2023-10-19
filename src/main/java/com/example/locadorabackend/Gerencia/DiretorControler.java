package com.example.locadorabackend.Gerencia;

import com.example.locadorabackend.Domain.Controle_Acervo.diretor.Diretor;
import com.example.locadorabackend.Domain.Controle_Acervo.diretor.DiretorRepository;
import com.example.locadorabackend.Domain.Controle_Acervo.diretor.RequestDiretor;
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
@RequestMapping("/api/diretor")
public class DiretorControler {

    @Autowired
    private DiretorRepository diretorRepository;

    @Autowired
    private TituloRepository tituloRepository;

    @GetMapping
    public List<Diretor> getAllDiretor(){
        return diretorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Diretor> buscarDiretor(@PathVariable Long id) {
        Optional<Diretor> diretor = diretorRepository.findById(id);
        return diretor.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity setActor(@RequestBody @Validated RequestDiretor data){
        Diretor diretor = new Diretor(data);
        this.diretorRepository.save(diretor);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Diretor> atualizarDiretor(@PathVariable Long id, @RequestBody @Validated RequestDiretor data) {
        Optional<Diretor> optionalDiretor = diretorRepository.findById(id);
        if (optionalDiretor.isPresent()) {
            Diretor diretor = optionalDiretor.get();
            diretor.setNome(data.nome());
            diretorRepository.save(diretor);
            return ResponseEntity.ok(diretor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirDiretor(@PathVariable Long id) {
        Optional<Diretor> optionalDiretor = diretorRepository.findById(id);
        if (optionalDiretor.isPresent()) {

            if (isDiretorAssociadoATitulos(optionalDiretor.get())) {
                return ResponseEntity.badRequest().body("O Diretor está associada a títulos e não pode ser excluído.");
            }
            diretorRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nao Encontrado no Banco");
        }
    }

    private boolean isDiretorAssociadoATitulos(Diretor diretor){
        return tituloRepository.existsByDiretor(diretor);
    }

}
