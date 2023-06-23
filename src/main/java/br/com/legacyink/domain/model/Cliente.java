package br.com.legacyink.domain.model;

import br.com.legacyink.domain.model.enums.Sexo;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cliente {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer idade;
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    private String email;
    private String telefone;
    private LocalDate dataNascimento;
    @Embedded
    private Endereco endereco;

    public Cliente(Long id, String nome, Integer idade, Sexo sexo, String email, String telefone, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.email = email;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
    }
}