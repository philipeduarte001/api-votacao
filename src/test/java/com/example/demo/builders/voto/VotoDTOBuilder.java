package com.example.demo.builders.voto;

import com.example.demo.dto.VoteDTO;

import static com.example.demo.util.Constants.SIM;

public class VotoDTOBuilder {

    public static VoteDTO umVotoDTO() {
        return VoteDTO.builder()
                .cpf("10338927425")
                .idCooperado(1L)
                .idPauta(1L)
                .voto(SIM)
                .build();
    }

    public static VoteDTO umVotoDTO(String voto) {
        return VoteDTO.builder()
                .cpf("10338927425")
                .idCooperado(1L)
                .idPauta(1L)
                .voto(voto)
                .build();
    }
}
