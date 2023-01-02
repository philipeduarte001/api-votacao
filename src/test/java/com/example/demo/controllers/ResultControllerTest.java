package com.example.demo.controllers;


import com.example.demo.service.ResultService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import static com.example.demo.builders.ResultDTOBuilder.umResultadoDTO;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(controllers = ResultController.class)
public class ResultControllerTest {

    @Autowired
    private ResultController resultController;

    @MockBean
    private ResultService resultService;

    @BeforeEach
    public void setUp() {
        standaloneSetup(this.resultController);
    }

    @Test
    @DisplayName("must get result successfully")
    public void deveObterResultadoComSucesso() {
        Mockito.when(resultService.getResult(any(Long.class))).thenReturn(umResultadoDTO());

        given()
                .header("Api-Version", 1)
                .accept(JSON)
                .when()
                .get("v1/public/results/{id}", 1L)
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
    }
}
