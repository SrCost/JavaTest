package com.sigabem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import javax.validation.Valid;

import com.sigabem.entities.ConsulEncomenda;
import com.sigabem.exception.ResourceNotException;
import com.sigabem.models.DadosEntrega;
import com.sigabem.models.DadosEnvio;
import com.sigabem.services.EncomendaService;

@RestController
@RequestMapping(value = "/api/versao1")
public class ConsulFreteController {
	@Autowired
    private EncomendaService encomendaService;


    @PostMapping(value = "/consultaEntrega")
    @ResponseStatus(HttpStatus.CREATED)
    public DadosEntrega SalvaEntrega(@Valid @RequestBody DadosEnvio dadosFrete) throws ResourceNotException {
        return encomendaService.SalvarConsulta(dadosFrete);
    }

    @GetMapping(value = "/consultaEncomendas")
    @ResponseStatus(HttpStatus.OK)
    public List<ConsulEncomenda> getAllConsultas() {
        return encomendaService.getAllEncomendas();
    }

}
