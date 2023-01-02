package com.example.demo.service.validation;

import org.springframework.stereotype.Service;

@Service
public interface CpfValidation {

    void validar(String cpf);
}
