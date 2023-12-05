package com.api.gastapi.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


public record NotaDto (


    @NotBlank String numeronota,
    @NotBlank String descricao,
    @NotBlank String valor,
    @NotBlank String quantidade

)
{
    }