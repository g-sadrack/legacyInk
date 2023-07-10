package br.com.legacyink.domain.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Generated //Pesquisar mais sobre
@Getter
@Setter
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