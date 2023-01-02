package com.example.demo.service.impl;

import com.example.demo.models.Voto;
import com.example.demo.models.VotoPK;
import com.example.demo.repositories.VotoRepository;
import com.example.demo.service.VotoService;
import com.example.demo.service.validation.PautaValidation;
import com.example.demo.service.validation.VotoValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class VotoServiceImpl implements VotoService {

    private static final Logger logger = LoggerFactory.getLogger(VotoServiceImpl.class);

    private final VotoRepository votoRepository;
    private final VotoValidation votoValidation;
    private final PautaValidation pautaValidation;

    @Autowired
    public VotoServiceImpl(VotoRepository votoRepository,
                           VotoValidation votoValidation,
                           PautaValidation pautaValidation) {
        this.votoRepository = votoRepository;
        this.votoValidation = votoValidation;
        this.pautaValidation = pautaValidation;
    }

    @Override
    public Optional<Voto> buscarPorId(Voto voto){
        return votoRepository.findById(obterVotoId(voto));
    }

    @Override
    public Voto register(Voto voto) {
        votoValidation.validar(voto);
        pautaValidation.validar(voto.getIdPauta());
        logger.info("registering a new vote: " + voto);
        return votoRepository.save(voto);
    }

    private VotoPK obterVotoId(Voto voto) {
        return VotoPK.builder()
                .idCooperado(1L)
                .idPauta(voto.getIdPauta())
                .build();
    }
}
