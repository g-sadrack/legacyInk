package br.com.legacyink.domain.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
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

    public Estudio(Long id, String nome, String telefone, String email, String cnpj, String razaoSocial,
                   String redesSociais, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.redesSociais = redesSociais;
        this.endereco = endereco;
    }

    public void adicionarItem(Item item) {
        estoque.add(item);
    }

    public void removerItem(Item item) {
        estoque.remove(item);
    }

    public void associarTatuador(Tatuador tatuador) {
        tatuadores.add(tatuador);
    }
}