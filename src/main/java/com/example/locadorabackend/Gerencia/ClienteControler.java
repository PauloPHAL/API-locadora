package com.example.locadorabackend.Gerencia;

import com.example.locadorabackend.Domain.Controle_Cliente.cliente.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/cliente")
public class ClienteControler {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> getAllCliente(){
        return clienteRepository.findAll();
    }



    @PostMapping
    public ResponseEntity setCliente(@RequestBody @Validated RequestCliente data){
        if(data.tipoCliente().equals("Socio")){
            Cliente cliente = new Socio(data);
            this.clienteRepository.save(cliente);
        }else if(data.tipoCliente().equals("Depedente")){

        }
        return ResponseEntity.ok().build();
    }

}
