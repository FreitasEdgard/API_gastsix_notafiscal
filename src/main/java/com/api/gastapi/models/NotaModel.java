package com.api.gastapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "nota")
public class NotaModel {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private String numeronota;
    private String descricao;
    private String valor;
    private String quantidade;


}