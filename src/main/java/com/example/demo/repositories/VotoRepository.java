package com.example.demo.repositories;

import com.example.demo.models.Voto;
import com.example.demo.models.VotoPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotoRepository extends JpaRepository<Voto, VotoPK> {
}
