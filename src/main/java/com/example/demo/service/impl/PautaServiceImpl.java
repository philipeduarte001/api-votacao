package com.example.demo.service.impl;

import com.example.demo.models.Pauta;
import com.example.demo.repositories.PautaRepository;
import com.example.demo.service.PautaService;
import com.example.demo.dto.SessionDTO;
import com.example.demo.exception.PautaNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.example.demo.util.Constants.ABERTA;
import static com.example.demo.util.Constants.PAUTA_NAO_ENCONTRADA_EXCEPTION;

@Component
public class PautaServiceImpl implements PautaService {

    private static final Logger logger = LoggerFactory.getLogger(PautaServiceImpl.class);

    private final PautaRepository pautaRepository;

    @Autowired
    public PautaServiceImpl(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    @Override
    public Pauta register(Pauta pauta) {
        pauta.obterStatusFechadaCasoNulo(pauta);
        logger.info("saving agenda: " + pauta);
        return pautaRepository.save(pauta);
    }

    @Override
    public Pauta openVote(SessionDTO sessionDTO) {
        logger.info("opening new session: " + sessionDTO);
        Pauta pauta = searchById(sessionDTO.getIdPauta());
        pauta.abrirVotacao(sessionDTO);

        logger.info("salvando pauta: " + pauta);
        return pautaRepository.save(pauta);
    }

    @Override
    public Pauta searchById(Long id) {
        logger.info("opening new Tariff by id: " + id);
        return pautaRepository.findById(id).orElseThrow(() -> {
            throw new PautaNotFoundException(PAUTA_NAO_ENCONTRADA_EXCEPTION);
        });
    }

    @Override
    public List<Pauta> consultarPautasAbertas() {
        return pautaRepository.findAllByStatus(ABERTA);
    }

    @Override
    public Pauta atualizarPauta(Pauta pauta) {
        logger.info("updating agenda: " + pauta);
        return this.pautaRepository.save(pauta);
    }
}
