package com.example.demo.service;

import com.example.demo.dto.ResultDTO;
import org.springframework.stereotype.Service;

@Service
public interface ResultService {
    ResultDTO getResult(Long id);
}
