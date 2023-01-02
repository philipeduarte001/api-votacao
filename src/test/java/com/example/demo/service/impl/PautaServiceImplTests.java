package com.example.demo.service.impl;

import com.example.demo.models.Pauta;
import com.example.demo.repositories.PautaRepository;
import com.example.demo.service.PautaService;
import com.example.demo.dto.SessionDTO;
import com.example.demo.exception.PautaNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static com.example.demo.builders.SessionDTOBuilder.umaSessaoComMinuto;
import static com.example.demo.builders.pauta.PautaBuilder.aListofRules;
import static com.example.demo.builders.pauta.PautaBuilder.aClosedAgenda;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

public class PautaServiceImplTests {

    private Pauta pauta;
    private List<Pauta> pautas;
    private SessionDTO sessionDTO;
    private PautaService pautaService;
    private PautaRepository pautaRepository;

    @BeforeEach
    public void setUp() {
        pauta = aClosedAgenda();
        sessionDTO = umaSessaoComMinuto();
        pautas = aListofRules();

        pautaRepository = mock(PautaRepository.class);
        this.pautaService = new PautaServiceImpl(pautaRepository);

        Mockito.when(pautaRepository.save(any(Pauta.class))).thenReturn(pauta);
        Mockito.when(pautaRepository.findAllByStatus(any(String.class))).thenReturn(pautas);
        Mockito.when(pautaRepository.findById(any(Long.class))).thenReturn(Optional.of(pauta));
    }

    @Test
    @DisplayName("must register a session")
    public void deveCadastrarUmaSessao() {
        Pauta esperada = pautaService.register(pauta);

        Assertions.assertEquals(esperada.getId(), pauta.getId());
        Assertions.assertEquals(esperada.getStatus(), pauta.getStatus());
        Assertions.assertEquals(esperada.getTitulo(), pauta.getTitulo());
        Assertions.assertEquals(esperada.getTempoLimite(), pauta.getTempoLimite());
    }

    @Test
    @DisplayName("must open session on a staff")
    public void deveAbrirSessaoEmUmaPauta() {
        Pauta esperada = pautaService.openVote(sessionDTO);

        Assertions.assertEquals(esperada.getId(), pauta.getId());
        Assertions.assertEquals(esperada.getStatus(), pauta.getStatus());
        Assertions.assertEquals(esperada.getTitulo(), pauta.getTitulo());
        Assertions.assertEquals(esperada.getTempoLimite(), pauta.getTempoLimite());
    }

    @Test
    @DisplayName("must search agenda by id")
    public void deveBuscarPautaPorId() {
        Pauta esperada = pautaService.searchById(1L);

        Assertions.assertEquals(esperada.getId(), pauta.getId());
        Assertions.assertEquals(esperada.getStatus(), pauta.getStatus());
        Assertions.assertEquals(esperada.getTitulo(), pauta.getTitulo());
        Assertions.assertEquals(esperada.getTempoLimite(), pauta.getTempoLimite());
    }

    @Test
    @DisplayName("should throw Staff Not Found Exception")
    public void deveLancarPautaNaoEncontradaException() {
        Mockito.when(pautaRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        Assertions.assertThrows(PautaNotFoundException.class, () -> {
            pautaService.searchById(1L);
        });
    }

    @Test
    @DisplayName("should return open staves")
    public void deveRetornarPautasAbertas() {
        List<Pauta> pautasEsperadas = pautaService.consultarPautasAbertas();

        Assertions.assertEquals(pautasEsperadas, pautas);
    }

    @Test
    @DisplayName("must update an agenda")
    public void deveAtualizarUmaPauta() {
        Pauta esperada = pautaService.atualizarPauta(this.pauta);

        Assertions.assertEquals(esperada.getId(), pauta.getId());
        Assertions.assertEquals(esperada.getStatus(), pauta.getStatus());
        Assertions.assertEquals(esperada.getTitulo(), pauta.getTitulo());
        Assertions.assertEquals(esperada.getTempoLimite(), pauta.getTempoLimite());
    }
}
