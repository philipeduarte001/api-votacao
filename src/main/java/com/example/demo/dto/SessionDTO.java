package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SessionDTO {

    @JsonProperty("id_pauta")
    private Long idPauta;

    @JsonProperty("minutos")
    private Integer minutos;

    @Override
    public String toString() {
        return "SessaoDTO{" +
                "idPauta=" + idPauta +
                ", minutos=" + minutos +
                '}';
    }
}
