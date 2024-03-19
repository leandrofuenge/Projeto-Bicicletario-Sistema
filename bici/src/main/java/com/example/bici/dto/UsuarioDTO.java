package com.example.bici.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioDTO {
    private int creditosRestantes;
    @Setter private String numeroDoCartao;

    // Construtor sem argumentos
    public UsuarioDTO() {
        // Você pode inicializar os valores padrão aqui, se necessário
        this.creditosRestantes = 0; // Por exemplo, inicializando com zero
    }

    // Construtor com argumento
    public UsuarioDTO(int creditosRestantes) {
        this.creditosRestantes = creditosRestantes;
    }
}
