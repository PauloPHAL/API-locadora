package com.example.locadorabackend.Gerencia;

import com.example.locadorabackend.Domain.Controle_Acervo.titulo.RequestTitulo;
import com.example.locadorabackend.Domain.Controle_Acervo.titulo.Titulo;
import com.example.locadorabackend.Domain.Controle_Acervo.titulo.TituloRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public ResponseEntity<Titulo> buscarTitulo(@PathVariable Long id) {
        Optional<Titulo> titulo = tituloRepository.findById(id);
        return titulo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @SuppressWarnings("rawtypes")
    @PostMapping
    public ResponseEntity setTitulo(@RequestBody @Validated RequestTitulo data){
        Titulo titulo = new Titulo(data);
        this.tituloRepository.save(titulo);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Titulo> atualizarTitulo(@PathVariable Long id, @RequestBody @Validated RequestTitulo data) {
        Optional<Titulo> optionalTitulo = tituloRepository.findById(id);
        if (optionalTitulo.isPresent()) {
            Titulo titulo = optionalTitulo.get();
            titulo.setNome(data.nome());
            titulo.setAno(data.ano());
            titulo.setSinopse(data.sinopse());
            titulo.setCategoria(data.categoria());
            titulo.setAtores(data.atores());
            titulo.setDiretor(data.diretor());
            titulo.setClasse(data.classe());
            titulo.setItens(data.itens());
            tituloRepository.save(titulo);
            return ResponseEntity.ok(titulo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirTitulo(@PathVariable Long id) {
        Optional<Titulo> optionalTitulo = tituloRepository.findById(id);
        if (optionalTitulo.isPresent()) {
            tituloRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
