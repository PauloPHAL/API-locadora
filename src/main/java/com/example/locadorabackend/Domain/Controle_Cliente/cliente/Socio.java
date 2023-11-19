package com.example.locadorabackend.Domain.Controle_Cliente.cliente;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("Socio")
public class Socio extends Cliente implements Serializable {
    @Column(length =14 , updatable = false)
    private String cpf;

    @Column(length =30 )
    private String telefone;

    @Column(length =100 )
    private String endereco;

    @OneToMany(mappedBy = "socio", fetch = FetchType.EAGER)
    private List<Dependente> dependentes;


    public Socio(RequestCliente requestCliente) {
        super(requestCliente);
        this.cpf = requestCliente.cpf();
        this.telefone = requestCliente.telefone();
        this.endereco = requestCliente.endereco();
        this.dependentes = requestCliente.dependentes();
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setDependentes(List<Dependente> dependentes) {
        this.dependentes = dependentes;
    }

    public List<Dependente> getDependentes() {
        return dependentes;
    }


}
