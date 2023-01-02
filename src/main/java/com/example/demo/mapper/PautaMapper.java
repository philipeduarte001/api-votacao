package com.example.demo.mapper;

import com.example.demo.dto.PautaDTO;
import com.example.demo.models.Pauta;

public class PautaMapper {
    public static Pauta toEntity(PautaDTO pautaDTO) {
        return Pauta.builder()
                .id(pautaDTO.getId())
                .status(pautaDTO.getStatus())
                .tempoLimite(pautaDTO.getTempoLimite())
                .titulo(pautaDTO.getTitulo())
                .build();
    }

    public static PautaDTO toDto(Pauta pauta) {
        return PautaDTO.builder()
                .id(pauta.getId())
                .status(pauta.getStatus())
                .tempoLimite(pauta.getTempoLimite())
                .titulo(pauta.getTitulo())
                .build();
    }
}
