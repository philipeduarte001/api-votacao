package com.example.demo.service;

import com.example.demo.models.Pauta;
import com.example.demo.dto.SessionDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PautaService {
    Pauta register(Pauta toEntity);

    Pauta openVote(SessionDTO sessionDTO);

    Pauta searchById(Long id);

    List<Pauta> consultarPautasAbertas();

    Pauta atualizarPauta(Pauta pauta);
}
