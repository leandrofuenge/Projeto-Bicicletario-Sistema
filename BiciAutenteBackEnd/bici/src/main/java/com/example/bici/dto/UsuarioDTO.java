package com.example.bici.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioDTO {
    private int creditosRestantes;
    @Setter private String numeroDoCartao;

    // Construtor com argumento
    public UsuarioDTO(int creditosRestantes) {
        this.creditosRestantes = creditosRestantes;
    }
}



