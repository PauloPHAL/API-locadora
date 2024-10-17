package com.example.locadorabackend.Domain.Controle_Cliente.cliente;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("Dependente")
public class Dependente extends Cliente  {

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
