package com.example.demo.controllers;

import com.example.demo.dto.PautaDTO;
import com.example.demo.dto.SessionDTO;
import com.example.demo.models.Pauta;
import com.example.demo.service.PautaService;
import com.example.demo.mapper.PautaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.demo.mapper.PautaMapper.toDto;

@RestController
@RequestMapping("v1/public/pautas")
public class PautaController {

    private final PautaService pautaService;

    @Autowired
    public PautaController(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @PostMapping(headers = "Api-Version=1")
    public ResponseEntity<Object> register(@RequestBody PautaDTO pautaDTO) {
        Pauta agendaRegistered = pautaService.register(PautaMapper.toEntity(pautaDTO));
        return new ResponseEntity<>(toDto(agendaRegistered), HttpStatus.CREATED);
    }

    @PostMapping(value = "/abrir", headers = "Api-Version=1")
    public ResponseEntity<Object> openPoll(@RequestBody SessionDTO sessionDTO) {
        Pauta agendaOpen = pautaService.openVote(sessionDTO);
        return new ResponseEntity<>(toDto(agendaOpen), HttpStatus.ACCEPTED);
    }
}
