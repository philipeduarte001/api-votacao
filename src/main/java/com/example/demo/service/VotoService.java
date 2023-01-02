package com.example.demo.service;

import com.example.demo.models.Voto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface VotoService {
    Optional<Voto> buscarPorId(Voto voto);

    Voto register(Voto voto);
}
