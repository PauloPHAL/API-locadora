package com.example.locadorabackend.Domain.Controle_Cliente.cliente;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("Dependente")
public class Dependente extends Cliente implements Serializable {

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idSocio")
    private Socio socio;

    public Dependente(RequestCliente requestCliente) {
        super(requestCliente);
        this.socio = requestCliente.socio();
    }


    public void setSocio(Socio socio) {
        this.socio = socio;
    }
}
