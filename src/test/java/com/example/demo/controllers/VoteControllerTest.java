package com.example.demo.controllers;

import com.example.demo.models.Voto;
import com.example.demo.service.VotoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import static com.example.demo.builders.voto.VotoBuilder.umVoto;
import static com.example.demo.builders.voto.VotoDTOBuilder.umVotoDTO;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(controllers = VoteController.class)
public class VoteControllerTest {

    @Autowired
    private VoteController voteController;

    @MockBean
    private VotoService votoService;

    @BeforeEach
    public void setUp() {
        standaloneSetup(voteController);
    }

    @Test
    @DisplayName("must register vote successfully")
    public void deveCadastrarVotoComSucesso() {
        Mockito.when(this.votoService.register(any(Voto.class))).thenReturn(umVoto());

        given().contentType(JSON)
                .header("Api-Version", 1)
                .body(umVotoDTO())
                .when()
                .post("v1/public/votos", umVotoDTO())
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value());
    }
}
