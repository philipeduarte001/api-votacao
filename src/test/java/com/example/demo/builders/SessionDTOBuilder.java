package com.example.demo.builders;

import com.example.demo.dto.SessionDTO;

public class SessionDTOBuilder {

    public static SessionDTO umaSessaoComMinuto(){
        return SessionDTO.builder()
                .idPauta(1L)
                .minutos(1)
                .build();
    }
    public static SessionDTO umaSessaoComMinuto(Integer minutos){
        return SessionDTO.builder()
                .idPauta(1L)
                .minutos(minutos)
                .build();
    }

    public static SessionDTO umaSessaoSemMinuto(){
        return SessionDTO.builder().build();
    }
}
