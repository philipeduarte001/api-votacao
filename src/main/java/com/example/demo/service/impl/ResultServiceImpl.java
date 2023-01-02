package com.example.demo.service.impl;

import com.example.demo.models.Pauta;
import com.example.demo.models.Voto;
import com.example.demo.service.PautaService;
import com.example.demo.service.ResultService;
import com.example.demo.dto.ResultDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.demo.util.Constants.*;

@Component
public class ResultServiceImpl implements ResultService {

    private static final Logger logger = LoggerFactory.getLogger(ResultServiceImpl.class);

    private final PautaService pautaService;

    @Autowired
    public ResultServiceImpl(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @Override
    public ResultDTO getResult(Long id) {
        logger.info("getting result of staff number: " + id);
        Pauta pauta = pautaService.searchById(id);
        return construirResultado(pauta);
    }

    private ResultDTO construirResultado(Pauta pauta) {
        Integer quantidadeSim = obterQuantidadePorOpcao(pauta.getVotos(), SIM);
        Integer quantidadeNao = obterQuantidadePorOpcao(pauta.getVotos(), NAO);

        return ResultDTO.builder()
                .seqPauta(pauta.getId())
                .titulo(pauta.getTitulo())
                .quantidadeNao(quantidadeNao)
                .quantidadeSim(quantidadeSim)
                .status(pauta.getStatus())
                .resultado(calcularVotos(quantidadeSim, quantidadeNao))
                .build();
    }

    private String calcularVotos(Integer quantidadeSim, Integer quantidadeNao) {
        if (quantidadeNao.equals(quantidadeSim)) {
            return EMPATE;
        } else if (quantidadeNao > quantidadeSim) {
            return NAO;
        } else {
            return SIM;
        }
    }

    private Integer obterQuantidadePorOpcao(Set<Voto> votos, String opcao) {
        List<Voto> votosFiltrados = votos.stream().filter(voto -> opcao.equals(voto.getVoto())).collect(Collectors.toList());
        return votosFiltrados.size();
    }
}
