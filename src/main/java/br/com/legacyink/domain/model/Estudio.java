package br.com.legacyink.domain.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Estudio {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String cnpj;
    private String razaoSocial;
    private String redesSociais;
    private LocalDate horario;

    @OneToMany
    @JoinTable(name = "estudio_estoque",
            joinColumns = @JoinColumn(name = "estudio_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    @ToString.Exclude
    private List<Item> estoque = new ArrayList<>();

    @OneToMany
    @JoinTable(name = "estudio_tatuadores",
            joinColumns = @JoinColumn(name = "estudio_id"),
            inverseJoinColumns = @JoinColumn(name = "tatuador_id"))
    @ToString.Exclude
    private List<Tatuador> tatuadores = new ArrayList<>();

    @OneToMany
    @JoinTable(name = "estudio_clientes",
            joinColumns = @JoinColumn(name = "estudio_id"),
            inverseJoinColumns = @JoinColumn(name = "cliente_id"))
    @ToString.Exclude
    private List<Cliente> clientes = new ArrayList<>();

    @Embedded
    private Endereco endereco;

    public void adicionarItem(Item item) {
        estoque.add(item);
    }

    public void removerItem(Item item) {
        estoque.remove(item);
    }

}