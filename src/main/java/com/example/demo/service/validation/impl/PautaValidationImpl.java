package com.example.demo.service.validation.impl;

import com.example.demo.models.Pauta;
import com.example.demo.service.PautaService;
import com.example.demo.service.validation.PautaValidation;
import com.example.demo.exception.SessionClosedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.example.demo.util.Constants.SESSAO_FECHADA_EXCEPTION;

@Component
public class PautaValidationImpl implements PautaValidation {


    private static final Logger logger = LoggerFactory.getLogger(PautaValidationImpl.class);

    private final PautaService pautaService;

    @Autowired
    public PautaValidationImpl(PautaService pautaService) {

        this.pautaService = pautaService;
    }

    @Override
    public void validar(Long idPauta) {
        logger.info("validating agenda nº: "+ idPauta);
        Pauta pauta = pautaService.searchById(idPauta);
        if (pauta.estahFechada()) {
            throw new SessionClosedException(SESSAO_FECHADA_EXCEPTION);
        }
    }

}
