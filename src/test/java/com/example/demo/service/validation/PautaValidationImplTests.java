package com.example.demo.service.validation;

import com.example.demo.service.PautaService;
import com.example.demo.service.impl.PautaServiceImpl;
import com.example.demo.service.validation.impl.PautaValidationImpl;
import com.example.demo.exception.SessionClosedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.example.demo.builders.pauta.PautaBuilder.anOpenAgenda;
import static com.example.demo.builders.pauta.PautaBuilder.aClosedAgenda;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

public class PautaValidationImplTests {

    @Autowired
    private PautaValidation pautaValidation;

    @MockBean
    private PautaService pautaService;

    @BeforeEach
    public void setUp() {
        pautaService = mock(PautaServiceImpl.class);
        pautaValidation = new PautaValidationImpl(pautaService);
    }

    @Test
    @DisplayName("must not throw exception when validating open agenda")
    public void naoDeveLancarExcecaoAoValidarPautaAberta() {
        Mockito.when(pautaService.searchById(any(Long.class))).thenReturn(anOpenAgenda());

        pautaValidation.validar(1L);
    }

    @Test
    @DisplayName("must throw an exception when validating closed agenda")
    public void deveLancarUmaExcecaoAoValidarPautaFechada(){
        Mockito.when(pautaService.searchById(any(Long.class))).thenReturn(aClosedAgenda());

        Assertions.assertThrows(SessionClosedException.class, ()->{
           pautaValidation.validar(1L);
        });
    }
}
