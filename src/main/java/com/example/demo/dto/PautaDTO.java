package com.example.demo.dto;

import com.example.demo.util.LocalDateTimeDeserializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PautaDTO implements Serializable {

    @JsonProperty("id_pauta")
    private Long id;

    @JsonProperty("titulo")
    private String titulo;

    @JsonProperty("status")
    private String status;

    @JsonProperty("datahora_limite")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime tempoLimite;
}
