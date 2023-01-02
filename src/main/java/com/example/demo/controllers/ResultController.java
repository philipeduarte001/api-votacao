package com.example.demo.controllers;

import com.example.demo.dto.ResultDTO;
import com.example.demo.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/public/results")
public class ResultController {

    private final ResultService resultService;

    @Autowired
    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @GetMapping(value = "/{id}", headers = "Api-Version=1")
    public ResponseEntity<Object> getResult(@PathVariable Long id) {
        ResultDTO resultDTO = resultService.getResult(id);
        return new ResponseEntity<>(resultDTO, HttpStatus.OK);
    }
}
