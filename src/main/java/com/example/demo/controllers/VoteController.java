package com.example.demo.controllers;

import com.example.demo.dto.VoteDTO;
import com.example.demo.models.Voto;
import com.example.demo.service.VotoService;
import com.example.demo.mapper.VotoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/public/votos")
public class VoteController {

    private final VotoService votoService;

    @Autowired
    public VoteController(VotoService votoService) {
        this.votoService = votoService;
    }

    @PostMapping(headers = "Api-Version=1")
    public ResponseEntity<Object> register(@RequestBody VoteDTO voteDTO) {
        Voto registeredVote = votoService.register(VotoMapper.toEntity(voteDTO));
        return new ResponseEntity<>(VotoMapper.toDto(registeredVote), HttpStatus.CREATED);
    }
}
