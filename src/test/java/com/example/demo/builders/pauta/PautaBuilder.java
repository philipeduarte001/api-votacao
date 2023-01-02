package com.example.demo.builders.pauta;

import com.example.demo.models.Pauta;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.demo.builders.voto.VotoBuilder.umVoto;
import static com.example.demo.util.Constants.*;
import static java.util.Set.of;

public class PautaBuilder {
    public static Pauta aClosedAgenda() {
        return Pauta.builder()
                .id(1L)
                .titulo("pautaTeste > all")
                .tempoLimite(LocalDateTime.now())
                .status(FECHADA).build();
    }

    public static Pauta oneAgendaClosedPerTime() {
        return Pauta.builder()
                .id(1L)
                .titulo("pautaTeste > all")
                .tempoLimite(LocalDateTime.now().minusMinutes(1))
                .status(ABERTA).build();
    }

    public static Pauta aClosedListIhNotSent() {
        return Pauta.builder()
                .enviadoKafka(false)
                .status(FECHADA)
                .build();
    }

    public static Pauta aStaffWithoutStatus() {
        return Pauta.builder()
                .titulo("pautaTeste > all")
                .build();
    }

    public static Pauta anOpenAgenda() {
        return Pauta.builder()
                .id(1L)
                .titulo("pautaTeste > all")
                .status(ABERTA)
                .tempoLimite(LocalDateTime.now().plusMinutes(2))
                .build();
    }

    public static List<Pauta> aListofRules() {
        return List.of(anOpenAgenda());
    }

    public static Pauta aTariffWithMoreVotesYes() {
        return Pauta.builder()
                .id(1L)
                .titulo("pautaTeste > all")
                .votos(of(umVoto(SIM), umVoto(SIM), umVoto(SIM), umVoto(NAO), umVoto(NAO)))
                .tempoLimite(LocalDateTime.now().minusMinutes(1))
                .status(FECHADA)
                .build();
    }

    public static Pauta aTiedAgenda() {
        return Pauta.builder()
                .id(1L)
                .titulo("pautaTeste > all")
                .votos(of(umVoto(SIM), umVoto(SIM), umVoto(SIM), umVoto(NAO), umVoto(NAO), umVoto(NAO)))
                .tempoLimite(LocalDateTime.now().minusMinutes(1))
                .status(FECHADA)
                .build();
    }

    public static Pauta aTariffWithMoreVotesNo() {
        return Pauta.builder()
                .id(1L)
                .titulo("pautaTeste > all")
                .votos(of(umVoto(SIM), umVoto(SIM), umVoto(NAO), umVoto(NAO), umVoto(NAO)))
                .tempoLimite(LocalDateTime.now().minusMinutes(1))
                .status(FECHADA)
                .build();
    }
}
