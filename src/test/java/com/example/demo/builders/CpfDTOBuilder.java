package com.example.demo.builders;

import com.example.demo.dto.CpfDTO;

import static com.example.demo.util.Constants.ABLE_TO_VOTE;
import static com.example.demo.util.Constants.UNABLE_TO_VOTE;

public class CpfDTOBuilder {

    public static CpfDTO anInvalidDTOCpf(){
        return CpfDTO.builder()
                .status(UNABLE_TO_VOTE)
                .build();
    }

    public static CpfDTO aValidDTOCpf(){
        return CpfDTO.builder()
                .status(ABLE_TO_VOTE)
                .build();
    }
}
