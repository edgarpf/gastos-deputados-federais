package com.gastos.deputado.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gastos.deputado.dto.Deputado;
import com.gastos.deputado.repository.DeputadoRepository;

@RestController
@RequestMapping
public class BackendRestController {
	
	@Autowired
	private DeputadoRepository deputiesRepository;
	
    @GetMapping
    public ResponseEntity<List<Deputado>> scraperDeputies() {
        return ResponseEntity.ok((StreamSupport.stream(deputiesRepository.findAll().spliterator(), false)
                .collect(Collectors.toList())));
    }
}
