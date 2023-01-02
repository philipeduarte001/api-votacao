package com.example.demo.service.validation.impl;

import com.example.demo.models.Voto;
import com.example.demo.models.VotoPK;
import com.example.demo.repositories.VotoRepository;
import com.example.demo.service.validation.CpfValidation;
import com.example.demo.service.validation.VotoValidation;
import com.example.demo.exception.DuplicateVoteException;
import com.example.demo.exception.InvalidVoteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.example.demo.util.Constants.*;

@Component
public class VotoValidationImpl implements VotoValidation {

    private static final Logger logger = LoggerFactory.getLogger(VotoValidationImpl.class);

    private final VotoRepository votoRepository;
    private final CpfValidation cpfValidation;

    @Autowired
    public VotoValidationImpl(VotoRepository votoRepository,
                              CpfValidation cpfValidation) {
        this.votoRepository = votoRepository;
        this.cpfValidation = cpfValidation;
    }

    @Override
    public void validar(Voto voto) {
        logger.info("validating vote: " + voto);
        Optional<Voto> votoConsultado = votoRepository.findById(obterVotoId(voto));
        validar(voto.getVoto());
        if (votoConsultado.isPresent()) {
            throw new DuplicateVoteException(VOTO_DUPLICADO_EXCEPTION);
        }
        cpfValidation.validar(voto.getCpf());
    }

    private void validar(String voto) {
        if(!(voto.equals(SIM) || voto.equals(NAO))){
            throw new InvalidVoteException(VOTO_INVALIDO_EXCEPTION);
        }
    }

    private VotoPK obterVotoId(Voto voto) {
        return VotoPK.builder()
                .idCooperado(voto.getIdCooperado())
                .idPauta(voto.getIdPauta())
                .build();
    }


}
