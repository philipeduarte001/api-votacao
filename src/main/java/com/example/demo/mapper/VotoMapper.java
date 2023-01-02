package com.example.demo.mapper;

import com.example.demo.dto.VoteDTO;
import com.example.demo.models.Voto;

public class VotoMapper {
    public static Voto toEntity(VoteDTO voteDTO) {
        return Voto.builder()
                .cpf(voteDTO.getCpf())
                .idPauta(voteDTO.getIdPauta())
                .idCooperado(voteDTO.getIdCooperado())
                .voto(voteDTO.getVoto())
                .build();
    }

    public static VoteDTO toDto(Voto voto) {
        return VoteDTO.builder()
                .cpf(voto.getCpf())
                .idPauta(voto.getIdPauta())
                .idCooperado(voto.getIdCooperado())
                .voto(voto.getVoto())
                .build();
    }
}
