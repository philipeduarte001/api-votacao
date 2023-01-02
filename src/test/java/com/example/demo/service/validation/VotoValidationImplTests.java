package com.example.demo.service.validation;

import com.example.demo.models.VotoPK;
import com.example.demo.repositories.VotoRepository;
import com.example.demo.service.validation.impl.VotoValidationImpl;
import com.example.demo.exception.DuplicateVoteException;
import com.example.demo.exception.InvalidVoteException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static com.example.demo.builders.voto.VotoBuilder.umVoto;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

public class VotoValidationImplTests {

    @Autowired
    private VotoValidation votoValidation;

    @MockBean
    private CpfValidation cpfValidation;

    @MockBean
    private VotoRepository votoRepository;

    @BeforeEach
    public void setUp() {
        cpfValidation = mock(CpfValidation.class);
        votoRepository = mock(VotoRepository.class);
        this.votoValidation = new VotoValidationImpl(votoRepository, cpfValidation);
    }

    @Test
    @DisplayName("must not throw exception when validating CPF")
    public void naoDeveLancarExcecaoAoValidar(){
        Mockito.when(this.votoRepository.findById(any(VotoPK.class))).thenReturn(Optional.empty());

        votoValidation.validar(umVoto());
    }

    @Test
    @DisplayName("should throw exception when validating duplicate vote")
    public void deveLancarExcecaoAoValidarVotoDuplicado(){
        Mockito.when(this.votoRepository.findById(any(VotoPK.class))).thenReturn(Optional.of(umVoto()));

        Assertions.assertThrows(DuplicateVoteException.class, ()->{
            votoValidation.validar(umVoto());
        });
    }

    @Test
    @DisplayName("must not throw exception when validating Yes valid vote")
    public void naoDeveLancarExcecaoAoValidarVotoSimValido(){
        Mockito.when(this.votoRepository.findById(any(VotoPK.class))).thenReturn(Optional.empty());

        votoValidation.validar(umVoto("Sim"));
    }

    @Test
    @DisplayName("must throw exception when validating vote Yes invalid")
    public void deveLancarExcecaoAoValidarVotoSimInvalido(){
        Mockito.when(this.votoRepository.findById(any(VotoPK.class))).thenReturn(Optional.empty());

        Assertions.assertThrows(InvalidVoteException.class, ()->{
            votoValidation.validar(umVoto("sim"));
        });
    }

    @Test
    @DisplayName("must not throw exception when validating vote Not valid")
    public void naoDeveLancarExcecaoAoValidarVotoNaoValido(){
        Mockito.when(this.votoRepository.findById(any(VotoPK.class))).thenReturn(Optional.empty());

        votoValidation.validar(umVoto("Não"));
    }


    @Test
    @DisplayName("must throw exception when validating invalid vote")
    public void deveLancarExcecaoAoValidarVotoInvalido(){
        Mockito.when(this.votoRepository.findById(any(VotoPK.class))).thenReturn(Optional.empty());

        Assertions.assertThrows(InvalidVoteException.class, ()->{
            votoValidation.validar(umVoto("sim"));
        });
    }



}
