package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VotoPK implements Serializable {

    private Long idPauta;
    private Long idCooperado;
}
