package com.example.demo.repositories;

import com.example.demo.models.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PautaRepository extends JpaRepository<Pauta, Long> {
    List<Pauta> findAllByStatus(String aberta);
}
