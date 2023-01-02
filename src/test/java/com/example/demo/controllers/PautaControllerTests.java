package com.example.demo.controllers;

import com.example.demo.models.Pauta;
import com.example.demo.service.PautaService;
import com.example.demo.dto.SessionDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import static com.example.demo.builders.SessionDTOBuilder.umaSessaoComMinuto;
import static com.example.demo.builders.pauta.PautaBuilder.anOpenAgenda;
import static com.example.demo.builders.pauta.PautaBuilder.aClosedAgenda;
import static com.example.demo.builders.pauta.PautaDTOBuilder.umaPautaDTO;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(controllers = PautaController.class)
public class PautaControllerTests {

    @Autowired
    private PautaController pautaController;

    @MockBean
    private PautaService pautaService;

    @BeforeEach
    public void setUp() {
        standaloneSetup(this.pautaController);
    }

    @Test
    @DisplayName("must register successfully")
    public void deveCadastrarPautaComSucesso() {
        Mockito.when(this.pautaService.register(any(Pauta.class))).thenReturn(aClosedAgenda());

        given().contentType(JSON)
                .header("Api-Version", 1)
                .body(umaPautaDTO())
                .when()
                .post("v1/public/pautas", umaPautaDTO())
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    @DisplayName("must open a session on a staff")
    public void deveAbrirUmaSessaoEmUmaPauta() {
        Mockito.when(this.pautaService.openVote(any(SessionDTO.class))).thenReturn(anOpenAgenda());

        given().contentType(JSON)
                .header("Api-Version", 1)
                .body(umaSessaoComMinuto())
                .when()
                .post("v1/public/pautas/abrir", umaSessaoComMinuto())
                .then().log().all()
                .statusCode(HttpStatus.ACCEPTED.value());
    }

}
