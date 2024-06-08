package com.example.bici.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioDTO {
    private int creditosRestantes;
    private String numeroDoCartao;

    // Constructor com argumento
    public UsuarioDTO(int creditosRestantes) {
        this.setCreditosRestantes(creditosRestantes);
    }

}



