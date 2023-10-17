package com.example.locadorabackend.Gerencia;

import com.example.locadorabackend.Domain.item.Item;
import com.example.locadorabackend.Domain.item.ItemRepository;
import com.example.locadorabackend.Domain.item.RequestItem;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/item")
public class ItemControler {
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping
    public List<Item> getAllItens(){
        return itemRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> buscarItem(@PathVariable Long id) {
        Optional<Item> item = itemRepository.findById(id);
        return item.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity setItem(@RequestBody @Validated RequestItem data){
        Item item = new Item(data);
        this.itemRepository.save(item);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> atualizarItem(@PathVariable Long id, @RequestBody @Validated RequestItem data) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();
            item.setNumSerie(data.numSerie());
            item.setDataAquisicao(data.getDataAquisicao());
            item.setTipoItem(data.tipoItem());
            item.setTitulo(data.titulo());
            itemRepository.save(item);
            return ResponseEntity.ok(item);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirItem(@PathVariable Long id) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        if (optionalItem.isPresent()) {
            itemRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
