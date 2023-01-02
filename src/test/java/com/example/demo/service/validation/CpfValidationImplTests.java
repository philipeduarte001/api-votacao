package com.example.demo.service.validation;

import com.example.demo.config.CpfConfig;
import com.example.demo.service.validation.impl.CpfValidationImpl;
import com.example.demo.dto.CpfDTO;
import com.example.demo.exception.InvalidCpfException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import static com.example.demo.builders.CpfDTOBuilder.anInvalidDTOCpf;
import static com.example.demo.builders.CpfDTOBuilder.aValidDTOCpf;
import static java.lang.String.format;
import static org.mockito.Mockito.mock;

public class CpfValidationImplTests {

    @Autowired
    private CpfValidation cpfValidation;

    @Mock
    private RestTemplate restTemplate;

    private CpfConfig cpfConfig;

    String cpf;

    @BeforeEach
    public void setUp() {
        cpf = "10338927425";
        cpfConfig = new CpfConfig();
        cpfConfig.setUrl("https://user-info.herokuapp.com/users/%s");

        restTemplate = mock(RestTemplate.class);
        cpfValidation = new CpfValidationImpl(restTemplate, cpfConfig);
    }

    @Test
    @DisplayName("must throw Exception when validating Invalid CPF")
    public void cpfDeveSerInvalido() {
        Mockito.when(this.restTemplate.getForObject(format(cpfConfig.getUrl(), cpf), CpfDTO.class)).thenReturn(anInvalidDTOCpf());

        Assertions.assertThrows(InvalidCpfException.class, ()->{
           cpfValidation.validar(cpf);
        });
    }

    @Test
    @DisplayName("cpf must be valid")
    public void cpfDeveSerValido() {
        Mockito.when(this.restTemplate.getForObject(format(cpfConfig.getUrl(), cpf), CpfDTO.class)).thenReturn(aValidDTOCpf());
        cpfValidation.validar(cpf);
    }

}
