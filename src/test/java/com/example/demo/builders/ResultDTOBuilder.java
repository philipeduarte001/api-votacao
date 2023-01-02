package com.example.demo.builders;

import com.example.demo.dto.ResultDTO;

import static com.example.demo.util.Constants.SIM;

public class ResultDTOBuilder {

    public static ResultDTO umResultadoDTO(){
        return ResultDTO.builder()
                .seqPauta(1L)
                .titulo("pautaTeste > all")
                .quantidadeSim(100)
                .quantidadeNao(1)
                .resultado(SIM)
                .status("FECHADA")
                .build();
    }
}
