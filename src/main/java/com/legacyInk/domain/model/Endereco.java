package com.legacyInk.domain.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Embeddable
public class Endereco {
    @Column(name = "endereco_cep")
    private String cep;
    @Column(name = "endereco_rua")
    private String rua;
    @Column(name = "endereco_numero")
    private String numero;
    @Column(name = "endereco_complemento")
    private String complemento;
    @Column(name = "endereco_logradouro")
    private String logradouro;
    @ManyToOne
    @JoinColumn(name = "endereco_cidade_id")
    private Cidade cidade;
}