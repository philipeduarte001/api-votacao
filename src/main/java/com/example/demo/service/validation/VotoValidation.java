package com.example.demo.service.validation;

import com.example.demo.models.Voto;
import org.springframework.stereotype.Service;

@Service
public interface VotoValidation {
    void validar(Voto voto);
}
