package com.example.demo.service.impl;

import com.example.demo.service.PautaService;
import com.example.demo.service.ResultService;
import com.example.demo.dto.ResultDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static com.example.demo.builders.pauta.PautaBuilder.*;
import static com.example.demo.util.Constants.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

public class ResultServiceImplTests {

    private ResultService resultService;

    private PautaService pautaService;

    @BeforeEach
    public void setUp() {
        pautaService = mock(PautaServiceImpl.class);
        resultService = new ResultServiceImpl(pautaService);
    }

    @Test
    @DisplayName("Tariff result must be Yes")
    public void resultadoDaPautaDeveSerSim() {
        Mockito.when(pautaService.searchById(any(Long.class))).thenReturn(aTariffWithMoreVotesYes());

        ResultDTO resultDTO = resultService.getResult(1L);
        Assertions.assertEquals(resultDTO.getResultado(), SIM);
    }

    @Test
    @DisplayName("agenda result must be No")
    public void resultadoDaPautaDeveSerNao() {
        Mockito.when(pautaService.searchById(any(Long.class))).thenReturn(aTariffWithMoreVotesNo());

        ResultDTO resultDTO = resultService.getResult(1L);
        Assertions.assertEquals(resultDTO.getResultado(), NAO);
    }

    @Test
    @DisplayName("agenda result must be Tie")
    public void resultadoDaPautaDeveSerEmpate() {
        Mockito.when(pautaService.searchById(any(Long.class))).thenReturn(aTiedAgenda());

        ResultDTO resultDTO = resultService.getResult(1L);
        Assertions.assertEquals(resultDTO.getResultado(), EMPATE);
    }
}
