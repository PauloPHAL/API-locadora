package com.example.locadorabackend.Gerencia;

import com.example.locadorabackend.Domain.Controle_Acervo.classe.Classe;
import com.example.locadorabackend.Domain.Controle_Cliente.cliente.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/cliente")
public class ClienteControler {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private SocioRepository socioRepository;

    @Autowired
    private DepedenteRepository depedenteRepository;

    @GetMapping
    public List<Cliente> getAllCliente(){
        return clienteRepository.findAll();
    }

    @GetMapping("/socio")
    public List<Socio> getAllSocio(){
        return socioRepository.findAll();
    }

    @GetMapping("/dependente")
    public List<Dependente> getAllDepedente(){
        return depedenteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarCliente(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity setCliente(@RequestBody @Validated RequestCliente data){
        if(data.tipoCliente().equals("Socio")){
            Socio cliente = new Socio(data);
            this.socioRepository.save(cliente);
        }else if(data.tipoCliente().equals("Dependente")){
            Dependente cliente = new Dependente(data);
            this.depedenteRepository.save(cliente);
        }
        return ResponseEntity.ok().build();
    }


}
