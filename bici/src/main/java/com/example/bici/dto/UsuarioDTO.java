package com.example.bici.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioDTO {
    private int creditosRestantes;

    public UsuarioDTO(int creditosRestantes) {
        this.creditosRestantes = creditosRestantes;
    }

}
