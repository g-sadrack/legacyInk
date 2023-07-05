package br.com.legacyink.api.dto.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemInput {
    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;
    @NotNull
    private Integer quantidade;

}