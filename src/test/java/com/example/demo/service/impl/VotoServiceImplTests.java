package com.example.demo.service.impl;

import com.example.demo.models.Voto;
import com.example.demo.models.VotoPK;
import com.example.demo.repositories.VotoRepository;
import com.example.demo.service.VotoService;
import com.example.demo.service.validation.PautaValidation;
import com.example.demo.service.validation.VotoValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static com.example.demo.builders.voto.VotoBuilder.umVoto;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

public class VotoServiceImplTests {

    private VotoService votoService;

    private VotoRepository votoRepository;

    private VotoValidation votoValidation;

    private PautaValidation pautaValidation;

    private Voto voto;

    @BeforeEach
    public void setUp() {
        voto = umVoto();
        votoValidation = mock(VotoValidation.class);
        pautaValidation = mock(PautaValidation.class);
        votoRepository = mock(VotoRepository.class);
        votoService = new VotoServiceImpl(votoRepository, votoValidation, pautaValidation);
    }

    @Test
    @DisplayName("must register vote")
    public void deveCadastrarVotoComSucesso() {
        Mockito.when(votoRepository.save(any(Voto.class))).thenReturn(voto);

        Voto esperado = votoService.register(umVoto());

        Assertions.assertEquals(esperado.getCpf(), voto.getCpf());
        Assertions.assertEquals(esperado.getVoto(), voto.getVoto());
        Assertions.assertEquals(esperado.getIdPauta(), voto.getIdPauta());
    }

    @Test
    @DisplayName("must fetch vote by id")
    public void deveBuscarVotoPorId() {
        Mockito.when(votoRepository.findById(any(VotoPK.class))).thenReturn(Optional.of(umVoto()));

        Optional<Voto> esperado = votoService.buscarPorId(umVoto());

        Assertions.assertTrue(esperado.isPresent());
    }
}
