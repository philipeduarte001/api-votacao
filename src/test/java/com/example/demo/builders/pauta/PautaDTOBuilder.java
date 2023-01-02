package com.example.demo.builders.pauta;

import com.example.demo.dto.PautaDTO;

public class PautaDTOBuilder {

    public static PautaDTO umaPautaDTO(){
        return PautaDTO.builder()
                .id(1L)
                .titulo("pautaTeste > all")
                .build();
    }
}
