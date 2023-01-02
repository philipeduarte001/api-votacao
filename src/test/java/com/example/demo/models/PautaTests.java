package com.example.demo.models;

import com.example.demo.builders.SessionDTOBuilder;
import com.example.demo.dto.SessionDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static com.example.demo.builders.SessionDTOBuilder.umaSessaoComMinuto;
import static com.example.demo.builders.pauta.PautaBuilder.*;
import static com.example.demo.util.Constants.ABERTA;
import static com.example.demo.util.Constants.FECHADA;


public class PautaTests {


    @Test
    @DisplayName("must open Session with informed time")
    public void deveAbrirVotacao() {
        Pauta pauta = aClosedAgenda();
        SessionDTO sessao = umaSessaoComMinuto(15);

        LocalDateTime dataHoraLimite = pauta.abrirVotacao(sessao);

        Assertions.assertEquals(pauta.getStatus(), ABERTA);
        Assertions.assertEquals(pauta.getTempoLimite(), dataHoraLimite);
        Assertions.assertEquals(pauta.isEnviadoKafka(), false);
    }

    @Test
    @DisplayName("should open Session with default time")
    public void deveAbrirVotacaoComTempoPadrão() {
        Pauta pauta = aClosedAgenda();
        SessionDTO sessao = SessionDTOBuilder.umaSessaoSemMinuto();

        LocalDateTime dataHoraLimite = pauta.abrirVotacao(sessao);

        Assertions.assertEquals(pauta.getStatus(), ABERTA);
        Assertions.assertEquals(pauta.getTempoLimite(), dataHoraLimite);
        Assertions.assertEquals(pauta.isEnviadoKafka(), false);
    }

    @Test
    @DisplayName("must be closed and not sent")
    public void deveEstarFechadaIhNaoEnviada() {
        Pauta pauta = aClosedListIhNotSent();

        Assertions.assertTrue(pauta.estahFechadaIhNaoFoiEnviada());
    }

    @Test
    @DisplayName("Must open Session with agenda without status")
    public void deveAbrirVotacaoComPautaSemStatus() {
        Pauta pauta = aStaffWithoutStatus();
        pauta.obterStatusFechadaCasoNulo(pauta);

        Assertions.assertEquals(pauta.getStatus(), FECHADA);
    }

    @Test
    @DisplayName("an agenda must be closed")
    public void umaSessaoDeveEstarFechada() {
        Pauta pauta = aClosedAgenda();

        Assertions.assertTrue(pauta.estahFechada());
    }

    @Test
    @DisplayName("an agenda must be closed for a period of time")
    public void umaSessaoDeveEstarFechadaPorTempo() {
        Pauta pauta = oneAgendaClosedPerTime();

        Assertions.assertTrue(pauta.estahFechada());
    }

    @Test
    @DisplayName("an agenda must not be closed")
    public void umaSessaoNaoDeveEstarFechada() {
        Pauta pauta = anOpenAgenda();

        Assertions.assertFalse(pauta.estahFechada());
    }
}
